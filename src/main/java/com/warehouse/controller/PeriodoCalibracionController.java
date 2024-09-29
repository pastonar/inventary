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


import com.warehouse.domain.PeriodoCalibracion;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.PeriodoCalibracionRepository;

import jakarta.validation.Valid;

@RestController
public class PeriodoCalibracionController {

@Autowired
private PeriodoCalibracionRepository periodoCalibracionRepository;

protected void verifyPeriodoCalibracion(Long periodoCalibracionId) throws ResourceNotFoundException {
	Optional<PeriodoCalibracion> periodoCalibracion = periodoCalibracionRepository.findById(periodoCalibracionId);
if(periodoCalibracion.isPresent() == false) {
throw new ResourceNotFoundException("PeriodoCalibracion con  id No." + periodoCalibracionId + " no encontrado");
}
}

 

// Recuperar todos los periodoCalibracions	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/periodoCalibracion", method=RequestMethod.GET)
public ResponseEntity<Iterable<PeriodoCalibracion>> getAllPeriodoCalibracions() {
Iterable<PeriodoCalibracion> allPeriodoCalibracions = periodoCalibracionRepository.findAll();
return new ResponseEntity<>(allPeriodoCalibracions, HttpStatus.OK);
}


//Recuperar periodoCalibracion por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/periodoCalibracion1", method=RequestMethod.GET)
public ResponseEntity<Iterable<PeriodoCalibracion>> getAllPeriodoCalibracionsBySerial(@RequestParam String descripcion) {
	Iterable<PeriodoCalibracion> allPeriodoCalibracion = periodoCalibracionRepository.findAllBydescripcion(descripcion) ;
return new ResponseEntity<>(allPeriodoCalibracion, HttpStatus.OK);
}

//Recuperar un periodoCalibracion en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

@RequestMapping(value="/periodoCalibracion/{periodoCalibracionId}", method=RequestMethod.GET)
public ResponseEntity<?> getPeriodoCalibracion(@PathVariable Long periodoCalibracionId) {
	 
	verifyPeriodoCalibracion(periodoCalibracionId);
	Optional<PeriodoCalibracion> e = periodoCalibracionRepository.findById(periodoCalibracionId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo periodoCalibracion
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/periodoCalibracion")
//@RequestMapping(value="/periodoCalibracions", method=RequestMethod.POST)
public ResponseEntity<?> createPeriodoCalibracion(@Valid @RequestBody PeriodoCalibracion periodoCalibracion) {
	periodoCalibracion = periodoCalibracionRepository.save(periodoCalibracion);
	// Set the location header for the newly created resource
	HttpHeaders responseHeaders = new HttpHeaders();
	URI newPeriodoCalibracionUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(periodoCalibracion.getId())
			.toUri();
	responseHeaders.setLocation(newPeriodoCalibracionUri);
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un periodoCalibracion OK
@RequestMapping(value="/periodoCalibracion/{periodoCalibracionId}", method=RequestMethod.PUT)
public ResponseEntity<?> updatePeriodoCalibracion(@RequestBody PeriodoCalibracion periodoCalibracion, @PathVariable Long periodoCalibracionId) {
	verifyPeriodoCalibracion(periodoCalibracionId);
	periodoCalibracionRepository.save(periodoCalibracion);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un periodoCalibracion OK
@RequestMapping(value="/periodoCalibracion/{periodoCalibracionId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deletePeriodoCalibracion(@PathVariable Long periodoCalibracionId) {
	verifyPeriodoCalibracion(periodoCalibracionId);
	periodoCalibracionRepository.deleteById(periodoCalibracionId);
return new ResponseEntity<>(HttpStatus.OK);
}

}