package com.warehouse.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.warehouse.domain.productos.Unidad;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.UnidadRepository;


import jakarta.validation.Valid;

@RestController
public class UnidadProductoController {

	@Autowired
	private UnidadRepository unidadRepository;

	protected void verifyUnidad(Long unidadId) throws ResourceNotFoundException {
		Optional<Unidad> unidad = unidadRepository.findById(unidadId);
	if(unidad.isPresent() == false) {
	throw new ResourceNotFoundException("unidad con  id No." + unidadId + " NO ENCONTRADO");
	}
	}

	 

	// Recuperar todos los equipos	OK
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	@GetMapping("/unidades")
	public ResponseEntity<Iterable<Unidad>> getAllUnidad() {
	Iterable<Unidad> allUnidades = unidadRepository.findAll();
	return new ResponseEntity<>(allUnidades, HttpStatus.OK);
	}


	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/unidadesXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Unidad>> getAllProductosByDescripcion(@RequestParam String descripcion) {
		Iterable<Unidad> allEstados = unidadRepository.findAllByDescripcion(descripcion) ;
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}

	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

	@RequestMapping(value="/unidads/{unidadId}", method=RequestMethod.GET)
	public ResponseEntity<?> getEstado(@PathVariable Long unidadId) {
		 
		verifyUnidad(unidadId);
		Optional<Unidad> e = unidadRepository.findById(unidadId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/unidadNuevo")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createUnidad(@Valid @RequestBody Unidad unidad) {
		unidad = unidadRepository.save(unidad);
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(unidad.getIdunidad())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	//Actualizar un unidad OK
	@RequestMapping(value="/unidades/{unidadId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateProducto(@RequestBody Unidad unidad, @PathVariable Long unidadId) {
		verifyUnidad(unidadId);
		unidadRepository.save(unidad);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un unidad OK
	@RequestMapping(value="/unidades/{unidadId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteEstado(@PathVariable Long unidadId) {
		verifyUnidad(unidadId);
		unidadRepository.deleteById(unidadId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
