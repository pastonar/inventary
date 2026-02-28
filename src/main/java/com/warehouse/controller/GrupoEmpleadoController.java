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

import com.warehouse.domain.empleados.GrupoEmpleado;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.GrupoEmpleadoRepository;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins="*")
public class GrupoEmpleadoController {

@Autowired
	
	private GrupoEmpleadoRepository grupoEmpleadoRepository;
	
	protected void verifyEstado(Long grupoId) throws ResourceNotFoundException {
		Optional<GrupoEmpleado> cliente = grupoEmpleadoRepository.findById(grupoId);
	if(cliente.isPresent() == false) {
	throw new ResourceNotFoundException("Producto con  id No." + grupoId + " NO ENCONTRADO");
	}
	}

	 

	// Recuperar todos los equipos	OK
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	@CrossOrigin(origins = "*") 
	@GetMapping("/grupoEmpleado")
	public ResponseEntity<Iterable<GrupoEmpleado>> getAllGrupoEmpleado() {
	Iterable<GrupoEmpleado> allGrupos = grupoEmpleadoRepository.findAll();
	return new ResponseEntity<>(allGrupos, HttpStatus.OK);
	}


	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/gruposXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<GrupoEmpleado>> getAllGruposByDescripcion(@RequestParam String descripcion) {
		Iterable<GrupoEmpleado> allEstados = grupoEmpleadoRepository.findAllByDescripcion(descripcion) ;
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}

	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/gruposEmpleados/{grupoId}", method=RequestMethod.GET)
	public ResponseEntity<?> getEstado(@PathVariable Long estadoId) {
		 
		verifyEstado(estadoId);
		Optional<GrupoEmpleado> e = grupoEmpleadoRepository.findById(estadoId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/nuevoGrupoEmpleado")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createEstado(@Valid @RequestBody GrupoEmpleado grupocliente) {
		grupocliente = grupoEmpleadoRepository.save(grupocliente);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(grupocliente.getIdGrupoEmpleado())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	//Actualizar un estado OK
	@PutMapping(value="/gruposEmpleados/{grupoId}")
	public ResponseEntity<?> updateProducto(@RequestBody GrupoEmpleado estado, @PathVariable Long estadoId) {
		verifyEstado(estadoId);
		grupoEmpleadoRepository.save(estado);
		return new ResponseEntity<>(HttpStatus.OK);  
	}

	//Borrar un estado OK
	@DeleteMapping(value="/gruposEmpleados/{grupoId}")
	public ResponseEntity<?> deleteEstado(@PathVariable Long estadoId) {
		verifyEstado(estadoId);
		grupoEmpleadoRepository.deleteById(estadoId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
