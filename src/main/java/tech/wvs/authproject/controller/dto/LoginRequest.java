package tech.wvs.authproject.controller.dto;

public record LoginRequest(
        String email,
        String password) {
}
