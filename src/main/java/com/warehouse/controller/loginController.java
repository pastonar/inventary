package com.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.dto.AuthenticationRequest;
import com.warehouse.dto.AuthenticationResponse;
import com.warehouse.service.JwtService;
import com.warehouse.service.UserDetailsServiceImpl;

@RestController
public class LoginController {

	
	  @Autowired private AuthenticationManager authenticationManager;
	  
	  @Autowired private UserDetailsServiceImpl userDetailsService;
	  
	  @Autowired private JwtService jwtService;
	  
	  @PostMapping("/login") public ResponseEntity<AuthenticationResponse>
	  createToken(@RequestBody AuthenticationRequest request) { 
	try { // Autenticar las credenciales del usuario manualmente 
		authenticationManager.authenticate(
	  new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()) ); } 
	  catch (AuthenticationException e) { 
	  // Retornar una respuesta 401 si las credenciales no son válidas 
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
	  
	  final UserDetails userDetails =
	  userDetailsService.loadUserByUsername(request.getUsername()); final String
	  jwtToken = jwtService.generateToken(userDetails);
	  
	  return ResponseEntity.ok(new AuthenticationResponse(jwtToken)); }
	  
	  @PostMapping("/") public String docker() { return "index"; }
	  
	 
}
