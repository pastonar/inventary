package com.warehouse.controller;


import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.net.http.HttpHeaders;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.warehouse.dto.VentasDto;
import com.warehouse.dto.VentasHeaderDTO;
import com.warehouse.dto.VentasListDTO;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.VentasHeaderRepository;
import jakarta.validation.Valid;

@RestController
public class VentasHeaderControllerDto {

@Autowired
private VentasHeaderRepository ventasheaderRepository;

protected void verifyEquipo(Long ventasId) throws ResourceNotFoundException {
	Optional<VentasHeaderDTO> ventas = ventasheaderRepository.findById(ventasId);
if(ventas.isPresent() == false) {
throw new ResourceNotFoundException("Ventas con  id No." + ventasId + " NO ENCONTRADO");
}
}

 

// Recuperar todos los equipos	OK
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//@RequestMapping(value="/ventass", method=RequestMethod.GET)

@GetMapping("/ventasHeaderDto")
public ResponseEntity<Iterable<VentasHeaderDTO>> getAllVentas() {
Iterable<VentasHeaderDTO> allVentas = ventasheaderRepository.findAll();
return new ResponseEntity<>(allVentas, HttpStatus.OK);
}

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/ventasHeaderDtoByCliente")
public ResponseEntity<Iterable<VentasHeaderDTO>> getAllVentasByCliente(@RequestParam  int tipoFactura,Long idcliente) {
Iterable<VentasHeaderDTO> allVentas = ventasheaderRepository.findAllByidCliente(tipoFactura,idcliente);
return new ResponseEntity<>(allVentas, HttpStatus.OK);
}

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/ventasHeaderDtoByFechas")
public ResponseEntity<Iterable<VentasHeaderDTO>> getAllVentasByFechas(@RequestParam  int tipoFactura,LocalDate date1,LocalDate date2) {
Iterable<VentasHeaderDTO> allVentas = ventasheaderRepository.findAllByfechas(tipoFactura,date1,date2);
return new ResponseEntity<>(allVentas, HttpStatus.OK); 
}

//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/ventasHeaderDtoByClienteFechas")
public ResponseEntity<Iterable<VentasHeaderDTO>> getAllVentasByClienteFechas(@RequestParam int tipoFactura, Long idcliente,LocalDate date1,LocalDate date2) {
Iterable<VentasHeaderDTO> allVentas = ventasheaderRepository.findAllByClienteFechas(tipoFactura,idcliente,date1,date2);
return new ResponseEntity<>(allVentas, HttpStatus.OK); 
}


/*
 * @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
 * 
 * @CrossOrigin(origins = "*")
 * 
 * @GetMapping("/ventasDtoByCliente") public ResponseEntity<Iterable<VentasDto>>
 * getAllVentasByCliente(@RequestParam Long idcliente) { Iterable<VentasDto>
 * allVentas = ventasRepository.findAllByidCliente(idcliente); return new
 * ResponseEntity<>(allVentas, HttpStatus.OK); }
 */


//Recuperar equipo por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
/*
 * @CrossOrigin(origins = "*")
 * 
 * @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
 * 
 * @RequestMapping(value="/ventassXmarcaYserial", method=RequestMethod.GET)
 * public ResponseEntity<Iterable<Ventas>>
 * getAllVentassByMarcaYSerial(@RequestParam String marca,@RequestParam String
 * serial) { Iterable<Ventas> allVentass =
 * ventasRepository.findAllByMarcaYSerial(marca,serial) ; return new
 * ResponseEntity<>(allVentass, HttpStatus.OK); }
 */

//Recuperar un equipo en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@GetMapping("/ventasHeaderDto/{ventasId}")
public ResponseEntity<?> getVentas(@PathVariable Long ventasId) {
	 
	verifyEquipo(ventasId);
	Optional<VentasHeaderDTO> e = ventasheaderRepository.findById(ventasId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo equipo
@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/nuevaFacturaHeaderDto")
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
public ResponseEntity<?> createVentas(@Valid @RequestBody VentasHeaderDTO venta) {
	double cantidadVendida = 0;
	long idProducto;
	HttpHeaders responseHeaders = new HttpHeaders();
	venta = ventasheaderRepository.save(venta);
	
	
	// Set the location header for the newly created resource
	
	URI newVentasUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(venta.getId_factura())
			.toUri();
	responseHeaders.setLocation(newVentasUri);
	
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un equipo OK
@RequestMapping(value="/ventasHeaderDto/{ventasId}", method=RequestMethod.PUT)
public ResponseEntity<?> updateVentas(@RequestBody VentasHeaderDTO ventas, @PathVariable Long ventasId) {
	verifyEquipo(ventasId);
	ventasheaderRepository.save(ventas);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Actualizar un equipo OK
@RequestMapping(value="/ventasHeaderDto", method=RequestMethod.PATCH)
public ResponseEntity<?> updateHeadeVentas(@RequestBody VentasListDTO venta) {
	System.out.println(venta.getRelacion_facturas().get(0).getId_factura());
	String cad ="";
	int l =venta.getRelacion_facturas().size();
	for(int i=0; i<l;i++)
	{
		ventasheaderRepository.updateventa(venta.getRelacion_facturas().get(i).getId_factura());
		//cad = cad +venta.getRelacion_facturas().get(i).getId_factura();
		//if (i<l-1) cad=cad+",";
		//System.out.println(venta.getRelacion_facturas().size());
	
	}
	//System.out.println("update facturas set estado_pago = 1 where ID_FACTURA in ("+cad+")");
	//System.out.println(cad);
	
	//System.out.println(v.getRelacion_facturas().get(0).);
	//ventasheaderRepository.save(ventas);
	return new ResponseEntity<>(HttpStatus.OK);
}


//Borrar un equipo OK
@DeleteMapping("/ventasHeaderDto/{ventasId}")
//@RequestMapping(value="/ventasHeaderDto/{ventasId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteVentas(@PathVariable Long ventasId) {
	verifyEquipo(ventasId);
	ventasheaderRepository.deleteById(ventasId);
return new ResponseEntity<>(HttpStatus.OK);
}

}