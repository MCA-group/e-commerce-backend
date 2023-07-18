package com.mcagroupecommerce.backendapplication.service;

import com.mcagroupecommerce.backendapplication.exception.UserException;
import com.mcagroupecommerce.backendapplication.model.User;
import com.mcagroupecommerce.backendapplication.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImplementation implements UserService{


    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {

        this.userRepository=userRepository;

    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user=userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with id "+userId);
    }


}
