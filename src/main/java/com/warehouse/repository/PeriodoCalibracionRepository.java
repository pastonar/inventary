package com.warehouse.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.PeriodoCalibracion;

public interface PeriodoCalibracionRepository extends CrudRepository<PeriodoCalibracion, Long> 
{
	
	@Query("select pc from PeriodoCalibracion  pc where pc.descripcion LIKE :descripcion%")
		 Iterable<PeriodoCalibracion> findAllBydescripcion(String descripcion) ;
	
	
	
	
}
