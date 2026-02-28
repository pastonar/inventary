package com.warehouse.controller;


import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
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


import com.warehouse.domain.Equipo;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.EquipoRepository;

import jakarta.validation.Valid;

@RestController
public class Utilities {

	 @GetMapping("/tiempoActual")
	    public long getTime() {
		 Instant now = Instant.now();
		 long timestampMillis = now.toEpochMilli(); // e.g., 1770638400000
		 System.out.println(timestampMillis);
		 	return timestampMillis;
	    }
	
	 @GetMapping("/fechaActual")
	    public LocalDateTime getLocalDateTime() {
	        return LocalDateTime.now();
	    }

//Borrar un equipo OK
/*@RequestMapping(value="/equipos/{equipoId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteEquipo(@PathVariable Long equipoId) {
	verifyEquipo(equipoId);
	equipoRepository.deleteById(equipoId);
return new ResponseEntity<>(HttpStatus.OK);
}
*/
}