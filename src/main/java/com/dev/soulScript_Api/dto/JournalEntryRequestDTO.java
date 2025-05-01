package com.dev.soulScript_Api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalEntryRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title can be at most 150 characters")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;
}
