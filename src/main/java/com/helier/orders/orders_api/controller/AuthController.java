package com.helier.orders.orders_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helier.orders.orders_api.dto.request.AuthRequestDTO;
import com.helier.orders.orders_api.dto.request.SignUpRequestDTO;
import com.helier.orders.orders_api.dto.response.AuthResponseDTO;
import com.helier.orders.orders_api.dto.response.UserResponseDTO;
import com.helier.orders.orders_api.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody AuthRequestDTO authRequest) {
        AuthResponseDTO authResponse = userService.signIn(authRequest);
        
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Validated SignUpRequestDTO signupRequestDTO) {
        UserResponseDTO userProfileResponseDTO = userService.signup(signupRequestDTO);
        return new ResponseEntity<>(userProfileResponseDTO, HttpStatus.CREATED);
    }
}
