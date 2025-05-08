package com.helier.orders.orders_api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.helier.orders.orders_api.dto.request.SignUpRequestDTO;
import com.helier.orders.orders_api.dto.response.AuthResponseDTO;
import com.helier.orders.orders_api.dto.response.UserResponseDTO;
import com.helier.orders.orders_api.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserResponseDTO toUserResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public AuthResponseDTO toAuthResponseDTO(String token, UserResponseDTO userProfile) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);
        authResponseDTO.setUser(userProfile);
        return authResponseDTO;
    }

    public User toUser(SignUpRequestDTO signupRequestDTO) {
        return modelMapper.map(signupRequestDTO, User.class);
    }
}
