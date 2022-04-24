package com.getir.readingisgood.rest.security.service;

public interface AuthService {
  String authenticate(String username, String password);
}
