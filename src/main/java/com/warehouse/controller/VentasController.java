package com.warehouse.controller;


import java.net.URI;
import java.util.ArrayList;
//import java.net.http.HttpHeaders;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import com.warehouse.domain.ventas.Ventas;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.VentasRepository;

import jakarta.validation.Valid;

@RestController
public class VentasController {

@Autowired
private VentasRepository ventasRepository;

protected void verifyEquipo(Long ventasId) throws ResourceNotFoundException {
	Optional<Ventas> ventas = ventasRepository.findById(ventasId);
if(ventas.isPresent() == false) {
throw new ResourceNotFoundException("Ventas con  id No." + ventasId + " NO ENCONTRADO");
}
}

 

// Recuperar todos los equipos	OK
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//@RequestMapping(value="/ventass", method=RequestMethod.GET)
@GetMapping("/ventas")
public ResponseEntity<Iterable<Ventas>> getAllVentas() {
Iterable<Ventas> allVentas = ventasRepository.findAll();
return new ResponseEntity<>(allVentas, HttpStatus.OK);
}



//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
/*
 * @CrossOrigin(origins = "*")
 * 
 * @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
 * 
 * @RequestMapping(value="/ventassXmarcaYserial", method=RequestMethod.GET)
 * public ResponseEntity<Iterable<Ventas>>
 * getAllVentassByMarcaYSerial(@RequestParam String marca,@RequestParam String
 * serial) { Iterable<Ventas> allVentass =
 * ventasRepository.findAllByMarcaYSerial(marca,serial) ; return new
 * ResponseEntity<>(allVentass, HttpStatus.OK); }
 */

//Recuperar un equipo en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@GetMapping("/ventas/{ventasId}")
public ResponseEntity<?> getVentas(@PathVariable Long ventasId) {
	 
	verifyEquipo(ventasId);
	Optional<Ventas> e = ventasRepository.findById(ventasId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo equipo
@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/nuevaFactura")

public ResponseEntity<?> createVentas(@Valid @RequestBody Ventas venta) {
		HttpHeaders responseHeaders = new HttpHeaders();
	venta = ventasRepository.save(venta);
	// Set the location header for the newly created resource
	
	URI newVentasUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(venta.getId_factura())
			.toUri();
	responseHeaders.setLocation(newVentasUri);
	
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un equipo OK
@RequestMapping(value="/ventas/{ventasId}", method=RequestMethod.PUT)
public ResponseEntity<?> updateVentas(@RequestBody Ventas ventas, @PathVariable Long ventasId) {
	verifyEquipo(ventasId);
	ventasRepository.save(ventas);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un equipo OK
@RequestMapping(value="/ventas/{ventasId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteVentas(@PathVariable Long ventasId) {
	verifyEquipo(ventasId);
	ventasRepository.deleteById(ventasId);
return new ResponseEntity<>(HttpStatus.OK);
}

}