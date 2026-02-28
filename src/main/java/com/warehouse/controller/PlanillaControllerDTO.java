package com.warehouse.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.warehouse.domain.planillas.Planilla;
import com.warehouse.dto.PlanillaDTO;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.PlanillaRepository;
import com.warehouse.repository.PlanillaRepositoryDTO;


	@RestController
	@CrossOrigin(origins = "*")
public class PlanillaControllerDTO {

		@Autowired
		private PlanillaRepositoryDTO planillaRepositoryDTO;
		
		@Autowired
		private PlanillaRepository planillaRepository;
		
		protected void verifyPlanilla(Integer planillaId) throws
		 ResourceNotFoundException { PlanillaDTO planilla =
		 planillaRepositoryDTO.findOne(planillaId); 
		 if (planilla == null) 
		 { throw new
		 ResourceNotFoundException("planilla with id " + planillaId + " not found"); 
		 }
		  }
		  
		 @PreAuthorize("hasRole('ROLE_ADMIN')")
		 @GetMapping("/planillasDTO/{planillaId}")
			public ResponseEntity<?> getPlanilla(@PathVariable Integer planillaId) 
		 {
				verifyPlanilla(planillaId);
				PlanillaDTO p = planillaRepositoryDTO.findOne(planillaId);

				return new ResponseEntity<>(p, HttpStatus.OK);
			}
		 
		 
		 // recuperar todas las planillas
		  
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  
		  @GetMapping(value = "/planillasDTO") 
		  public ResponseEntity<List<PlanillaDTO>> getAllPlanillas() { 
		  List<PlanillaDTO> allPlanillas = planillaRepositoryDTO.findAll();
		  
		  return new ResponseEntity<>(allPlanillas, HttpStatus.OK); }
		  
		  // obtener planillas por rango de fecha
		  
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  @GetMapping("/planillaDTOXFecha") 
		  ResponseEntity<List<PlanillaDTO>> 
		  getAllPlanillasByDate(@RequestParam LocalDate fecha1,@RequestParam LocalDate fecha2) { 
			  List<PlanillaDTO> allPlanillas =
		  planillaRepositoryDTO.findAllByDate(fecha1, fecha2); 
			  return new ResponseEntity<>(allPlanillas, HttpStatus.OK); }
		  
		 
 // obtener planillas por estado
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  @GetMapping("/planillaDTOXEstado") 
		  ResponseEntity<List<PlanillaDTO>> 
		  getAllPlanillasByDate(@RequestParam int estado) { 
		  List<PlanillaDTO> allPlanillas = planillaRepositoryDTO.findAllByEstado(estado); 
			  return new ResponseEntity<>(allPlanillas, HttpStatus.OK); }
		  
		 // agregar una nueva planilla
		  @CrossOrigin(origins = "*")
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  @PostMapping("/planillaNueva") 
		  public ResponseEntity<?> createPlanilla(@RequestBody Planilla planilla) {
		  System.out.println(planilla);
		  HttpHeaders responseHeaders = new HttpHeaders(); 
		  planillaRepository.save(planilla); 
		  URI newPlanillaUri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(planilla.getIdPlanilla())
				  .toUri();
		  responseHeaders.setLocation(newPlanillaUri); 
		  return new ResponseEntity<>(null, responseHeaders,HttpStatus.CREATED); }
		 
		  // modificar una planilla
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  @PutMapping("/planillasDTO/{planillaId}") //@RequestMapping(value =
		  public ResponseEntity<?> updateplanilla(@RequestBody PlanillaDTO planilla, @PathVariable Integer planillaId) 
		  { // Save the entity
		  verifyPlanilla(planillaId); 
		  planillaRepositoryDTO.save(planilla); 
		  return new ResponseEntity<>(HttpStatus.OK); 
		  }
		  
		  @DeleteMapping("/planillasDTO/{planillaId}")
		  @PreAuthorize("hasRole('ROLE_ADMIN')") 
		  //@RequestMapping(value = "/planillas/{planillaId}", method = RequestMethod.DELETE) public
		  ResponseEntity<?> deleteplanilla(@PathVariable Integer planillaId) {
		  verifyPlanilla(planillaId); planillaRepositoryDTO.deleteById((long)
		  planillaId); return new ResponseEntity<>(HttpStatus.OK); }
		 

	}
	

