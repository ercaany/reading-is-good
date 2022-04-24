package com.getir.readingisgood.rest.security.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequest {
  private String username;
  private String password;
}
