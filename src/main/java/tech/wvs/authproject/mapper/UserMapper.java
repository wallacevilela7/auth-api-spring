package tech.wvs.authproject.mapper;

import tech.wvs.authproject.controller.dto.UserRequest;
import tech.wvs.authproject.controller.dto.UserResponse;
import tech.wvs.authproject.entity.User;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        var entity = new User();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPassword(request.password());
        return entity;
    }

    public static UserResponse toResponse(User entity) {
        return new UserResponse(entity.getId(), entity.getName(), entity.getEmail());
    }
}
