package com.warehouse.controller;


import java.net.URI;
//import java.net.http.HttpHeaders;
import java.util.Optional;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;


import com.warehouse.domain.PrestamoEquipo;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.PrestamoRepository;

import jakarta.validation.Valid;

@RestController
public class PrestamoController {

@Autowired
private PrestamoRepository prestamoRepository;

protected void verifyPrestamo(Long prestamoId) throws ResourceNotFoundException {
	Optional<PrestamoEquipo> prestamo = prestamoRepository.findById(prestamoId);
if(prestamo.isPresent() == false) {
throw new ResourceNotFoundException("Prestamo con  id No." + prestamoId + " no encontrado");
}
}

 

// Recuperar todos los prestamos	OK
@RequestMapping(value="/equipos/{equipoId}/prestamos", method=RequestMethod.GET)
public ResponseEntity<Iterable<PrestamoEquipo>> getAllPrestamos() {
Iterable<PrestamoEquipo> allPrestamos = prestamoRepository.findAll();
return new ResponseEntity<>(allPrestamos, HttpStatus.OK);
}


//Recuperar un prestamo en particular	OK
@RequestMapping(value="/equipos/{equipoId}/prestamos/{prestamoId}", method=RequestMethod.GET)
public ResponseEntity<?> getPrestamo(@PathVariable Long prestamoId) {
	 
	verifyPrestamo(prestamoId);
	Optional<PrestamoEquipo> e = prestamoRepository.findById(prestamoId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo prestamo
@RequestMapping(value="/equipos/{equipoId}/prestamos", method=RequestMethod.POST)
public ResponseEntity<?> createPrestamo(@Valid @RequestBody PrestamoEquipo prestamo) {
prestamo = prestamoRepository.save(prestamo);
// Set the location header for the newly created resource
HttpHeaders responseHeaders = new HttpHeaders();
URI newPrestamoUri = ServletUriComponentsBuilder
.fromCurrentRequest()
.path("/{id}")
.buildAndExpand(prestamo.getId())
.toUri();
responseHeaders.setLocation(newPrestamoUri);
return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un prestamo OK
@RequestMapping(value="/equipos/{equipoId}/prestamos/{prestamoId}", method=RequestMethod.PUT)
public ResponseEntity<?> updatePrestamo(@RequestBody PrestamoEquipo prestamo, @PathVariable Long prestamoId) {
	verifyPrestamo(prestamoId);
	prestamoRepository.save(prestamo);
return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un prestamo OK
@RequestMapping(value="/equipos/{equipoId}/prestamos/{prestamoId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deletePrestamo(@PathVariable Long prestamoId) {
	verifyPrestamo(prestamoId);
	prestamoRepository.deleteById(prestamoId);
return new ResponseEntity<>(HttpStatus.OK);
}

}