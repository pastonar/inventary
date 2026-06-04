package com.warehouse.controller;

import java.net.URI;
import java.util.List;
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

import com.warehouse.domain.proveedores.Proveedor;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.ProveedorRepository;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*") 
public class ProveedorController {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	protected void verifyEquipo(Long proveedorId) throws ResourceNotFoundException {
		Optional<Proveedor> proveedor = proveedorRepository.findById(proveedorId);
	if (proveedor.isPresent() == false) {
	throw new ResourceNotFoundException("Proveedor con  id No." + proveedorId + " NO ENCONTRADO");
	}
	}
	
	@CrossOrigin(origins = "*") 
	@GetMapping("/proveedores")
	public ResponseEntity<Iterable<Proveedor>> getAllProveedor() {
	Iterable<Proveedor> allProveedor = proveedorRepository.findAll();
	return new ResponseEntity<>(allProveedor, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/proveedoresXNit")
	public ResponseEntity<Iterable<Proveedor>> getAllproveedorsByNit(@RequestParam String nit) {
		//System.out.println(nombreproveedor);
		Iterable<Proveedor> allProveedores = proveedorRepository.findAllBynit(nit) ;
	return new ResponseEntity<>(allProveedores, HttpStatus.OK);
	}
	
	
	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/proveedorsXrazonsocial", method=RequestMethod.GET)
	//@CrossOrigin(origins = "*")
	// http://localhost:8080/proveedorsXrazonsocial?nombreproveedor=
	@GetMapping("/proveedoresXRazonSocial")
	public ResponseEntity<Iterable<Proveedor>> getAllproveedorsByRazonSocial(@RequestParam String nombreproveedor) {
		System.out.println(nombreproveedor);
		Iterable<Proveedor> allProveedors = proveedorRepository.findAllByrazonSocial(nombreproveedor) ;
	return new ResponseEntity<>(allProveedors, HttpStatus.OK);
	}
	
	
	
	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	//@CrossOrigin(origins = "*") 
	// http://localhost:8080/proveedoresXrazonsocial?nombrecliente=
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		@GetMapping("/proveedoresXDireccion")
		//@RequestMapping(value="/proveedores", method=RequestMethod.GET)
		public ResponseEntity<Iterable<Proveedor>> getAllproveedoresByDireccion(@RequestParam String direccion) {
			System.out.println(direccion);
			Iterable<Proveedor> allProveedors = proveedorRepository.findAllByDireccion(direccion) ;
		return new ResponseEntity<>(allProveedors, HttpStatus.OK);
		}
	
	//Crear un nuevo equipo
	//@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/proveedores")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createProducto(@Valid @RequestBody Proveedor proveedor) {
		HttpHeaders responseHeaders = new HttpHeaders();
		proveedor = proveedorRepository.save(proveedor);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(proveedor.getIdProveedor())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	
	
	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@CrossOrigin(origins = "*")
	@GetMapping("/proveedores/{idProveedor}")
	//@RequestMapping(value="/productos/{id_producto}", method=RequestMethod.GET)
	public ResponseEntity<?> getProveedor(@PathVariable Long idProveedor) {
	
		Optional<Proveedor> cliente = proveedorRepository.findById(idProveedor);
		Proveedor clienteE = new Proveedor();
		if(cliente.isPresent() == true) 
			clienteE = cliente.get();
		return new ResponseEntity<> (clienteE, HttpStatus.OK);  
	}
	
	
	//Actualizar un equipo OK
	@PutMapping("/proveedores/{idProveedor}")
	@CrossOrigin(origins = "*") 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updaterProducto(@RequestBody Proveedor cliente, @PathVariable Long idProveedor) {
		
		proveedorRepository.save(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un equipo OK
	@DeleteMapping(value="/proveedores/{idProveedor}")
	public ResponseEntity<?> deleteProducto(@PathVariable Long idProveedor) {
		verifyEquipo(idProveedor);
		proveedorRepository.deleteById(idProveedor);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
