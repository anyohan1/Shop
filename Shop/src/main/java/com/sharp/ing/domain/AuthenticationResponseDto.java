package com.sharp.ing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponseDto {
    private final String jwt;
}