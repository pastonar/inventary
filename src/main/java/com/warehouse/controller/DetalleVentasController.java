package com.warehouse.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.warehouse.domain.ventas.DetalleVentas;

import com.warehouse.repository.DetalleVentasRepository;



import jakarta.validation.Valid;

@RestController
public class DetalleVentasController {
	
	@Autowired
	private DetalleVentasRepository detalleventasRepository;
	
	@RequestMapping(value="/Ventas/{IdFactura}/detalleFactura", method=RequestMethod.GET)
	//@ApiOperation(value = "Retrieves all the votes", response=Vote.class, responseContainer="List")
	public List<DetalleVentas> getAllDetalleVentas(@PathVariable Long IdFactura) {
		return detalleventasRepository.findByIdFactura(IdFactura);
	}
	
	//Crear un nuevo equipo
	@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/nuevoDetalleVentas")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createDetalleVentas(@Valid @RequestBody DetalleVentas detalleventas) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
			detalleventas = detalleventasRepository.save(detalleventas);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(detalleventas.getId_detalle_factura())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

}
