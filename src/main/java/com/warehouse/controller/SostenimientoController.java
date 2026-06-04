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

import com.warehouse.domain.sostenimiento.Sostenimiento;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.SostenimientoRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*") 
public class SostenimientoController {

	  
	 
	
	
	@Autowired
	private SostenimientoRepository sostenimientoRepository;
	
	protected void verifyEquipo(int empleadoId) throws ResourceNotFoundException {
		Optional<Sostenimiento> data = sostenimientoRepository.findById(empleadoId);
	if(data.isPresent() == false) {
	throw new ResourceNotFoundException("Empleado con  id No." + empleadoId + " NO ENCONTRADO");
	}
	}
	
	// Recuperar todos los equipos	OK
	// @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	//@CrossOrigin(origins = "*") 
	@GetMapping("/sostenimiento")
	public ResponseEntity<Iterable<Sostenimiento>> getAllEmpleados() {
	Iterable<Sostenimiento> allEmpleado = sostenimientoRepository.findAll();
	return new ResponseEntity<>(allEmpleado, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/sostenimientoXNit")
	public ResponseEntity<Iterable<Sostenimiento>> getAllsostenimientoByNit(@RequestParam String nit) {
		//System.out.println(nombreempleado);
		Iterable<Sostenimiento> allEmpleados = sostenimientoRepository.findAllBynit(nit) ;
	return new ResponseEntity<>(allEmpleados, HttpStatus.OK);
	}
	
	
	@GetMapping("/sostenimientoXrazonsocial")
	public ResponseEntity<Iterable<Sostenimiento>> getAllsostenimientoByRazonSocial(@RequestParam String nombreempleado) {
		System.out.println(nombreempleado);
		Iterable<Sostenimiento> allData = sostenimientoRepository.findAllBynomCompleto(nombreempleado) ;
	return new ResponseEntity<>(allData, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/sostenimientoXgrupoEmpleado") public
	 * ResponseEntity<Iterable<Sostenimiento>>
	 * getAllsostenimientoBygrupoEmpleado(@RequestParam int grupo) {
	 * 
	 * Iterable<Sostenimiento> allEmpleados =
	 * sostenimientoRepository.findAllBygrupoEmpleado(grupo); return new
	 * ResponseEntity<>(allEmpleados, HttpStatus.OK); }
	 */
	
	
	
	//Crear un nuevo equipo
	//@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/NuevoDatoSostenimiento")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createProducto(@Valid @RequestBody Sostenimiento data) {
		HttpHeaders responseHeaders = new HttpHeaders();
		data = sostenimientoRepository.save(data);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(data.getIdSostenimiento())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	
	
	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@CrossOrigin(origins = "*")
	@GetMapping("/sostenimiento/{idSostenimiento}")
	//@RequestMapping(value="/productos/{id_producto}", method=RequestMethod.GET)
	public ResponseEntity<?> getEmpleado(@PathVariable int idSostenimiento) {
	
		Optional<Sostenimiento> data = sostenimientoRepository.findById(idSostenimiento);
		Sostenimiento datum = new Sostenimiento();
		if(data.isPresent() == true) 
			datum = data.get();
		return new ResponseEntity<> (data, HttpStatus.OK);  
	}
	
	
	//Actualizar un equipo OK
	@PutMapping("/sostenimiento/{idSostenimiento}")
	@CrossOrigin(origins = "*") 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updaterSostenimiento(@RequestBody Sostenimiento data, @PathVariable Long idSostenimiento) {
		
		sostenimientoRepository.save(data);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un equipo OK
	@DeleteMapping(value="/sostenimiento/{idSostenimiento}")
	public ResponseEntity<?> deleteSostenimiento(@PathVariable int idSostenimiento) {
		verifyEquipo(idSostenimiento);
		sostenimientoRepository.deleteById(idSostenimiento);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
