package com.dev.soulScript_Api.service;

import com.dev.soulScript_Api.model.JournalEntry;
import com.dev.soulScript_Api.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface JournalService{
    List<JournalEntry> getAllEntries();
    List<JournalEntry> getEntriesByUser(Long userId);
    Optional<JournalEntry> getEntryById(Long id);
    JournalEntry saveEntry(JournalEntry entry);
    void deleteEntry(Long id);
}

