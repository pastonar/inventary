package com.warehouse.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.warehouse.domain.proveedores.*;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.TipoPersonaRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")

public class TipoPersonaController {

@Autowired
	
	private TipoPersonaRepository tipoPersonaRepository;
	
	protected void verifyTipoPersona(int estadoId) throws ResourceNotFoundException {
		Optional<TipoPersona> cliente = tipoPersonaRepository.findById(estadoId);
	if(cliente.isPresent() == false) {
	throw new ResourceNotFoundException("Producto con  id No." + estadoId + " NO ENCONTRADO");
	}
	}

	 

	// Recuperar todos los equipos	OK
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	@CrossOrigin(origins = "*") 
	@GetMapping("/tiposPersona")
	public ResponseEntity<Iterable<TipoPersona>> getAllTipoPersona() {
	Iterable<TipoPersona> allEstados = tipoPersonaRepository.findAll();
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}


	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/tiposPersonaXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<TipoPersona>> getAllTipoPersonaByDescripcion(@RequestParam String descripcion) {
		Iterable<TipoPersona> allEstados = tipoPersonaRepository.findAllByDescripcion(descripcion) ;
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	} 

	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/tiposPersonas/{estadoId}", method=RequestMethod.GET)
	public ResponseEntity<?> getTipoPersona(@PathVariable int estadoId) {
		 
		verifyTipoPersona(estadoId);
		Optional<TipoPersona> e = tipoPersonaRepository.findById(estadoId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/nuevoTipoPersona")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createTipoPersona(@Valid @RequestBody TipoPersona estadocliente) {
		estadocliente = tipoPersonaRepository.save(estadocliente);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(estadocliente.getIdTipoPersona())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	//Actualizar un estado OK
	@PutMapping(value="/tipoPersona/{estadoId}")
	public ResponseEntity<?> updateTipoPersona(@RequestBody TipoPersona estado, @PathVariable int estadoId) {
		verifyTipoPersona(estadoId);
		tipoPersonaRepository.save(estado);
		return new ResponseEntity<>(HttpStatus.OK);  
	}

	//Borrar un estado OK
	@DeleteMapping(value="/tiposPersona/{estadoId}")
	public ResponseEntity<?> deleteTipoPersona(@PathVariable int estadoId) {
		verifyTipoPersona(estadoId);
		tipoPersonaRepository.deleteById(estadoId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
