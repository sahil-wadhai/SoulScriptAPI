package com.dev.soulScript_Api.service.impl;

import com.dev.soulScript_Api.dto.JournalEntryRequestDTO;
import com.dev.soulScript_Api.dto.JournalEntryResponseDTO;
import com.dev.soulScript_Api.exception.JournalEntryNotFoundException;
import com.dev.soulScript_Api.exception.UserNotFoundException;
import com.dev.soulScript_Api.mapper.JournalEntryMapper;
import com.dev.soulScript_Api.model.JournalEntry;
import com.dev.soulScript_Api.model.User;
import com.dev.soulScript_Api.repository.JournalRepository;
import com.dev.soulScript_Api.repository.UserRepository;
import com.dev.soulScript_Api.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    private final UserRepository userRepository;

    @Autowired
    public JournalServiceImpl(JournalRepository journalRepository, UserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }

    public List<JournalEntryResponseDTO> getAllEntries() {
        List<JournalEntry> entries = journalRepository.findAll();
        return entries.stream().map((entry) -> JournalEntryMapper.toResponse(entry)).toList();
    }

    public List<JournalEntryResponseDTO> getEntriesByUser(String username){
        List<JournalEntry> entries = journalRepository.findByUserUsername(username);
        return entries.stream().map((entry) -> JournalEntryMapper.toResponse(entry)).toList();
    }

    public JournalEntryResponseDTO getEntryById(Long id){
        Optional<JournalEntry> entry = journalRepository.findById(id);
        if(entry.isEmpty()) throw new JournalEntryNotFoundException("Journal Entry for id "+id+" not found.");

        return JournalEntryMapper.toResponse(entry.get());
    }

    public JournalEntryResponseDTO saveEntry(JournalEntryRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UserNotFoundException("User not found for given username");

        JournalEntry journal = JournalEntryMapper.toEntity(request,user.get());
        return JournalEntryMapper.toResponse(journalRepository.save(journal));
    }

    public void deleteEntry(Long id) {
        if(!journalRepository.existsById(id)){
            throw new JournalEntryNotFoundException("Journal for id "+id+" not found");
        }
        journalRepository.deleteById(id);
    }
}
