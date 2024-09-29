package com.warehouse.controller;


import java.net.URI;
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


import com.warehouse.domain.Bodega;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.BodegaRepository;

import jakarta.validation.Valid;

@RestController
public class BodegaController {

@Autowired
private BodegaRepository bodegaRepository;

protected void verifyBodega(Long bodegaId) throws ResourceNotFoundException {
	Optional<Bodega> bodega = bodegaRepository.findById(bodegaId);
if(bodega.isPresent() == false) {
throw new ResourceNotFoundException("Bodega con  id No." + bodegaId + " no encontrado");
}
}

 

// Recuperar todos los bodegas	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/bodegas", method=RequestMethod.GET)
public ResponseEntity<Iterable<Bodega>> getAllBodegas() {
Iterable<Bodega> allBodegas = bodegaRepository.findAll();
return new ResponseEntity<>(allBodegas, HttpStatus.OK);
}


//Recuperar bodega por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/bodegas1", method=RequestMethod.GET)
public ResponseEntity<Iterable<Bodega>> getAllBodegasBySerial(@RequestParam String codigointerno) {
	Iterable<Bodega> allBodegas = bodegaRepository.findAllBydescripcion(codigointerno) ;
return new ResponseEntity<>(allBodegas, HttpStatus.OK);
}

//Recuperar un bodega en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

@RequestMapping(value="/bodegas/{bodegaId}", method=RequestMethod.GET)
public ResponseEntity<?> getBodega(@PathVariable Long bodegaId) {
	 
	verifyBodega(bodegaId);
	Optional<Bodega> e = bodegaRepository.findById(bodegaId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo bodega
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/bodegas")
//@RequestMapping(value="/bodegas", method=RequestMethod.POST)
public ResponseEntity<?> createBodega(@Valid @RequestBody Bodega bodega) {
	bodega = bodegaRepository.save(bodega);
	// Set the location header for the newly created resource
	HttpHeaders responseHeaders = new HttpHeaders();
	URI newBodegaUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(bodega.getId())
			.toUri();
	responseHeaders.setLocation(newBodegaUri);
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un bodega OK
@RequestMapping(value="/bodegas/{bodegaId}", method=RequestMethod.PUT)
public ResponseEntity<?> updateBodega(@RequestBody Bodega bodega, @PathVariable Long bodegaId) {
	verifyBodega(bodegaId);
	bodegaRepository.save(bodega);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un bodega OK
@RequestMapping(value="/bodegas/{bodegaId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteBodega(@PathVariable Long bodegaId) {
	verifyBodega(bodegaId);
	bodegaRepository.deleteById(bodegaId);
return new ResponseEntity<>(HttpStatus.OK);
}

}