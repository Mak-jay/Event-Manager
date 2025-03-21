package com.jay.EventManager.Services;

import com.jay.EventManager.DTOs.EventDTO;
import com.jay.EventManager.DTOs.UserDTO;
import com.jay.EventManager.Exceptions.EventNotFoundException;
import com.jay.EventManager.Exceptions.UserNotFoundException;
import com.jay.EventManager.Model.User;
import com.jay.EventManager.Repositories.EventRepo;
import com.jay.EventManager.Repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO registerUser(User user) throws UserNotFoundException {
        Optional<User> myUser = userRepo.findById(user.getUserEmail());

        if (myUser.isPresent()) {
            throw new UserNotFoundException("User already exists with the email id: " + user.getUserEmail());
        }

        User registerUser = userRepo.save(user);
        UserDTO registerUserDTO = modelMapper.map(registerUser, UserDTO.class);

        return registerUserDTO;
    }

    @Override
    public UserDTO updateUser(User user) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<EventDTO> getEventsByType(String eventType) throws EventNotFoundException {
        return List.of();
    }
}
