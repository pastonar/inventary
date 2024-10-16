package com.warehouse.controller;


import java.net.URI;
import java.util.ArrayList;
//import java.net.http.HttpHeaders;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;



import com.warehouse.domain.productos.Producto;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.ProductoRepository;

import jakarta.validation.Valid;

@RestController
public class ProductoController {

@Autowired
private ProductoRepository productoRepository;

protected void verifyEquipo(Long productoId) throws ResourceNotFoundException {
	Optional<Producto> producto = productoRepository.findById(productoId);
if(producto.isPresent() == false) {
throw new ResourceNotFoundException("Producto con  id No." + productoId + " NO ENCONTRADO");
}
}

 

// Recuperar todos los equipos	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/productos", method=RequestMethod.GET)
//@GetMapping("/productos")
public ResponseEntity<Iterable<Producto>> getAllProductos() {
Iterable<Producto> allProductos = productoRepository.findAll();
return new ResponseEntity<>(allProductos, HttpStatus.OK);
}


//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping(value="/productosXdescripcion", method=RequestMethod.GET)
public ResponseEntity<Iterable<Producto>> getAllProductosByDescripcion(@RequestParam String descripcion) {
	Iterable<Producto> allProductos = productoRepository.findAllByDescripcion(descripcion) ;
return new ResponseEntity<>(allProductos, HttpStatus.OK);
}

//Recuperar un equipo en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")

@RequestMapping(value="/productos/{productoId}", method=RequestMethod.GET)
public ResponseEntity<?> getProducto(@PathVariable Long productoId) {
	 
	verifyEquipo(productoId);
	Optional<Producto> e = productoRepository.findById(productoId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo equipo
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/productoNuevo")
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto) {
	producto = productoRepository.save(producto);
	// Set the location header for the newly created resource
	HttpHeaders responseHeaders = new HttpHeaders();
	URI newProductoUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(producto.getId_producto())
			.toUri();
	responseHeaders.setLocation(newProductoUri);
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un equipo OK
@RequestMapping(value="/productos/{productoId}", method=RequestMethod.PUT)
public ResponseEntity<?> updateProducto(@RequestBody Producto producto, @PathVariable Long productoId) {
	verifyEquipo(productoId);
	productoRepository.save(producto);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un equipo OK
@RequestMapping(value="/productos/{productoId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteProducto(@PathVariable Long productoId) {
	verifyEquipo(productoId);
	productoRepository.deleteById(productoId);
return new ResponseEntity<>(HttpStatus.OK);
}

}