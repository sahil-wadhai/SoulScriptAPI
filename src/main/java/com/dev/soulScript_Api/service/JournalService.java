package com.dev.soulScript_Api.service;

import com.dev.soulScript_Api.dto.JournalEntryRequestDTO;
import com.dev.soulScript_Api.dto.JournalEntryResponseDTO;
import com.dev.soulScript_Api.model.JournalEntry;
import com.dev.soulScript_Api.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface JournalService{
    List<JournalEntryResponseDTO> getAllEntries();
    List<JournalEntryResponseDTO> getEntriesByUser(String username);
    JournalEntryResponseDTO getEntryById(Long id);
    JournalEntryResponseDTO saveEntry(JournalEntryRequestDTO entry, String username);
    void deleteEntry(Long id);
}

