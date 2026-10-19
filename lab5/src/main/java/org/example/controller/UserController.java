package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(r -> UserDto.builder()
                        .name(r.getName())
                        .email(r.getEmail())
                        .phone(r.getPhone())
                        .telegramChatId(r.getTelegramChatId())
                        .deviceToken(r.getDeviceToken())
                        .createdAt(r.getCreatedAt())
                        .build())
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User r = userService.getUserById(id);
        return UserDto.builder()
                .name(r.getName())
                .email(r.getEmail())
                .phone(r.getPhone())
                .telegramChatId(r.getTelegramChatId())
                .deviceToken(r.getDeviceToken())
                .createdAt(r.getCreatedAt())
                .build();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return String.format("Пользователь %s удален", id);
    }
}
