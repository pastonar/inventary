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


import com.warehouse.exception.ResourceNotFoundException;
//import com.warehouse.repository.GrupoClienteRepository;

import jakarta.validation.Valid;

import com.warehouse.domain.clientes.GrupoCliente;
//@RestController
@CrossOrigin(origins="*")
public class GrupoClienteController {

	/*
	 * @Autowired
	 * 
	 * private GrupoClienteRepository grupoClienteRepository;
	 * 
	 * protected void verifyEstado(Long grupoId) throws ResourceNotFoundException {
	 * Optional<GrupoCliente> cliente = grupoClienteRepository.findById(grupoId);
	 * if(cliente.isPresent() == false) { throw new
	 * ResourceNotFoundException("Producto con  id No." + grupoId +
	 * " NO ENCONTRADO"); } }
	 * 
	 * 
	 * 
	 * @CrossOrigin(origins = "*")
	 * 
	 * @GetMapping("/grupoCliente") public ResponseEntity<Iterable<GrupoCliente>>
	 * getAllGrupoCliente() { Iterable<GrupoCliente> allGrupos =
	 * grupoClienteRepository.findAll(); return new ResponseEntity<>(allGrupos,
	 * HttpStatus.OK); }
	 * 
	 * 
	 * @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	 * 
	 * @RequestMapping(value="/gruposXdescripcion", method=RequestMethod.GET) public
	 * ResponseEntity<Iterable<GrupoCliente>>
	 * getAllGruposByDescripcion(@RequestParam String descripcion) {
	 * Iterable<GrupoCliente> allEstados =
	 * grupoClienteRepository.findAllByDescripcion(descripcion) ; return new
	 * ResponseEntity<>(allEstados, HttpStatus.OK); }
	 * 
	 * //Recuperar un equipo en particular OK
	 * 
	 * @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	 * 
	 * @RequestMapping(value="/gruposClientes/{grupoId}", method=RequestMethod.GET)
	 * public ResponseEntity<?> getEstado(@PathVariable Long estadoId) {
	 * 
	 * verifyEstado(estadoId); Optional<GrupoCliente> e =
	 * grupoClienteRepository.findById(estadoId); return new ResponseEntity<> (e,
	 * HttpStatus.OK); } //Crear un nuevo equipo
	 * 
	 * @PreAuthorize("hasRole('ADMIN')")
	 * 
	 * @PostMapping("/nuevoGrupoCliente") //@RequestMapping(value="/equipos",
	 * method=RequestMethod.POST) public ResponseEntity<?>
	 * createEstado(@Valid @RequestBody GrupoCliente grupocliente) { grupocliente =
	 * grupoClienteRepository.save(grupocliente);
	 * 
	 * HttpHeaders responseHeaders = new HttpHeaders(); URI newProductoUri =
	 * ServletUriComponentsBuilder .fromCurrentRequest() .path("/{id}")
	 * .buildAndExpand(grupocliente.getIdGrupoCliente()) .toUri();
	 * responseHeaders.setLocation(newProductoUri); return new
	 * ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED); }
	 * 
	 * //Actualizar un estado OK
	 * 
	 * @PutMapping(value="/gruposClientes/{grupoId}") public ResponseEntity<?>
	 * updateProducto(@RequestBody GrupoCliente estado, @PathVariable Long estadoId)
	 * { verifyEstado(estadoId); grupoClienteRepository.save(estado); return new
	 * ResponseEntity<>(HttpStatus.OK); }
	 * 
	 * //Borrar un estado OK
	 * 
	 * @DeleteMapping(value="/gruposClientes/{grupoId}") public ResponseEntity<?>
	 * deleteEstado(@PathVariable Long estadoId) { verifyEstado(estadoId);
	 * grupoClienteRepository.deleteById(estadoId); return new
	 * ResponseEntity<>(HttpStatus.OK); }
	 */
}
