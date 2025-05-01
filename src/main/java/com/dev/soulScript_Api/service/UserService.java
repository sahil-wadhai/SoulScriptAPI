package com.dev.soulScript_Api.service;


import com.dev.soulScript_Api.dto.UserRequestDTO;
import com.dev.soulScript_Api.dto.UserResponseDTO;
import com.dev.soulScript_Api.model.User;

import java.util.List;

public interface UserService{
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserByUsername(String username);

    UserResponseDTO saveUser(UserRequestDTO user);

    void deleteUser(String username);
}
