package com.dev.soulScript_Api.controller;

import com.dev.soulScript_Api.dto.JournalEntryRequestDTO;
import com.dev.soulScript_Api.dto.JournalEntryResponseDTO;
import com.dev.soulScript_Api.model.JournalEntry;
import com.dev.soulScript_Api.service.JournalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journal")
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public List<JournalEntryResponseDTO> getAllEntries() {
        return journalService.getAllEntries();
    }

    @GetMapping("/user/{username}")
    public List<JournalEntryResponseDTO> getEntriesByUser(@PathVariable String username) {
        return journalService.getEntriesByUser(username);
    }

    @GetMapping("/{id}")
    public JournalEntryResponseDTO getEntryById(@PathVariable long id) {
        return journalService.getEntryById(id);
    }

    @PostMapping("/{username}")
    public JournalEntryResponseDTO createEntry(@Valid @RequestBody JournalEntryRequestDTO entry, @PathVariable String username) {
        return journalService.saveEntry(entry,username);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        journalService.deleteEntry(id);
    }
}

