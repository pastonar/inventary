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

import com.warehouse.domain.productos.Presentacion;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.PresentacionRepository;


import jakarta.validation.Valid;

@RestController
public class PresentacionProductoController {

	@Autowired
	private PresentacionRepository presentacionRepository;

	protected void verifyPresentacion(Long presentacionId) throws ResourceNotFoundException {
		Optional<Presentacion> presentacion = presentacionRepository.findById(presentacionId);
	if(presentacion.isPresent() == false) {
	throw new ResourceNotFoundException("presentacion con  id No." + presentacionId + " NO ENCONTRADO");
	}
	}

	 

	// Recuperar todos los equipos	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/presentaciones", method=RequestMethod.GET)
	//@GetMapping("/presentaciones")
	public ResponseEntity<Iterable<Presentacion>> getAllPresentacion() {
	Iterable<Presentacion> allPresentaciones = presentacionRepository.findAll();
	return new ResponseEntity<>(allPresentaciones, HttpStatus.OK);
	}


	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/presentacionesXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Presentacion>> getAllProductosByDescripcion(@RequestParam String descripcion) {
		Iterable<Presentacion> allEstados = presentacionRepository.findAllByDescripcion(descripcion) ;
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}

	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

	@RequestMapping(value="/presentacions/{presentacionId}", method=RequestMethod.GET)
	public ResponseEntity<?> getEstado(@PathVariable Long presentacionId) {
		 
		verifyPresentacion(presentacionId);
		Optional<Presentacion> e = presentacionRepository.findById(presentacionId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/presentacionNuevo")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createPresentacion(@Valid @RequestBody Presentacion presentacion) {
		presentacion = presentacionRepository.save(presentacion);
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(presentacion.getIdpresentacion())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	//Actualizar un presentacion OK
	@RequestMapping(value="/presentaciones/{presentacionId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateProducto(@RequestBody Presentacion presentacion, @PathVariable Long presentacionId) {
		verifyPresentacion(presentacionId);
		presentacionRepository.save(presentacion);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un presentacion OK
	@RequestMapping(value="/presentaciones/{presentacionId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteEstado(@PathVariable Long presentacionId) {
		verifyPresentacion(presentacionId);
		presentacionRepository.deleteById(presentacionId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
