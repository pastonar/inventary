package com.warehouse.controller;


import com.warehouse.domain.ERol;
import com.warehouse.domain.RolEntity;
import com.warehouse.domain.UserEntity;
import com.warehouse.dto.CreateUserDTO;
import com.warehouse.repository.UserEntityRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserEntityRepository userRepository;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World Not Secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hello World Secured";
    }

	/*
	 * // Recuperar todos los users OK
	 * 
	 * @GetMapping("/usuarios")
	 * 
	 * public ResponseEntity<Iterable<UserEntity>> getAllusers() {
	 * Iterable<UserEntity> allusers = userRepository.findAll(); return new
	 * ResponseEntity<>(allusers, HttpStatus.OK); }
	 * 
	 * //Recuperar un user en particular OK
	 * 
	 * @RequestMapping(value="/usuarios/{userId}", method=RequestMethod.GET) public
	 * ResponseEntity<?> getuser(@PathVariable Long userId) {
	 * 
	 * //verifyuser(userId); Optional<UserEntity> e =
	 * userRepository.findById(userId); Set<String> roles = new HashSet<String>();
	 * roles.add("ADMIN"); CreateUserDTO createUserDTO = new CreateUserDTO();
	 * 
	 * createUserDTO.setUsername(e.get().getUsername());
	 * createUserDTO.setPassword(e.get().getPassword());
	 * createUserDTO.setRoles(roles); return new ResponseEntity<> (createUserDTO,
	 * HttpStatus.OK); }
	 */
	/*
	 * @PostMapping("/createUser") public ResponseEntity<?>
	 * createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
	 * 
	 * Set<RoleEntity> roles = createUserDTO.getRoles().stream() .map(role ->
	 * RoleEntity.builder() .name(ERole.valueOf(role)) .build())
	 * .collect(Collectors.toSet());
	 * 
	 * UserEntity userEntity = UserEntity.builder()
	 * .username(createUserDTO.getUsername())
	 * .password(passwordEncoder.encode(createUserDTO.getPassword()))
	 * .email(createUserDTO.getEmail()) .roles(roles) .build();
	 * 
	 * userRepository.save(userEntity);
	 * 
	 * return ResponseEntity.ok(userEntity); }
	 */

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));
        return "Se ha borrado el user con id".concat(id);
    }
}
