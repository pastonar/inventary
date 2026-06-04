package com.warehouse.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.warehouse.domain.empleados.Empleado;
import com.warehouse.domain.produccion.*;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.ProduccionRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*") 
public class ProduccionController {

	  
	 
	
	
	@Autowired
	private ProduccionRepository produccionRepository;
	
	protected void verifyEquipo(int empleadoId) throws ResourceNotFoundException {
		Optional<Produccion> data = produccionRepository.findById(empleadoId);
	if(data.isPresent() == false) {
	throw new ResourceNotFoundException("Empleado con  id No." + empleadoId + " NO ENCONTRADO");
	}
	}
	
	// Recuperar todos los equipos	OK
	// @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	//@CrossOrigin(origins = "*") 
	@GetMapping("/produccion")
	public ResponseEntity<Iterable<Produccion>> getAllEmpleados() {
	Iterable<Produccion> allEmpleado = produccionRepository.findAll();
	return new ResponseEntity<>(allEmpleado, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/produccionXNit")
	public ResponseEntity<Iterable<Produccion>> getAllproduccionByNit(@RequestParam String nit) {
		//System.out.println(nombreempleado);
		Iterable<Produccion> allEmpleados = produccionRepository.findAllBynit(nit) ;
	return new ResponseEntity<>(allEmpleados, HttpStatus.OK);
	}
	
	
	@GetMapping("/produccionXrazonsocial")
	public ResponseEntity<Iterable<Produccion>> getAllproduccionByRazonSocial(@RequestParam String nombreempleado) {
		System.out.println(nombreempleado);
		Iterable<Produccion> allData = produccionRepository.findAllBynomCompleto(nombreempleado) ;
	return new ResponseEntity<>(allData, HttpStatus.OK);
	}
	/*
	@GetMapping("/produccionXgrupoEmpleado")
	public ResponseEntity<Iterable<Produccion>> getAllproduccionBygrupoEmpleado(@RequestParam int grupo) {

	
	 * Iterable<Produccion> allEmpleados =
	 * produccionRepository.findAllBygrupoEmpleado(grupo); return new
	 * ResponseEntity<>(allEmpleados, HttpStatus.OK); }
	 */
	
	
	
	//Crear un nuevo equipo
	//@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/NuevoDatoProduccion")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createProducto(@Valid @RequestBody Produccion data) {
		HttpHeaders responseHeaders = new HttpHeaders();
		data = produccionRepository.save(data);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(data.getIdProduccion())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	
	
	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@CrossOrigin(origins = "*")
	@GetMapping("/produccion/{idProduccion}")
	//@RequestMapping(value="/productos/{id_producto}", method=RequestMethod.GET)
	public ResponseEntity<?> getEmpleado(@PathVariable int idProduccion) {
	
		Optional<Produccion> data = produccionRepository.findById(idProduccion);
		Produccion datum = new Produccion();
		if(data.isPresent() == true) 
			datum = data.get();
		return new ResponseEntity<> (data, HttpStatus.OK);  
	}
	
	
	//Actualizar un equipo OK
	@PutMapping("/produccion/{idProduccion}")
	@CrossOrigin(origins = "*") 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updaterProduccion(@RequestBody Produccion data, @PathVariable Long idProduccion) {
		
		produccionRepository.save(data);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un equipo OK
	@DeleteMapping(value="/produccion/{idProduccion}")
	public ResponseEntity<?> deleteProduccion(@PathVariable int idProduccion) {
		verifyEquipo(idProduccion);
		produccionRepository.deleteById(idProduccion);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
