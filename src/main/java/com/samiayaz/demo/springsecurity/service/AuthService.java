package com.samiayaz.demo.springsecurity.service;

import com.samiayaz.demo.springsecurity.dto.AuthRequest;
import com.samiayaz.demo.springsecurity.dto.AuthResponse;
import com.samiayaz.demo.springsecurity.dto.RegisterRequest;
import com.samiayaz.demo.springsecurity.model.Role;
import com.samiayaz.demo.springsecurity.model.User;
import com.samiayaz.demo.springsecurity.repository.UserRepository;
import com.samiayaz.demo.springsecurity.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.name())
                .surname(request.surname())
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        User user = userRepository.findByUsername(request.username())
                .orElseThrow();

        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

}
