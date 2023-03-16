package com.oguerrero.userservice.controller;

import com.oguerrero.userservice.Utils.Utils;
import com.oguerrero.userservice.dto.UserInfoDTO;
import com.oguerrero.userservice.dto.UserRegisterDTO;
import com.oguerrero.userservice.entity.User;
import com.oguerrero.userservice.mapper.UserInfoMapper;
import com.oguerrero.userservice.mapper.UserRegisterMapper;
import com.oguerrero.userservice.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable Integer id) {
        User user = userService.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UserInfoMapper().toDTO(user));
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody UserRegisterDTO user) {
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmail().isEmpty()
                || user.getPassword().isEmpty() || user.getVerifyPassword().isEmpty() || user.getPhoneNumber().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (!Objects.equals(user.getPassword(), user.getVerifyPassword())) {
            return ResponseEntity.badRequest().build();
        }

        String firstName = Utils.format(user.getFirstName());
        String lastName = Utils.format(user.getLastName());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        if (Utils.invalidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        if (Utils.invalidPhoneNumber(user.getPhoneNumber())) {
            return ResponseEntity.badRequest().build();
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(encryptedPassword);

        User saveUser = new UserRegisterMapper().toEntity(user);

        userService.create(saveUser);
        return ResponseEntity.ok().build();
    }
}
