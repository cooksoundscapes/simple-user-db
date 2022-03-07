package com.example.basicuserdb.users;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("email already taken.");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("user with id "+id+" doesn't exists.");
        }
        userRepository.deleteById(id);
    } 
    
    @Transactional
    public void updateUser(Long id, String name, String email) 
    {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("user doesn't exists!"));
        if (name != null && 
            name.length() > 0 && 
            !Objects.equals(user.getName(), name) ) 
        {
            user.setName(name);        
        }
        if (email != null && 
            email.length() > 0 && 
            !Objects.equals(user.getEmail(), email) ) 
        {
            Optional<User> userOpt = userRepository.findUserByEmail(email);
            if (userOpt.isPresent()) {
                throw new IllegalStateException("this email has been taken.");
            }
            user.setEmail(email);        
        }

    }
}

