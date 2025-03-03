package com.dev.soulScript_Api.service.impl;

import com.dev.soulScript_Api.model.JournalEntry;
import com.dev.soulScript_Api.repository.JournalRepository;
import com.dev.soulScript_Api.service.JournalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;


    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }

    public List<JournalEntry> getEntriesByUser(Long userId) {
        return journalRepository.findByUserId(userId);
    }

    public Optional<JournalEntry> getEntryById(Long id) {
        return journalRepository.findById(id);
    }

    public JournalEntry saveEntry(JournalEntry entry) {
        return journalRepository.save(entry);
    }

    public void deleteEntry(Long id) {
        journalRepository.deleteById(id);
    }
}
