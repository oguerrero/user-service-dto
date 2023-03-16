package com.oguerrero.userservice.mapper;

import com.oguerrero.userservice.dto.UserInfoDTO;
import com.oguerrero.userservice.entity.User;
import org.modelmapper.ModelMapper;

public class UserInfoMapper {

    private final ModelMapper modelMapper;

    public UserInfoMapper() {
        this.modelMapper = new ModelMapper();
    }

    public UserInfoDTO toDTO(User user) {
        return modelMapper.map(user, UserInfoDTO.class);
    }
}
