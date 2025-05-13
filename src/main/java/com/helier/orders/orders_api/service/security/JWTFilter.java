package com.helier.orders.orders_api.service.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        
        String requestURI = httpServletRequest.getRequestURI();
        if (isPublicRoute(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        
        try {
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } else {
                sendUnauthorizedResponse(httpServletResponse, "No valid authorization token was provided.");
            }
        } catch (ExpiredJwtException e) {
            sendUnauthorizedResponse(httpServletResponse, "The token has expired.");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            sendUnauthorizedResponse(httpServletResponse, "Invalid JWT token");
        } catch (Exception e) {
            sendUnauthorizedResponse(httpServletResponse, "Authentication error:" + e.getMessage());
        }
    }
    
    private boolean isPublicRoute(String requestURI) {
        return requestURI.startsWith("/auth/");
    }
    
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.UNAUTHORIZED.value());
        errorDetails.put("error", "Unauthorized");
        errorDetails.put("message", message);
        
        response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
    }
}