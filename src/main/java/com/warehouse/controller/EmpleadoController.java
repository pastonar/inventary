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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.warehouse.domain.empleados.Empleado;
import com.warehouse.domain.productos.Producto;
import com.warehouse.dto.DetallePlanillaDTO;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.EmpleadoRepository;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*") 
public class EmpleadoController {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	private long count;
	
	protected void verifyEquipo(int empleadoId) throws ResourceNotFoundException {
		Optional<Empleado> cliente = empleadoRepository.findById(empleadoId);
	if(cliente.isPresent() == false) {
	throw new ResourceNotFoundException("Empleado con  id No." + empleadoId + " NO ENCONTRADO");
	}
	}
	
	// Recuperar todos los empleados	OK
	// @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	//@RequestMapping(value="/productos", method=RequestMethod.GET)
	//@CrossOrigin(origins = "*") 
	@GetMapping("/empleados")
	public ResponseEntity<Iterable<Empleado>> getAllEmpleados() {
	Iterable<Empleado> allEmpleado = empleadoRepository.findAll();
	return new ResponseEntity<>(allEmpleado, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/empleadosXIdentificcion")
	public ResponseEntity<Iterable<Empleado>> getAllEmpleadosById(@RequestParam String identificacion) {
		//System.out.println(nombrecliente);
		Iterable<Empleado> allEmpleados = empleadoRepository.findAllByNumId(identificacion) ;
	return new ResponseEntity<>(allEmpleados, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/empleadosXNombre")
	public ResponseEntity<Iterable<Empleado>> getAllempleadosByRazonSocial(@RequestParam String nombreempleado) {
		System.out.println(nombreempleado);
		Iterable<Empleado> allEmpleados = empleadoRepository.findAllByNomCompleto(nombreempleado) ;
	return new ResponseEntity<>(allEmpleados, HttpStatus.OK);
	}
	
	@GetMapping("/empleadosXgrupo")
	public ResponseEntity<Iterable<Empleado>> getAllempleadosBygrupoEmpleado(@RequestParam int grupo) {

		Iterable<Empleado> allEmpleados = empleadoRepository.findAllBygrupoEmpleado(grupo);
	return new ResponseEntity<>(allEmpleados, HttpStatus.OK);
	}
	
	//Recuperar empleado por serial	OK @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
	//@CrossOrigin(origins = "*") 
	// http://localhost:8080/empleadosXrazonsocial?nombreempleado=
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		//@GetMapping("/empleadosXdireccion")
		@RequestMapping(value="/empleadosXdireccion", method=RequestMethod.GET)
		public ResponseEntity<Iterable<Empleado>> getAllempleadosByDireccion(@RequestParam String direccion) {
			System.out.println(direccion);
			Iterable<Empleado> allEmpleados = empleadoRepository.findAllByDireccion(direccion) ;
		return new ResponseEntity<>(allEmpleados, HttpStatus.OK);
		}
	
	//Crear un nuevo empleado
	//@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/empleadoNuevo")
	//@RequestMapping(value="/empleados", method=RequestMethod.POST)
	public ResponseEntity<?> createEmpleado(@Valid @RequestBody Empleado empleado) {
		HttpHeaders responseHeaders = new HttpHeaders();
		count = 0;
		count = empleadoRepository.countByIdNamePassword(empleado.getIdEmpleado(),empleado.getNomCompleto(),empleado.getCodVerificacion());
		empleado = empleadoRepository.save(empleado);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(empleado.getIdEmpleado())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/empleados/empleadosNoAsistentes", method=RequestMethod.GET)
	public List<Empleado> getAllNoAsistentes(@RequestParam List<Integer> Idempleados) {
		System.out.println(Idempleados);
		return empleadoRepository.findByIdNoAsistentes(Idempleados);
	}
	
	@GetMapping("/empleados/empleadosNoAsistentesActivos")
	public List<Empleado> findByIdNoAsistentesActivos(@RequestParam List<Integer> IdEmpleados,int estado) {
		System.out.println(IdEmpleados);
		return empleadoRepository.findByNumIdYEstado(IdEmpleados,estado);
	}
	
	//Recuperar un empleado en particular	OK
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@CrossOrigin(origins = "*")
	@GetMapping("/empleados/{idEmpleado}")
	//@RequestMapping(value="/productos/{id_producto}", method=RequestMethod.GET)
	public ResponseEntity<?> getEmpleado(@PathVariable int idEmpleado) {
		 
		//verifyEquipo(id_producto);
		Optional<Empleado> e = empleadoRepository.findById(idEmpleado);
		return new ResponseEntity<> (e, HttpStatus.OK);  
	}
	
	
	//Actualizar un empleado OK
	@PutMapping("/empleados/{idEmpleado}")
	@CrossOrigin(origins = "*") 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updaterProducto(@RequestBody Empleado empleado) {
		long count = 0;
		count = empleadoRepository.countByPassword(empleado.getCodVerificacion());
		HttpHeaders responseHeaders = new HttpHeaders();
		if (count == 0)
		{
			empleado = empleadoRepository.save(empleado);
		URI newEmpleadoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(empleado.getIdEmpleado())
				.toUri();
		responseHeaders.setLocation(newEmpleadoUri);
		}
		empleadoRepository.save(empleado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Actualizar un empleado OK
		@PatchMapping("/empleados/{idEmpleado}")
		@CrossOrigin(origins = "*") 
		//@PreAuthorize("hasRole('ROLE_ADMIN')")
		public ResponseEntity<?> updateDetallePlanilla(@RequestBody Empleado empleado,
				   @PathVariable int idEmpleado) 
{
			empleadoRepository.save(empleado);
			return new ResponseEntity<>(HttpStatus.OK);  
}
	
	//Borrar un empleado OK
	@DeleteMapping(value="/empleados/{idEmpleado}")
	public ResponseEntity<?> deleteProducto(@PathVariable int idEmpleado) {
		verifyEquipo(idEmpleado);
		empleadoRepository.deleteById(idEmpleado);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/empleados/empleadoXPassword")
	public long empleadoByPassword(@RequestParam String password) 
	{
	long count  = 0;
		count = empleadoRepository.countByPassword(password);
		return count;//new ResponseEntity<> (count, HttpStatus.OK);  
	}
	
	@GetMapping("/empleados/empleadoXIdYPassword")
	public long empleadoByIdYPassword(@RequestParam int idEmpleado,String password) 
	{
	long count  = 0;
		count = empleadoRepository.countByIdYPassword(idEmpleado,password);
		return count;//new ResponseEntity<> (count, HttpStatus.OK);  
	}
	
}
