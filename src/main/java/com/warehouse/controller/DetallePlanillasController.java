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

import com.warehouse.domain.empleados.EstadoEmpleado;
import com.warehouse.domain.planillas.*;
import com.warehouse.repository.DetallePlanillaRepository;

import jakarta.validation.Valid;

@RestController
public class DetallePlanillasController {

	@Autowired
	private DetallePlanillaRepository detalleplanillaRepository;
	
	@GetMapping("/Planillas/{IdPlanilla}/detallePlanilla")
	public List<DetallePlanilla> getAlldetallePlanilla(@PathVariable int IdPlanilla) {
	
		return detalleplanillaRepository.findByIdPlanilla(IdPlanilla);
	}
	@GetMapping(value="/Planillas/detallePlanilla")
	public List<DetallePlanilla> getAlldetallePlanilla(@RequestParam List<Integer> IdPlanillas) {
		System.out.println(IdPlanillas);
		return detalleplanillaRepository.findByIdPlanillas(IdPlanillas);
	}
	
	
	//Crear un nuevo equipo
	@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/nuevodetallePlanilla")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createdetallePlanilla(@Valid @RequestBody DetallePlanilla detalleplanilla) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
			detalleplanilla = detalleplanillaRepository.save(detalleplanilla);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(detalleplanilla.getIdDetallePlanilla())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}
	
	//Actualizar un empleado OK
	
	@PatchMapping("/planillas/detallePlanilla/{idDetallePlanilla}")
		@CrossOrigin(origins = "*") 
		public ResponseEntity<?> updateDetallePlanilla(@RequestBody DetallePlanilla detalleplanilla,
													   @PathVariable Long idDetallePlanilla) 
		{
			detalleplanillaRepository.save(detalleplanilla);
			return new ResponseEntity<>(HttpStatus.OK);  
		}
		
		
}
