package com.warehouse.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.dto.AuthenticationRequest;
import com.warehouse.dto.AuthenticationResponse;
import com.warehouse.jwt.JwtUtils;

import com.warehouse.service.UserDetailsServiceImpl;

@RestController
public class LoginController {

	
	@Autowired 
	  private AuthenticationManager authenticationManager;
	  
	  @Autowired 
	  private UserDetailsServiceImpl userDetailsService;
	
	  @Autowired 
	  private JwtUtils jwtService;
	  

	  //@RequestMapping(value="/unidadesXdescripcion", method=RequestMethod.GET)
	  @PostMapping("/login") 		
	  public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) { 
		  try {
			  
			 
					authenticationManager.authenticate(
				  new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()) );
					 System.out.println(request.getUsername());
		  } catch (org.springframework.security.core.AuthenticationException e) {
				
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
			} 
		  
		
	  final UserDetails userDetails =
	  userDetailsService.loadUserByUsername(request.getUsername()); 
	  final String jwtToken = 
			  jwtService.generateAccesToken(userDetails.getUsername());
	  
	  System.out.println("createToken"+jwtToken);
	  return ResponseEntity.ok(new AuthenticationResponse(jwtToken)); 
	  
	  }
	  
	  @PostMapping("/") 
	  public String docker() { 
		  return "index"; }
	  
	 
}


