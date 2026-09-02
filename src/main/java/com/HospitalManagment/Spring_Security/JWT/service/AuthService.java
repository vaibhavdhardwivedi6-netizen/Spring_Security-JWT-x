package com.HospitalManagment.Spring_Security.JWT.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HospitalManagment.Spring_Security.JWT.Config.jwtUtils;
import com.HospitalManagment.Spring_Security.JWT.DTO.JwtResponse;
import com.HospitalManagment.Spring_Security.JWT.DTO.LoginResquest;
import com.HospitalManagment.Spring_Security.JWT.DTO.RegisterRequest;
import com.HospitalManagment.Spring_Security.JWT.Repository.UserRepository;
import com.HospitalManagment.Spring_Security.JWT.entity.Roles;
import com.HospitalManagment.Spring_Security.JWT.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final jwtUtils jwt;

	public String register(RegisterRequest request) {

		if (userRepository.findByUsername(request.getUsername()) != null) {
			throw new RuntimeException("ERROR: Username is Already Taken");
		}
		UserEntity user = new UserEntity();

		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Roles.Role_USER);

		userRepository.save(user);

		return "User registered successfully!";
	}

	public JwtResponse authenticateUser(LoginResquest loginRequest) {
		log.info("Yes the auth service is called for login request");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		log.info("After the authentiation manager....");
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwt.generateJwtToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		UserEntity user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

		return JwtResponse.builder().token(token).type("Bearer").username(user.getUsername()).email(user.getEmail())
				.roles(roles).build();
	}
}