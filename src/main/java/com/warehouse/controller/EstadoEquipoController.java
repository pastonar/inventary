package com.warehouse.controller;


import java.net.URI;
import java.util.ArrayList;
//import java.net.http.HttpHeaders;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;


import com.warehouse.domain.EstadoEquipo;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.EstadoEquipoRepository;

import jakarta.validation.Valid;

@RestController
public class EstadoEquipoController {

@Autowired
private EstadoEquipoRepository estadoEquipoRepository;

protected void verifyEstadoEquipo(Long estadoEquipoId) throws ResourceNotFoundException {
	Optional<EstadoEquipo> estadoEquipo = estadoEquipoRepository.findById(estadoEquipoId);
if(estadoEquipo.isPresent() == false) {
throw new ResourceNotFoundException("EstadoEquipo con  id No." + estadoEquipoId + " no encontrado");
}
}

 

// Recuperar todos los estadoEquipos	OK
@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/estadoEquipo", method=RequestMethod.GET)
public ResponseEntity<Iterable<EstadoEquipo>> getAllEstadoEquipos() {
Iterable<EstadoEquipo> allEstadoEquipos = estadoEquipoRepository.findAll();
return new ResponseEntity<>(allEstadoEquipos, HttpStatus.OK);
}


//Recuperar estadoEquipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/estadoEquipo/listparam", method=RequestMethod.GET)
public ResponseEntity<Iterable<EstadoEquipo>> getAllEstadoEquiposBySerial(@RequestParam String descripcion) {
	Iterable<EstadoEquipo> allEstadoEquipos = estadoEquipoRepository.findAllByestadoEquipo(descripcion) ;
return new ResponseEntity<>(allEstadoEquipos, HttpStatus.OK);
}

//Recuperar un estadoEquipo en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

@RequestMapping(value="/estadoEquipo/{estadoEquipoId}", method=RequestMethod.GET)
public ResponseEntity<?> getEstadoEquipo(@PathVariable Long estadoEquipoId) {
	 
	verifyEstadoEquipo(estadoEquipoId);
	Optional<EstadoEquipo> e = estadoEquipoRepository.findById(estadoEquipoId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo estadoEquipo
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/estadoEquipo")
//@RequestMapping(value="/estadoEquipos", method=RequestMethod.POST)
public ResponseEntity<?> createEstadoEquipo(@Valid @RequestBody EstadoEquipo estadoEquipo) {
	estadoEquipo = estadoEquipoRepository.save(estadoEquipo);
	// Set the location header for the newly created resource
	HttpHeaders responseHeaders = new HttpHeaders();
	URI newEstadoEquipoUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(estadoEquipo.getId())
			.toUri();
	responseHeaders.setLocation(newEstadoEquipoUri);
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un estadoEquipo OK
@RequestMapping(value="/estadoEquipo/{estadoEquipoId}", method=RequestMethod.PUT)
public ResponseEntity<?> updateEstadoEquipo(@RequestBody EstadoEquipo estadoEquipo, @PathVariable Long estadoEquipoId) {
	verifyEstadoEquipo(estadoEquipoId);
	estadoEquipoRepository.save(estadoEquipo);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un estadoEquipo OK
@RequestMapping(value="/estadoEquipo/{estadoEquipoId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteEstadoEquipo(@PathVariable Long estadoEquipoId) {
	verifyEstadoEquipo(estadoEquipoId);
	estadoEquipoRepository.deleteById(estadoEquipoId);
return new ResponseEntity<>(HttpStatus.OK);
}

}