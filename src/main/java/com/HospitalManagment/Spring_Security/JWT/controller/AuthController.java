package com.HospitalManagment.Spring_Security.JWT.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagment.Spring_Security.JWT.DTO.JwtResponse;
import com.HospitalManagment.Spring_Security.JWT.DTO.LoginResquest;
import com.HospitalManagment.Spring_Security.JWT.DTO.RegisterRequest;
import com.HospitalManagment.Spring_Security.JWT.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	// Register
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

		return ResponseEntity.ok(authService.register(request));
	}

	// Login
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginResquest request) {

		return ResponseEntity.ok(authService.authenticateUser(request));
	}
}