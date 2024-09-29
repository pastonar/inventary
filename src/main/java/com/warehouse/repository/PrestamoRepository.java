package com.warehouse.repository;

import org.springframework.data.repository.CrudRepository;
import com.warehouse.domain.PrestamoEquipo;

public interface PrestamoRepository extends CrudRepository<PrestamoEquipo, Long> 
{
	//public Equipo findOne(Long Id);



}
