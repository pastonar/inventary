package com.warehouse.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.warehouse.repository.DetallePlanillaRepositoryDTO;
import com.warehouse.domain.planillas.DetallePlanilla;
import com.warehouse.dto.DetallePlanillaDTO;
import jakarta.validation.Valid;

@RestController
public class DetallePlanillasControllerDTO {

	@Autowired
	private DetallePlanillaRepositoryDTO detalleplanillaRepositoryDTO;
	
	@GetMapping("/PlanillasDTO/{IdPlanilla}/detallePlanillaDTO")
	public List<DetallePlanillaDTO> getdetallePlanilla(@PathVariable int IdPlanilla) {
	
		return detalleplanillaRepositoryDTO.findByIdPlanilla(IdPlanilla);
	}
	
	@GetMapping(value="/PlanillasDTO/detallePlanillaDTO")
	public List<DetallePlanillaDTO> getAlldetallePlanillas(@RequestParam List<Integer> IdPlanillas) {
		System.out.println(IdPlanillas);
		return detalleplanillaRepositoryDTO.findByIdPlanillas(IdPlanillas);
	}
	
	@PostMapping(value="/PlanillasDTO/saveGroup")
	public ResponseEntity<?> saveGroupClient(@RequestBody List<DetallePlanillaDTO> clientes) {
		HttpHeaders responseHeaders = new HttpHeaders();
		for (DetallePlanillaDTO cliente : clientes) {
			  detalleplanillaRepositoryDTO.save(cliente);
	        }
		 return new ResponseEntity<>(null, responseHeaders,HttpStatus.CREATED);
	}
	
	//Crear un nuevo equipo
	@CrossOrigin(origins = "*") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/nuevodetallePlanillaDTO")
	//@RequestMapping(value="/equipos", method=RequestMethod.POST)
	public ResponseEntity<?> createdetallePlanilla(@Valid @RequestBody DetallePlanillaDTO detalleplanilla) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
			detalleplanilla = detalleplanillaRepositoryDTO.save(detalleplanilla);
		URI newProductoUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(detalleplanilla.getIdDetallePlanilla())
				.toUri();
		responseHeaders.setLocation(newProductoUri);
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
	}
	
	@PatchMapping("/planillas/detallePlanillaDTO/{idDetallePlanilla}")
	@CrossOrigin(origins = "*") 
	public ResponseEntity<?> updateDetallePlanilla(@RequestBody DetallePlanillaDTO detalleplanilla,
												   @PathVariable Long idDetallePlanilla) 
	{
		detalleplanillaRepositoryDTO.save(detalleplanilla);
		return new ResponseEntity<>(HttpStatus.OK);  
	}
	
	
}
