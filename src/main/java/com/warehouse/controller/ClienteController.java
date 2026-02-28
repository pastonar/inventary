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

import com.warehouse.domain.clientes.Cliente;
import com.warehouse.domain.productos.Producto;
import com.warehouse.domain.ventas.DetalleVentas;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.ClienteRepository;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*") 
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	protected void verifyEquipo(Long clienteId) throws ResourceNotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
	if(cliente.isPresent() == false) {
	throw new ResourceNotFoundException("Cliente con  id No." + clienteId + " NO ENCONTRADO");
	}
	}
	
	// Recuperar todos los equipos	OK
	// @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	//@CrossOrigin(origins = "*") 
	@GetMapping("/clientes")
	public ResponseEntity<Iterable<Cliente>> getAllClientes() {
	Iterable<Cliente> allCliente = clienteRepository.findAll();
	return new ResponseEntity<>(allCliente, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/clientesXNit")
	public ResponseEntity<Iterable<Cliente>> getAllclientesByNit(@RequestParam String nit) {
		//System.out.println(nombrecliente);
		Iterable<Cliente> allClientes = clienteRepository.findAllBynit(nit) ;
	return new ResponseEntity<>(allClientes, HttpStatus.OK);
	}
	
	
	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/clientesXrazonsocial", method=RequestMethod.GET)
	//@CrossOrigin(origins = "*")
	// http://localhost:8080/clientesXrazonsocial?nombrecliente=
	@GetMapping("/clientesXrazonsocial")
	public ResponseEntity<Iterable<Cliente>> getAllclientesByRazonSocial(@RequestParam String nombrecliente) {
		System.out.println(nombrecliente);
		Iterable<Cliente> allClientes = clienteRepository.findAllByrazonSocial(nombrecliente) ;
	return new ResponseEntity<>(allClientes, HttpStatus.OK);
	}
	
	@GetMapping("/clientesXgrupoCliente")
	public ResponseEntity<Iterable<Cliente>> getAllclientesBygrupoCliente(@RequestParam int grupo) {

		Iterable<Cliente> allClientes = clienteRepository.findAllBygrupoCliente(grupo);
	return new ResponseEntity<>(allClientes, HttpStatus.OK);
	}
	
	//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	//@CrossOrigin(origins = "*") 
	// http://localhost:8080/clientesXrazonsocial?nombrecliente=
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		//@GetMapping("/clientesXdireccion")
		@RequestMapping(value="/clientesXdireccion", method=RequestMethod.GET)
		public ResponseEntity<Iterable<Cliente>> getAllclientesByDireccion(@RequestParam String direccion) {
			System.out.println(direccion);
			Iterable<Cliente> allClientes = clienteRepository.findAllByDireccion(direccion) ;
		return new ResponseEntity<>(allClientes, HttpStatus.OK);
		}
	
	//Crear un nuevo equipo
	//@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/clienteNuevo")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createProducto(@Valid @RequestBody Cliente cliente) {
		HttpHeaders responseHeaders = new HttpHeaders();
		cliente = clienteRepository.save(cliente);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getIdCliente())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	
	
	//Recuperar un equipo en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@CrossOrigin(origins = "*")
	@GetMapping("/clientes/{idCliente}")
	//@RequestMapping(value="/productos/{id_producto}", method=RequestMethod.GET)
	public ResponseEntity<?> getCliente(@PathVariable Long idCliente) {
	
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		Cliente clienteE = new Cliente();
		if(cliente.isPresent() == true) 
			clienteE = cliente.get();
		return new ResponseEntity<> (clienteE, HttpStatus.OK);  
	}
	
	
	//Actualizar un equipo OK
	@PutMapping("/clientes/{idCliente}")
	@CrossOrigin(origins = "*") 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updaterProducto(@RequestBody Cliente cliente, @PathVariable Long idCliente) {
		
		clienteRepository.save(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Borrar un equipo OK
	@DeleteMapping(value="/clientes/{idCliente}")
	public ResponseEntity<?> deleteProducto(@PathVariable Long idCliente) {
		verifyEquipo(idCliente);
		clienteRepository.deleteById(idCliente);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
