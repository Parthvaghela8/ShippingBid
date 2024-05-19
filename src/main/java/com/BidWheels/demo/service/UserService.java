package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.User;
import com.BidWheels.demo.Repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(Math.toIntExact(id))) {
            userRepository.deleteById(Math.toIntExact(id));
            return true;
        }
        return false;
    }
}
