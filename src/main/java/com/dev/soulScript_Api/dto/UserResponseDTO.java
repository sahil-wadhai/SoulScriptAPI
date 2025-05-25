package com.dev.soulScript_Api.dto;

import com.dev.soulScript_Api.model.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO{
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private Role role;
}
