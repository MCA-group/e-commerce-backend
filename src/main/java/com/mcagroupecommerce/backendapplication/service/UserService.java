package com.mcagroupecommerce.backendapplication.service;

import com.mcagroupecommerce.backendapplication.exception.UserException;
import com.mcagroupecommerce.backendapplication.model.User;
import org.springframework.context.annotation.Bean;


public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
