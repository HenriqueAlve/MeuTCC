package com.example.padaria.usuario.model;

import java.util.UUID;

public record LoginResponseDto(String token, String role) {
}
