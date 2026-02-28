package com.warehouse.controller;

import java.net.URI;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.*;
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

import com.warehouse.domain.planillas.Planilla;

import com.warehouse.dto.PlanillaDTO;
import com.warehouse.exception.ResourceNotFoundException;

import com.warehouse.repository.PlanillaRepository;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
public class PlanillaController {

	@Autowired
	private PlanillaRepository planillaRepository;


	protected void verifyPlanilla(Integer planillaId) throws ResourceNotFoundException {
		Planilla planilla = planillaRepository.findOne(planillaId);
		if (planilla == null) {
			throw new ResourceNotFoundException("planilla with id " + planillaId + " not found");
		}
	}

	// recuperar todas las planillas
	/*
	 * @PreAuthorize("hasRole('ROLE_ADMIN')")
	 * 
	 * @GetMapping(value = "/planillasDTO") public
	 * ResponseEntity<Iterable<Planilla>> getAllPlanillasDTO() {
	 * Iterable<planillaDTO> allPlanillasDTO = planillaRepository.findAllDTO();
	 * 
	 * return new ResponseEntity<>(allPlanillasDTO, HttpStatus.OK); }
	 */
	
	// recuperar todas las planillas
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/planillas")
		public ResponseEntity<Iterable<Planilla>> getAllPlanillas() {
		Iterable<Planilla> allPlanillas = planillaRepository.findAll();

		return new ResponseEntity<>(allPlanillas, HttpStatus.OK);
	}

	// obtener planillas por rango de fecha
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/planillaXFecha")
	@RequestMapping(value = "/planillasXFecha", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Planilla>> getAllPlanillasByDate(@RequestParam LocalDate fecha1,
			@RequestParam LocalDate fecha2) {
		Iterable<Planilla> allPlanillas = planillaRepository.findAllByDate(fecha1, fecha2);
		return new ResponseEntity<>(allPlanillas, HttpStatus.OK);
	}
	

	// agregar una nueva planilla
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/Nuevaplanilla")
		public ResponseEntity<?> createPlanilla(@RequestBody Planilla planilla) {
		planillaRepository.save(planilla);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPlanillaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(planilla.getIdPlanilla()).toUri();
		responseHeaders.setLocation(newPlanillaUri);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	// obtener una sola planilla
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/planillas/{planillaId}")
	public ResponseEntity<?> getPlanilla(@PathVariable Integer planillaId) {

		verifyPlanilla(planillaId);
		Planilla p = planillaRepository.findOne(planillaId);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	// modificar una planilla
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/planillas/{planillaId}")
	public ResponseEntity<?> updateplanilla(@RequestBody Planilla planilla, @PathVariable Integer planillaId) {
		// Save the entity
		verifyPlanilla(planillaId);
		planillaRepository.save(planilla);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/planillas/{planillaId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	//@RequestMapping(value = "/planillas/{planillaId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteplanilla(@PathVariable Integer planillaId) {
		verifyPlanilla(planillaId);
		planillaRepository.deleteById((long) planillaId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * private PlanillaDto EntityToDto(Planilla Planilla) {
	 * 
	 * PlanillaDto planillaDto = modelMapper.map(Planilla, PlanillaDto.class);
	 * PlanillaDto.setSubmissionDate(Planilla.getSubmissionDate(),
	 * userService.getCurrentUser().getPreference().getTimezone()); return
	 * planillaDto; }
	 */

	/*
	 * private Planilla DtoToEntity(PlanillaDto planillaDto) throws ParseException {
	 * 
	 * Planilla Planilla = modelMapper.map(planillaDto, Planilla.class);
	 * 
	 * Planilla.setSubmissionDate(PlanillaDto.getSubmissionDateConverted(
	 * userService.getCurrentUser().getPreference().getTimezone()));
	 * 
	 * 
	 * if (planillaDto != null) { Planilla oldPlanilla =
	 * planillaRepository.findOne(planillaDto.getIdPlanilla());
	 * 
	 * Planilla.setRedditID(oldPlanilla.getRedditID());
	 * Planilla.setSent(oldPlanilla.isSent());
	 * 
	 * } return Planilla; }
	 */

}
