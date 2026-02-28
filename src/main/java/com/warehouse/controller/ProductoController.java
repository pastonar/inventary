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



import com.warehouse.domain.productos.Producto;
import com.warehouse.dto.VentasDto;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.ProductoRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*") 
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
//@RequestMapping(value="/productos", method=RequestMethod.GET)
@GetMapping("/productos")
public ResponseEntity<Iterable<Producto>> getAllProductos() {
Iterable<Producto> allProductos = productoRepository.findAll();
return new ResponseEntity<>(allProductos, HttpStatus.OK);
}


//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@GetMapping("/productosXdescripcion")
public ResponseEntity<Iterable<Producto>> getAllProductosByDescripcion(@RequestParam String descripcion) {
	Iterable<Producto> allProductos = productoRepository.findAllByDescripcion(descripcion) ;
return new ResponseEntity<>(allProductos, HttpStatus.OK);
}

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@GetMapping("/productosXdescripcionEstado")
public ResponseEntity<Iterable<Producto>> getAllProductosByDescripcion(@RequestParam String descripcion,@RequestParam int estado) {
	Iterable<Producto> allProductos = productoRepository.findAllByDescripcionEstado(descripcion,estado) ;
return new ResponseEntity<>(allProductos, HttpStatus.OK);
}



//Recuperar un equipo en particular	OK
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/productos/{id_producto}")
//@RequestMapping(value="/productos/{id_producto}", method=RequestMethod.GET)
public ResponseEntity<?> getProducto(@PathVariable Long id_producto) {
	 
	verifyEquipo(id_producto);
	Optional<Producto> e = productoRepository.findById(id_producto);
	return new ResponseEntity<> (e, HttpStatus.OK);  
}
//Crear un nuevo equipo
//@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/productoNuevo")
public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto) {
	long count = 0;
	count = productoRepository.countByPresentacionYUnidad(producto.getDescripcion());
	//System.out.println(producto.isFraccionar());
	HttpHeaders responseHeaders = new HttpHeaders();
	if (count == 0)
	{
	producto = productoRepository.save(producto);
	URI newProductoUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(producto.getId_producto())
			.toUri();
	responseHeaders.setLocation(newProductoUri);
	}
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}



//Actualizar un producto OK
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PutMapping("/productos/{id_producto}")
@CrossOrigin(origins = "*") 

public ResponseEntity<?> updaterProducto(@RequestBody Producto producto, @PathVariable Long id_producto) {
	
	productoRepository.save(producto);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un equipo OK
@DeleteMapping("/productos/{id_producto}")
public ResponseEntity<?> deleteProducto(@PathVariable Long id_producto) {
	
	productoRepository.deleteById(id_producto);
return new ResponseEntity<>(HttpStatus.OK);
}

/*
@Query(value="SELECT f.*,c.razon_social as nombre_cliente "
		+ "FROM facturas as f,clientes as c "
		+ "where fecha_factura between :date1 and :date2 "
		,nativeQuery = true)
List<VentasDto> findAllByfechas(LocalDate date1,LocalDate date2);


@Query(value="SELECT f.*,c.razon_social as nombre_cliente "
		+ "FROM facturas as f,clientes as c "
		+ "where fecha_factura between :date1 and :date2 "
		+ "and f.id_cliente = c.idcliente and f.id_cliente = :idCliente"
		,nativeQuery = true)
List<VentasDto> findAllByClienteFechas(LocalDate date1,LocalDate date2,Long idCliente);
*/


}