package com.oguerrero.userservice.mapper;

import com.oguerrero.userservice.dto.UserRegisterDTO;
import com.oguerrero.userservice.entity.User;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class UserRegisterMapper {

    private final ModelMapper modelMapper;

    public UserRegisterMapper() {
        this.modelMapper = new ModelMapper();
    }

    public User toEntity(UserRegisterDTO userRegisterDTO) {
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(null);

        return user;
    }
}
