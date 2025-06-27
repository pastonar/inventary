package com.warehouse.controller;


import java.net.URI;
import java.util.ArrayList;
//import java.net.http.HttpHeaders;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


import com.warehouse.domain.Equipo;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.EquipoRepository;

import jakarta.validation.Valid;

@RestController
public class EquipoController {

@Autowired
private EquipoRepository equipoRepository;

protected void verifyEquipo(Long equipoId) throws ResourceNotFoundException {
	Optional<Equipo> equipo = equipoRepository.findById(equipoId);
if(equipo.isPresent() == false) {
throw new ResourceNotFoundException("Equipo con  id No." + equipoId + " no encontrado");
}
}

// Recuperar todos los equipos	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/equipos", method=RequestMethod.GET)
public ResponseEntity<Iterable<Equipo>> getAllEquipos() {
Iterable<Equipo> allEquipos = equipoRepository.findAll();
return new ResponseEntity<>(allEquipos, HttpStatus.OK);
}


//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/equipos1", method=RequestMethod.GET)
public ResponseEntity<Iterable<Equipo>> getAllEquiposBySerial(@RequestParam String codigointerno) {
	Iterable<Equipo> allEquipos = equipoRepository.findAllBycodigointerno(codigointerno) ;
return new ResponseEntity<>(allEquipos, HttpStatus.OK);
}

//Recuperar un equipo en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

@RequestMapping(value="/equipos/{equipoId}", method=RequestMethod.GET)
public ResponseEntity<?> getEquipo(@PathVariable Long equipoId) {
	 
	verifyEquipo(equipoId);
	Optional<Equipo> e = equipoRepository.findById(equipoId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo equipo
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/equipos")
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
public ResponseEntity<?> createEquipo(@Valid @RequestBody Equipo equipo) {
	equipo = equipoRepository.save(equipo);
	// Set the location header for the newly created resource
	HttpHeaders responseHeaders = new HttpHeaders();
	URI newEquipoUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(equipo.getId())
			.toUri();
	responseHeaders.setLocation(newEquipoUri);
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un equipo OK
@RequestMapping(value="/equipos/{equipoId}", method=RequestMethod.PUT)
public ResponseEntity<?> updateEquipo(@RequestBody Equipo equipo, @PathVariable Long equipoId) {
	verifyEquipo(equipoId);
	equipoRepository.save(equipo);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un equipo OK
@RequestMapping(value="/equipos/{equipoId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteEquipo(@PathVariable Long equipoId) {
	verifyEquipo(equipoId);
	equipoRepository.deleteById(equipoId);
return new ResponseEntity<>(HttpStatus.OK);
}

}