package com.warehouse.controller;


import java.net.URI;
import java.util.HashSet;
//import java.net.http.HttpHeaders;
import java.util.Optional;
import java.util.Set;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.warehouse.dto.ErrorDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;


import com.warehouse.domain.ERol;
import com.warehouse.domain.RolEntity;
import com.warehouse.domain.UserEntity;
import com.warehouse.dto.UserEntityDto;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.UserEntityRepository;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder


@RestController
public class UserEntityController {

	//private static final Logger logger = LogManager.getLogger(InventarioApplication.class);
	
	@Autowired
private UserEntityRepository userEntityRepository;



/*
 * @Autowired private PasswordEncoder passwordEncoder;
 */
 

//@Bean
// BCryptPasswordEncoder passwordEncoder() {
//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    return bCryptPasswordEncoder;
//}
  




protected void verifyuser(Long userId) throws ResourceNotFoundException {
	Optional<UserEntity> user = userEntityRepository.findById(userId);
	//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	//passwordEncoder.encode("12345");
	if(user.isEmpty()) {
throw new ResourceNotFoundException("user con  id No." + userId + " no encontrado");
}
}

 

// Recuperar todos los users	OK
@GetMapping("/usuarios")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@ApiOperation(value = "Recupera todos los usuarios", response=UserEntity.class, responseContainer="List")

public ResponseEntity<Iterable<UserEntity>> getAllusers() {
Iterable<UserEntity> allusers = userEntityRepository.findAll();
return new ResponseEntity<>(allusers, HttpStatus.OK);
}


//Recuperar un user en particular	OK
@CrossOrigin(origins = "*") 
@GetMapping("/usuarios/{Id}")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@ApiOperation(value = "Recupera unvusuario en particular", response=UserEntity.class)
@ApiResponses(value = {@ApiResponse(code=200, message="", response=UserEntity.class),  
@ApiResponse(code=404, message="Imposible recuperar el usuario", response=ErrorDetail.class) } )


public ResponseEntity<?> getuser(@PathVariable Long userId) {
	 
	verifyuser(userId);
	Optional<UserEntity> e = userEntityRepository.findById(userId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo user
@PostMapping("/usuarios")
public ResponseEntity<?> createuser(@Valid @RequestBody UserEntityDto user) {
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	// PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	String pwd = passwordEncoder.encode(user.getPassword());
	
	Set<RolEntity> set = new HashSet<RolEntity>();
	Set<String> roles = user.getRoles();
	for(String rol : roles)
		 {
		 set.add(new RolEntity(ERol.valueOf(rol)));
		 }
	UserEntity userEntity = new UserEntity(
											user.getUsername(),
											pwd,
											user.getTipoid(),
											user.getNumid(),
											user.getFirstName(),
											user.getLastName(),
											set);
	
	userEntity = userEntityRepository.save(userEntity);
	// Set the location header for the newly created resource
	HttpHeaders responseHeaders = new HttpHeaders();
	URI newuserUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(userEntity.getId())
			.toUri();
	responseHeaders.setLocation(newuserUri);
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un user OK
@RequestMapping(value="/usuarios/{userId}", method=RequestMethod.PUT)

public ResponseEntity<?> updateUser(@RequestBody UserEntity  user, @PathVariable Long userId) {
	verifyuser(userId);
	userEntityRepository.save(user);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un user OK
@RequestMapping(value="/usuarios/{userId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteuser(@PathVariable Long userId) {
	verifyuser(userId);
	userEntityRepository.deleteById(userId);
return new ResponseEntity<>(HttpStatus.OK);
}




}