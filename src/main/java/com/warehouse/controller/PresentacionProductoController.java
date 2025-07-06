package com.warehouse.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.warehouse.domain.productos.Presentacion;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.PresentacionRepository;

import jakarta.servlet.ServletException;
import jakarta.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
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

	protected int verifyDescripcion(@RequestParam String descripcion) throws ResourceNotFoundException {
		Presentacion presentacion = presentacionRepository.findByDescripcion(descripcion);
	if(presentacion == null) 
	return 0;
		//throw new ResourceNotFoundException("La presentacion "+presentacion.getDescripcion()+" ya existe");
	else return 1;
	
	}

	
	
	
	protected void verifyDescripcion1(@RequestParam String descripcion) throws java.sql.SQLIntegrityConstraintViolationException {
		Presentacion presentacion = presentacionRepository.findByDescripcion(descripcion);
	if(presentacion != null) {
	throw new java.sql.SQLIntegrityConstraintViolationException("La presentacion "+presentacion.getDescripcion()+" ya existe");
	}
	}
	//throw new java.sql.SQLIntegrityConstraintViolationException("duplicado");
	
	// Recuperar todos los equipos	OK
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	//@RequestMapping(value="/presentaciones", method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/presentaciones")
	public ResponseEntity<Iterable<Presentacion>> getAllPresentacion() 
		throws IOException, ServletException
		{
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin","*"); 
        //responseHeaders.
		Iterable<Presentacion> allPresentaciones = presentacionRepository.findAll();
		//return ResponseEntity.ok()
			//.headers(responseHeaders)
		// .body(allPresentaciones);
			  
		return new ResponseEntity<>(allPresentaciones, HttpStatus.OK);
	}

	/*
	 public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin","*");
        System.out.println("response");
        return ResponseEntity.ok()
          .headers(responseHeaders)
          .body("Response with header using ResponseEntity");
    }
	 */

	
	
	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	@CrossOrigin(origins = "http://localhost:8080")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/presentaXdescripcion", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Presentacion>>
	  getAllPresentacionByDescripcion(@RequestParam String descripcion) {
	  Iterable<Presentacion> allEstados = presentacionRepository.findAllByDescripcion(descripcion);
	  System.out.println(allEstados);
	  return new ResponseEntity<>(allEstados, HttpStatus.OK); }
	
	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
		@CrossOrigin(origins = "http://localhost:8080")
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		@RequestMapping(value="/presentacionXdescribir1", method=RequestMethod.GET)
		public ResponseEntity <Iterable<Presentacion>>  
		  getPresentacionByDescripcion(@RequestParam String descripcion) {
			Iterable<Presentacion> allEstados = presentacionRepository.findByDescripcion1(descripcion);
		 
		  System.out.println("allEstados: "+allEstados);
		  return new ResponseEntity<>(allEstados, HttpStatus.OK); }
		
	
		@CrossOrigin(origins = "http://localhost:8080")
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		@RequestMapping(value="/presentacionXdescribir", method=RequestMethod.GET)
		public ResponseEntity <?>  
		  getPresentacionesByDescripcion(@RequestParam String descripcion) {
		  Presentacion presentacion = presentacionRepository.findByDescripcion(descripcion);
		 
		  System.out.println("presentacion: "+presentacion.getDescripcion());
		  return new ResponseEntity<>(presentacion, HttpStatus.OK); }
		
	
	/*
	 * @CrossOrigin(origins = "http://localhost:8080")
	 * 
	 * @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	 * 
	 * @RequestMapping(value="/presentacionXdescripcion", method=RequestMethod.GET)
	 * public ResponseEntity<Iterable<Presentacion>>
	 * getAllPresentacionByDescripcion(@RequestParam String descripcion) {
	 * Iterable<Presentacion> allEstados =
	 * presentacionRepository.findAllByDescripcion(descripcion)
	 * //.findAllByDescripcion(descripcion) ;
	 * 
	 * return new ResponseEntity<>(allEstados, HttpStatus.OK); }
	 */

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value="/presentacionXdescripcion", method=RequestMethod.GET) 
	public ResponseEntity<?> countByDescripcion(@RequestParam String descripcion) {
		Long counterestados = presentacionRepository.countByDescripcionIgnoreCase(descripcion) ;
	
		return new ResponseEntity<>(counterestados, HttpStatus.OK);
	}
	
	
	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

	@RequestMapping(value="/presentaciones/{presentacionId}", method=RequestMethod.GET)
	public ResponseEntity<?> getEstado(@PathVariable Long presentacionId) {
		 
		verifyPresentacion(presentacionId);
		Optional<Presentacion> e = presentacionRepository.findById(presentacionId);
		return new ResponseEntity<> (e, HttpStatus.OK); 
	}
	//Crear un nuevo equipo
	@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/presentacionNuevo")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createPresentacion(@Valid @RequestBody Presentacion presentacion) 
		{
			  Long counterestados =
			  presentacionRepository.countByDescripcionIgnoreCase(presentacion.getDescripcion().toUpperCase()) ;
			  System.out.println("No presentacion: "+counterestados);
			  HttpHeaders  responseHeaders = new HttpHeaders();
			 	
		if (counterestados == 0)
		{
			presentacion = presentacionRepository.save(presentacion);
			URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(presentacion.getIdpresentacion())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<>(null,responseHeaders,HttpStatus.BAD_REQUEST);
			
	}

	//Actualizar un presentacion OK
	//@RequestMapping(value="/presentaciones/{presentacionId}", method=RequestMethod.PUT)
	@PutMapping("/updatepresentaciones/{presentacionId}")
	public ResponseEntity<?> updateProducto(@RequestBody Presentacion presentacion, @PathVariable Long presentacionId) {
		//verifyPresentacion(presentacionId);
		presentacionRepository.save(presentacion);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un presentacion OK
	@RequestMapping(value="/presentaciones/{presentacionId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteEstado(@PathVariable Long presentacionId) {
		
		presentacionRepository.deleteById(presentacionId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
