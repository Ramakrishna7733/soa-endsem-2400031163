
package com.klef.soa.endsem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.soa.endsem.dto.RegisterRequest;
import com.klef.soa.endsem.dto.LoginRequest;
import com.klef.soa.endsem.dto.AuthResponse;
import com.klef.soa.endsem.entity.UserAccount;
import com.klef.soa.endsem.repository.UserAccountRepository;
import com.klef.soa.endsem.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService
{
    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @Override
    public String register(RegisterRequest request)
    {
        if(repository.existsByUsername(request.getUsername()))
        {
            return "Username already exists";
        }

        String encryptedPassword =
                encoder.encode(request.getPassword());

        UserAccount user = new UserAccount(
                request.getUsername(),
                encryptedPassword
        );

        repository.save(user);

        return "User registered successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request)
    {
        UserAccount user =
                repository.findByUsername(request.getUsername());

        if(user == null ||
           !encoder.matches(request.getPassword(), user.getPassword()))
        {
        	throw new org.springframework.web.server.ResponseStatusException(
        	        org.springframework.http.HttpStatus.UNAUTHORIZED,
        	        "Invalid username or password"
        	);
        }

        String token = jwtUtil.generateToken(user.getUsername());

        AuthResponse response = new AuthResponse();
        response.setToken(token);

        return response;
    }
}
