package com.getir.readingisgood.rest.security.service;

import com.getir.readingisgood.rest.security.configuration.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final AuthenticationManager authenticationManager;
  private final FakeUserDetailsService fakeUserDetailsService;
  private final JwtUtil jwtUtil;

  @Override
  public String authenticate(String username, String password) {

    /* we assume username-password are ok.*/
    Authentication authenticate =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

    UserDetails userDetails = fakeUserDetailsService.loadUserByUsername(username);

    return jwtUtil.generateToken(userDetails);
  }
}
