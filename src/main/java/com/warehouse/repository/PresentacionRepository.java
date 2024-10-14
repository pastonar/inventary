package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.warehouse.domain.productos.Presentacion;
import com.warehouse.domain.productos.Unidad;

public interface PresentacionRepository extends CrudRepository<Presentacion, Long>{
	@Query("select p from Presentacion p")
	 List<Presentacion> findAll() ;
	
	@Query("select e from Presentacion  e where e.descripcion LIKE :descripcion%")
	List<Presentacion> findAllByDescripcion(String descripcion) ;

}
