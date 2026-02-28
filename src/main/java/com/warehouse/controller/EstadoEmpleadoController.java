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

import com.warehouse.domain.empleados.EstadoEmpleado;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.EstadoEmpleadoRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")


public class EstadoEmpleadoController {


@Autowired
	
	private EstadoEmpleadoRepository estadoEmpleadoRepository;
	
	protected void verifyEstado(int estadoId) throws ResourceNotFoundException {
		Optional<EstadoEmpleado> cliente = estadoEmpleadoRepository.findById(estadoId);
	if(cliente.isPresent() == false) {
	throw new ResourceNotFoundException("Producto con  id No." + estadoId + " NO ENCONTRADO");
	}
	}

	 

	// Recuperar todos los equipos	OK
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	@CrossOrigin(origins = "*") 
	@GetMapping("/estadosEmpleado")
	public ResponseEntity<Iterable<EstadoEmpleado>> getAllEstadoEmpleado() {
	Iterable<EstadoEmpleado> allEstados = estadoEmpleadoRepository.findAll();
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}


	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/estadosEmpleadoXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<EstadoEmpleado>> getAllEstadosByDescripcion(@RequestParam String descripcion) {
		Iterable<EstadoEmpleado> allEstados = estadoEmpleadoRepository.findAllByDescripcion(descripcion) ;
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	} 

	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/estadosEmpleados/{estadoId}", method=RequestMethod.GET)
	public ResponseEntity<?> getEstado(@PathVariable int estadoId) {
		 
		verifyEstado(estadoId);
		Optional<EstadoEmpleado> e = estadoEmpleadoRepository.findById(estadoId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/nuevoEstadoEmpleado")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createEstado(@Valid @RequestBody EstadoEmpleado estadocliente) {
		estadocliente = estadoEmpleadoRepository.save(estadocliente);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(estadocliente.getIdEstado())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	//Actualizar un estado OK
	@PutMapping(value="/estadoEmplead/{estadoId}")
	public ResponseEntity<?> updateProducto(@RequestBody EstadoEmpleado estado, @PathVariable int estadoId) {
		verifyEstado(estadoId);
		estadoEmpleadoRepository.save(estado);
		return new ResponseEntity<>(HttpStatus.OK);  
	}

	//Borrar un estado OK
	@DeleteMapping(value="/estadosEmpleado/{estadoId}")
	public ResponseEntity<?> deleteEstado(@PathVariable int estadoId) {
		verifyEstado(estadoId);
		estadoEmpleadoRepository.deleteById(estadoId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
