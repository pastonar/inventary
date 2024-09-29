package com.warehouse.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.Bodega;

public interface BodegaRepository extends CrudRepository<Bodega, Long> 
{
	
	@Query("select b from Bodega  b where b.descripcion LIKE :descripcion%")
		 List<Bodega> findAllBydescripcion(String descripcion) ;
	
	
	
	
}
