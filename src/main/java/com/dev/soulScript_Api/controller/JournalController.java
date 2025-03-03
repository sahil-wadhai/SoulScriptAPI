package com.dev.soulScript_Api.controller;

import com.dev.soulScript_Api.model.JournalEntry;
import com.dev.soulScript_Api.service.JournalService;
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
    public List<JournalEntry> getAllEntries() {
        return journalService.getAllEntries();
    }

    @GetMapping("/user/{userId}")
    public List<JournalEntry> getEntriesByUser(@PathVariable Long userId) {
        return journalService.getEntriesByUser(userId);
    }

    @GetMapping("/{id}")
    public Optional<JournalEntry> getEntryById(@PathVariable Long id) {
        return journalService.getEntryById(id);
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {
        return journalService.saveEntry(entry);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        journalService.deleteEntry(id);
    }
}

