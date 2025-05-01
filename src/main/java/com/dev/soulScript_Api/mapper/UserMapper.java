package com.dev.soulScript_Api.mapper;

import com.dev.soulScript_Api.dto.UserRequestDTO;
import com.dev.soulScript_Api.dto.UserResponseDTO;
import com.dev.soulScript_Api.model.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO request) {
        return User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }

    public static UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
