package com.oguerrero.userservice.service;

import com.oguerrero.userservice.dto.UserRegisterDTO;
import com.oguerrero.userservice.entity.User;

public interface IUserService {

    void create(User user);
    User findById(Integer id);
}
