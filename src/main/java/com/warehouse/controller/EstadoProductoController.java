package com.warehouse.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.EstadoProductoRepository;

import jakarta.validation.Valid;
import com.warehouse.domain.productos.EstadoProducto;
@RestController
@CrossOrigin(origins="*")
public class EstadoProductoController {

	@Autowired
	private EstadoProductoRepository estadoProductoRepository;

	protected void verifyEstado(Long estadoId) throws ResourceNotFoundException {
		Optional<EstadoProducto> producto = estadoProductoRepository.findById(estadoId);
	if(producto.isPresent() == false) {
	throw new ResourceNotFoundException("Producto con  id No." + estadoId + " NO ENCONTRADO");
	}
	}

	 

	// Recuperar todos los equipos	OK
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	@CrossOrigin(origins = "*") 
	@GetMapping("/estadoProducto")
	public ResponseEntity<Iterable<EstadoProducto>> getAllProductos() {
	Iterable<EstadoProducto> allEstados = estadoProductoRepository.findAll();
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}


	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/estadosXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<EstadoProducto>> getAllProductosByDescripcion(@RequestParam String descripcion) {
		Iterable<EstadoProducto> allEstados = estadoProductoRepository.findAllByDescripcion(descripcion) ;
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}

	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

	@RequestMapping(value="/estados/{estadoId}", method=RequestMethod.GET)
	public ResponseEntity<?> getEstado(@PathVariable Long estadoId) {
		 
		verifyEstado(estadoId);
		Optional<EstadoProducto> e = estadoProductoRepository.findById(estadoId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/estadoNuevo")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createEstado(@Valid @RequestBody EstadoProducto estado) {
		estado = estadoProductoRepository.save(estado);
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(estado.getIdEstadoProducto())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	//Actualizar un estado OK
	@RequestMapping(value="/estados/{estadoId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateProducto(@RequestBody EstadoProducto estado, @PathVariable Long estadoId) {
		verifyEstado(estadoId);
		estadoProductoRepository.save(estado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un estado OK
	@RequestMapping(value="/estados/{estadoId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteEstado(@PathVariable Long estadoId) {
		verifyEstado(estadoId);
		estadoProductoRepository.deleteById(estadoId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
}
