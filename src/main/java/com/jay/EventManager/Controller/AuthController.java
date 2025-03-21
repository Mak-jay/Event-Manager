package com.jay.EventManager.Controller;

import com.jay.EventManager.DTOs.UserDTO;
import com.jay.EventManager.Exceptions.UserNotFoundException;
import com.jay.EventManager.Model.User;
import com.jay.EventManager.Security.JwtUtility;
import com.jay.EventManager.Services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Event Manager")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@Valid @RequestBody User user) throws UserNotFoundException {
        String encodedPass = passwordEncoder.encode(user.getUserPassword());

        user.setUserPassword(encodedPass);

        UserDTO userDTO = userService.registerUser(user);

        String token = jwtUtility.generateToken(userDTO.getEmail());

        return Collections.singletonMap("jwt-token", token);
    }


}
