package com.warehouse.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.domain.planillas.Planilla;
import com.warehouse.repository.ReporteAsistenciaRepository;
import com.warehouse.domain.planillas.ReporteAsistencia;
@Controller
@CrossOrigin(origins = "*")
public class ReporteAsistenciaController {

	@Autowired
	private ReporteAsistenciaRepository reporteAsistenciaRepository;
	
	@GetMapping("/reporteAsistencia/asistentesXPeriodo") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	
	
	public ResponseEntity<Iterable<ReporteAsistencia>> 
	findAllByDate(@RequestParam LocalDate fecha1,@RequestParam LocalDate fecha2) {
		Iterable<ReporteAsistencia> allRegister = reporteAsistenciaRepository.findAllByDate(fecha1, fecha2);
		return new ResponseEntity<>(allRegister, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/reporteAsistencia/{planillaId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deletereporte(@PathVariable Integer reporteId) {
		
		reporteAsistenciaRepository.deleteById( reporteId);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
