package com.dev.soulScript_Api.dto;

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
}
