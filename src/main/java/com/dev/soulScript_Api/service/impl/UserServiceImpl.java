package com.dev.soulScript_Api.service.impl;

import com.dev.soulScript_Api.dto.UserRequestDTO;
import com.dev.soulScript_Api.dto.UserResponseDTO;
import com.dev.soulScript_Api.exception.EmailAlreadyExistsException;
import com.dev.soulScript_Api.exception.UserNotFoundException;
import com.dev.soulScript_Api.exception.UsernameAlreadyExistsException;
import com.dev.soulScript_Api.mapper.UserMapper;
import com.dev.soulScript_Api.model.User;
import com.dev.soulScript_Api.repository.UserRepository;
import com.dev.soulScript_Api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserMapper.toResponse(user))
                .toList(); // Java 16+ or use .collect(Collectors.toList())
    }


    public UserResponseDTO getUserById(Long id) {
        return UserMapper.toResponse(userRepository.findById(id).orElseThrow(()->{
            return new UserNotFoundException("User with ID " + id + " not found");
        }));
    }

    public UserResponseDTO getUserByUsername(String username) {
        return UserMapper.toResponse(userRepository.findByUsername(username).orElseThrow(()->{
            return new UserNotFoundException("User with username :" + username + " not found");
        }));
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequest) {
        userRequest.setPassword( passwordEncoder.encode(userRequest.getPassword()) ) ;
        User user = UserMapper.toEntity(userRequest);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' is already taken");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + user.getEmail() + "' is already registered");
        }
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(String username) {
        long deletedCount = userRepository.deleteByUsername(username);
        if (deletedCount == 0) {
            throw new UserNotFoundException("User with username " + username + " not found");
        }
    }
}


/*
    Even if Spring applies @Transactional internally for some methods:
    You should explicitly annotate service methods performing write operations —
    it makes the code behavior clear, and gives you more control (e.g., for rollback, propagation, isolation level).
*/
