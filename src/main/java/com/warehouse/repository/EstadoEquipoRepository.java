package com.warehouse.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.EstadoEquipo;

public interface EstadoEquipoRepository extends CrudRepository<EstadoEquipo, Long> 
{
	
	@Query("select e from EstadoEquipo  e where e.descripcion LIKE :descripcion%")
		 List<EstadoEquipo> findAllByestadoEquipo(String descripcion) ;
	
	
	
	
}
