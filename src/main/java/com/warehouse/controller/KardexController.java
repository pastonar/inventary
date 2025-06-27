package com.warehouse.controller;


import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.net.http.HttpHeaders;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;



import com.warehouse.domain.productos.Kardex;
import com.warehouse.dto.VentasDto;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.KardexRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*") 
public class KardexController {

@Autowired
private KardexRepository kardexRepository;

protected void verifyEquipo(Long kardexId) throws ResourceNotFoundException {
	Optional<Kardex> kardex = kardexRepository.findById(kardexId);
if(kardex.isPresent() == false) {
throw new ResourceNotFoundException("Kardex con  id No." + kardexId + " NO ENCONTRADO");
}
}

 

// Recuperar todos los equipos	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//@RequestMapping(value="/kardexs", method=RequestMethod.GET)
@GetMapping("/kardex")
public ResponseEntity<Iterable<Kardex>> getAllKardex() {
Iterable<Kardex> allKardex = kardexRepository.findAll();
return new ResponseEntity<>(allKardex, HttpStatus.OK);
}


//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@GetMapping("/kardexsXdescripcion")
public ResponseEntity<Iterable<Kardex>> getAllKardexByDescripcion(@RequestParam String descripcion) {
	Iterable<Kardex> allKardex = kardexRepository.findAllByDescripcion(descripcion) ;
return new ResponseEntity<>(allKardex, HttpStatus.OK);
}



//Recuperar un equipo en particular	OK
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/kardexs/{id_kardex}")
//@RequestMapping(value="/kardexs/{id_kardex}", method=RequestMethod.GET)
public ResponseEntity<?> getKardex(@PathVariable Long id_kardex) {
	 
	verifyEquipo(id_kardex);
	Optional<Kardex> e = kardexRepository.findById(id_kardex);
	return new ResponseEntity<> (e, HttpStatus.OK);  
}
//Crear un nuevo equipo
//@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/Nuevokardex")
public ResponseEntity<?> createKardex(@Valid @RequestBody Kardex kardex) {
		HttpHeaders responseHeaders = new HttpHeaders();
		kardex = kardexRepository.save(kardex);
	URI newKardexUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(kardex.getId_mvto())
			.toUri();
	responseHeaders.setLocation(newKardexUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

/*
	 * @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	 * 
	 * @CrossOrigin(origins = "*")
	 * 
	 * @GetMapping("/kardexDtoByfechas") public ResponseEntity<Iterable<Kardex>>
	 * getAllByFechas(@RequestParam LocalDate dateI,LocalDate dateF) {
	 * Iterable<Kardex> allKardex =
	 * kardexRepository.findAllByProductoFechas(dateI,dateF); return new
	 * ResponseEntity<>(allKardex, HttpStatus.OK); }
	 */

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/kardexByProducto")
public ResponseEntity<Iterable<Kardex>> getAllByProducto(@RequestParam  Long idProducto) {
Iterable<Kardex> allKardex = kardexRepository.findAllByProducto(idProducto);
return new ResponseEntity<>(allKardex, HttpStatus.OK); 
}


@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/kardexByProductofechas")
public ResponseEntity<Iterable<Kardex>> getAllByProductoFechas(@RequestParam  Long idProducto,LocalDate date1,LocalDate date2) {
Iterable<Kardex> allKardex = kardexRepository.findAllByProductoFechas(idProducto,date1,date2);
return new ResponseEntity<>(allKardex, HttpStatus.OK); 
}

//Actualizar un equipo OK
@PutMapping("/kardexs/{id_kardex}")
@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
public ResponseEntity<?> updaterKardex(@RequestBody Kardex kardex, @PathVariable Long id_kardex) {
	
	kardexRepository.save(kardex);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un equipo OK
@DeleteMapping("/kardex/{id_kardex}")
public ResponseEntity<?> deleteKardex(@PathVariable Long id_kardex) {
	
	kardexRepository.deleteById(id_kardex);
return new ResponseEntity<>(HttpStatus.OK);
}




}