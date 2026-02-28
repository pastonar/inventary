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

import com.warehouse.domain.empleados.TipoIdentificacion;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.TipoIdentificacionEmpleadoRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")

public class TipoIdentificacionController {

@Autowired
	
	private TipoIdentificacionEmpleadoRepository tipoIdentificacionRepository;
	
	protected void verifyTipoIdentificacion(int estadoId) throws ResourceNotFoundException {
		Optional<TipoIdentificacion> cliente = tipoIdentificacionRepository.findById(estadoId);
	if(cliente.isPresent() == false) {
	throw new ResourceNotFoundException("Producto con  id No." + estadoId + " NO ENCONTRADO");
	}
	}

	 

	// Recuperar todos los equipos	OK
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	@CrossOrigin(origins = "*") 
	@GetMapping("/tiposIdentificacion")
	public ResponseEntity<Iterable<TipoIdentificacion>> getAllTipoIdentificacion() {
	Iterable<TipoIdentificacion> allEstados = tipoIdentificacionRepository.findAll();
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	}


	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/tiposIdentificacionXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<TipoIdentificacion>> getAllTipoIdentificacionByDescripcion(@RequestParam String descripcion) {
		Iterable<TipoIdentificacion> allEstados = tipoIdentificacionRepository.findAllByDescripcion(descripcion) ;
	return new ResponseEntity<>(allEstados, HttpStatus.OK);
	} 

	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/tiposIdentificacions/{estadoId}", method=RequestMethod.GET)
	public ResponseEntity<?> getTipoIdentificacion(@PathVariable int estadoId) {
		 
		verifyTipoIdentificacion(estadoId);
		Optional<TipoIdentificacion> e = tipoIdentificacionRepository.findById(estadoId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/nuevoTipoIdentificacion")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createTipoIdentificacion(@Valid @RequestBody TipoIdentificacion estadocliente) {
		estadocliente = tipoIdentificacionRepository.save(estadocliente);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(estadocliente.getIdTipoIdentificacion())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	//Actualizar un estado OK
	@PutMapping(value="/tipoIdentificacion/{estadoId}")
	public ResponseEntity<?> updateTipoIdentificacion(@RequestBody TipoIdentificacion estado, @PathVariable int estadoId) {
		verifyTipoIdentificacion(estadoId);
		tipoIdentificacionRepository.save(estado);
		return new ResponseEntity<>(HttpStatus.OK);  
	}

	//Borrar un estado OK
	@DeleteMapping(value="/tiposIdentificacion/{estadoId}")
	public ResponseEntity<?> deleteTipoIdentificacion(@PathVariable int estadoId) {
		verifyTipoIdentificacion(estadoId);
		tipoIdentificacionRepository.deleteById(estadoId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
