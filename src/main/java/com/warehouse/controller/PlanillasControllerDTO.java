package com.warehouse.controller;

import java.net.URI;
import java.net.http.HttpRequest;
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

import com.warehouse.dto.PlanillasDTO;
import com.warehouse.exception.ResourceNotFoundException;

import com.warehouse.repository.PlanillasRepositoryDTO;


	@RestController
	@CrossOrigin(origins = "*")
public class PlanillasControllerDTO {

		@Autowired
		private PlanillasRepositoryDTO planillasRepositoryDTO;
		
		
		
		protected void verifyPlanillaDTO(Integer planillaId) throws
		 ResourceNotFoundException { PlanillasDTO planilla =
		 planillasRepositoryDTO.findOne(planillaId); 
		 if (planilla == null) 
		 { throw new
		 ResourceNotFoundException("planilla with id " + planillaId + " not found"); 
		 }
		  }
		  
			
			  @PreAuthorize("hasRole('ROLE_ADMIN')")
			  
			  @GetMapping("/planillasDTO/{planillaId}") 
			  public ResponseEntity<?> getPlanillaDTO(@PathVariable Integer planillaId) 
			  {
			  verifyPlanillaDTO(planillaId); 
			  PlanillasDTO p = planillasRepositoryDTO.findOne(planillaId); return new ResponseEntity<>(p,
			  HttpStatus.OK); }
			 
		 
		 
		 // recuperar todas las planillas
		  
			/*
			 * @PreAuthorize("hasRole('ROLE_ADMIN')")
			 * 
			 * @GetMapping(value = "/planillasDTO") public
			 * ResponseEntity<List<PlanillasDTO>> getAllPlanillasDTO() { List<PlanillasDTO>
			 * lAllPlanillas = planillasRepositoryDTO.findAll(); return new
			 * ResponseEntity<>(lAllPlanillas, HttpStatus.OK); }
			 */
		  // obtener planillas por rango de fecha
		  
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  @GetMapping("/planillasDTO/planillaDTOXFecha") 
		  ResponseEntity<List<PlanillasDTO>> 
		  getAllPlanillasDTOByDates(@RequestParam LocalDate fecha1,@RequestParam LocalDate fecha2) { 
			  List<PlanillasDTO> lAllPlanillas =
			  planillasRepositoryDTO.findAllByDate(fecha1, fecha2); 
			  return new ResponseEntity<>(lAllPlanillas, HttpStatus.OK); }
		  
		 
 // obtener planillas por estado
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  @GetMapping("/planillasDTO/planillaDTOXEstado") 
		  ResponseEntity<List<PlanillasDTO>> 
		  getAllPlanillasDTOByEstado(@RequestParam int estado) { 
		  List<PlanillasDTO> allPlanillas = planillasRepositoryDTO.findAllByEstado(estado); 
			  return new ResponseEntity<>(allPlanillas, HttpStatus.OK); }
		  
		 // agregar una nueva planilla
		  @CrossOrigin(origins = "*")
		  @PreAuthorize("hasRole('ROLE_ADMIN')")
		  @PostMapping("/planillasDTO/nuevaPlanilla") 
		  public ResponseEntity<?> createPlanillasDTO(@RequestBody PlanillasDTO planilla) {
		 
		  HttpHeaders responseHeaders = new HttpHeaders(); 
		  planillasRepositoryDTO.save(planilla); 
		  System.out.println(planilla.getIdPlanilla());
		  System.out.println(planilla.getAsistentes().size());
		  URI newPlanillaUri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(planilla.getIdPlanilla())
				  .toUri();
		  responseHeaders.set("idPlanilla",planilla.getIdPlanilla()+"");
		  responseHeaders.setLocation(newPlanillaUri);
			/*
			 * HttpRequest request = HttpRequest.newBuilder()
			 * .uri(URI.create("http://example.com")) .header("X-Custom-Header",
			 * "CustomHeaderValue") // Add custom header .build();
			 */
		  //return new ResponseEntity<>(null, responseHeaders,HttpStatus.CREATED); 
		  /*return ResponseEntity.ok()
				  .headers(responseHeaders)
				  .body(newPlanillaUri);*/
		  return ResponseEntity.ok(planilla.getIdPlanilla());
		  }
		 
		  // modificar una planilla
			
			  @PreAuthorize("hasRole('ROLE_ADMIN')")
			  
			  @PutMapping("/planillasDTO/{planillaId}") //@RequestMapping(value = public
			  ResponseEntity<?> 
			  updateplanillaDTO1(@RequestBody PlanillasDTO planilla, @PathVariable Integer planillaId) 
			  { // Save the entity
			  verifyPlanillaDTO(planillaId); planillasRepositoryDTO.save(planilla); return
			  new ResponseEntity<>(HttpStatus.OK); }
			 
			
			  @DeleteMapping("/planillasDTO/{planillaId}")
			  
			  @PreAuthorize("hasRole('ROLE_ADMIN')")  
			  public ResponseEntity<?> deleteplanillasDTO(@PathVariable Integer planillaId) {
			  verifyPlanillaDTO(planillaId); 
			  planillasRepositoryDTO.deleteById((long) planillaId); 
			  return new ResponseEntity<>(HttpStatus.OK); }
			 
		 

	}
	

