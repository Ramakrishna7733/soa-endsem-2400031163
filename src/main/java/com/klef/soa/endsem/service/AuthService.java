
package com.klef.soa.endsem.service;

import com.klef.soa.endsem.dto.RegisterRequest;
import com.klef.soa.endsem.dto.LoginRequest;
import com.klef.soa.endsem.dto.AuthResponse;

public interface AuthService
{
    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
