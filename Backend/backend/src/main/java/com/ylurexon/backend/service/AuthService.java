package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.LoginRequest;
import com.ylurexon.backend.dto.LoginResponse;
import com.ylurexon.backend.dto.RegisterRequest;
import com.ylurexon.backend.dto.RegisterResponse;
import com.ylurexon.backend.entity.User;
import com.ylurexon.backend.exception.DuplicateResourceException;
import com.ylurexon.backend.repository.UserRepository;
import com.ylurexon.backend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTER
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "Email already registered"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(hashedPassword);

        user.setRole("USER");

        userRepository.save(user);

        return new RegisterResponse(
                "User registered successfully"
        );
    }

    // FIND USER FOR LOGIN
    public User findUserForLogin(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        )
                );
    }

    // VERIFY PASSWORD
    public boolean verifyPassword(
            String rawPassword,
            String encodedPassword) {

        return passwordEncoder.matches(
                rawPassword,
                encodedPassword
        );
    }

    // LOGIN
    public LoginResponse login(LoginRequest request) {

        User user = findUserForLogin(
                request.getEmail()
        );

        boolean passwordMatches = verifyPassword(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                "Login successful",
                token
        );
    }
}