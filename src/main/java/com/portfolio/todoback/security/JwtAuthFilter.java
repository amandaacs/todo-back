package com.portfolio.todoback.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter {

    private final JwtService jwtService;

}
