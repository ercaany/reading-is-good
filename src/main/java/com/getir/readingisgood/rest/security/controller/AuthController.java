package com.getir.readingisgood.rest.security.controller;

import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.common.BaseController;
import com.getir.readingisgood.rest.security.controller.dto.AuthRequest;
import com.getir.readingisgood.rest.security.controller.dto.AuthResponse;
import com.getir.readingisgood.rest.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
  private final AuthService authService;

  @PostMapping("/login")
  public ApiResponse<AuthResponse> login(@RequestBody AuthRequest request) {
    final String jwt = authService.authenticate(request.getUsername(), request.getPassword());

    return respond(new AuthResponse(request.getUsername(), jwt));
  }
}
