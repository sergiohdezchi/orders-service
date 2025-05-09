package com.helier.orders.orders_api.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import com.helier.orders.orders_api.dto.request.AuthRequestDTO;
import com.helier.orders.orders_api.dto.request.SignUpRequestDTO;
import com.helier.orders.orders_api.dto.response.AuthResponseDTO;
import com.helier.orders.orders_api.dto.response.UserResponseDTO;
import com.helier.orders.orders_api.exception.BadRequestException;
import com.helier.orders.orders_api.exception.ResourceNotFoundException;
import com.helier.orders.orders_api.mapper.UserMapper;
import com.helier.orders.orders_api.model.User;
import com.helier.orders.orders_api.model.enums.Role;
import com.helier.orders.orders_api.repository.UserRepository;
import com.helier.orders.orders_api.service.security.TokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticateManagerBuilder;

    @Transactional(readOnly = true)
    public AuthResponseDTO signIn(AuthRequestDTO authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(),
            authRequest.getPassword()
        );
        Authentication authentication = authenticateManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createAccessToken(authentication);
        UserResponseDTO userProfileDTO = findByEmail(authRequest.getEmail());

        return userMapper.toAuthResponseDTO(accessToken, userProfileDTO);
    }



    @Transactional
    public UserResponseDTO signup(SignUpRequestDTO signupFormDTO) {
        boolean emailAlreadyExists = userRepository.existsByEmail(signupFormDTO.getEmail());

        if (emailAlreadyExists) {
        throw new BadRequestException("Email already in use");
        }

        User user = userMapper.toUser(signupFormDTO);
        user.setPassword(passwordEncoder.encode(signupFormDTO.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        System.out.println("User created: " + user);
        return userMapper.toUserResponseDTO(user);
    }


    @Transactional(readOnly = true)
    public UserResponseDTO findByEmail(String email) {
        User user = userRepository.findOneByEmail(email)
        .orElseThrow(ResourceNotFoundException::new);

        return userMapper.toUserResponseDTO(user);
    }
}
