package com.dev.soulScript_Api.mapper;

import com.dev.soulScript_Api.dto.JournalEntryRequestDTO;
import com.dev.soulScript_Api.dto.JournalEntryResponseDTO;
import com.dev.soulScript_Api.model.JournalEntry;
import com.dev.soulScript_Api.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JournalEntryMapper {

    // Convert Entity to Response DTO
    public static JournalEntryResponseDTO toResponse(JournalEntry journal) {
        return JournalEntryResponseDTO.builder()
                .id(journal.getId())
                .title(journal.getTitle())
                .content(journal.getContent())
                .createdAt(journal.getCreatedAt())
                .username(journal.getUser().getUsername())
                .userFullName(journal.getUser().getFullName())
                .build();
    }

    // Convert Request DTO to Entity
    public static JournalEntry toEntity(JournalEntryRequestDTO dto, User user) {
        return JournalEntry.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now()) // You can also use @PrePersist in entity
                .user(user)
                .build();
    }
}
