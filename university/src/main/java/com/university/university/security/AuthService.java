package com.university.university.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public String registerTutor(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));

        Role tutorRole = roleRepository.findByName("ROLE_TUTORS")
                .orElseThrow(() -> new RuntimeException("ROLE_TUTORS not found in DB"));
        u.setRoles(Set.of(tutorRole));
        userRepository.save(u);

        return jwtService.generateToken(
                u.getUsername(),
                u.getAuthorities().stream().map(a -> a.getAuthority()).toList()
        );
    }

    public String login(AuthRequest req) {
        var authentication = new UsernamePasswordAuthenticationToken(
                req.getUsername(), req.getPassword());
        authManager.authenticate(authentication);

        User u = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> roles = u.getAuthorities().stream()
                .map(a -> a.getAuthority()).toList();

        return jwtService.generateToken(u.getUsername(), roles);
    }
}

