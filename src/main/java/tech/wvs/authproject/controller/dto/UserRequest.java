package tech.wvs.authproject.controller.dto;

public record UserRequest(String name,
                          String email,
                          String password) {
}
