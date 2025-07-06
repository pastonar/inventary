package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.warehouse.domain.productos.Presentacion;
import com.warehouse.domain.productos.Unidad;

public interface PresentacionRepository extends CrudRepository<Presentacion, Long>{
	@Query("select p from Presentacion p")
	 List<Presentacion> findAll() ;
	
	@Query("select e from Presentacion  e where e.descripcion LIKE :descripcion%")
	List<Presentacion> findAllByDescripcion(String descripcion) ;

	@Query("select e from Presentacion  e where e.descripcion = :descripcion")
	List<Presentacion> findByDescripcion1(String descripcion) ;
	
	@Query("select e from Presentacion  e where e.descripcion = :descripcion")
	Presentacion findByDescripcion(String descripcion) ;
	 
	//SELECT count(*) FROM inventario.presentacion as e where descripcion like "v20*";
	//@Query(value=" SELECT count(*) from presentacion   where ucase(descripcion) = ':descripcion'",nativeQuery = true)
	@Query(" SELECT count(*) from Presentacion  e where e.descripcion = :descripcion")
	long countByDescripcionIgnoreCase(String descripcion);
	
}
