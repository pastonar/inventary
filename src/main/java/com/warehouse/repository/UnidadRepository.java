package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.productos.EstadoProducto;
import com.warehouse.domain.productos.Unidad;

public interface UnidadRepository extends CrudRepository<Unidad, Long>{
	@Query("select p from Unidad p")
	 List<Unidad> findAll() ;
	
	@Query("select e from Unidad  e where e.descripcion LIKE :descripcion%")
	List<Unidad> findAllByDescripcion(String descripcion) ;

}
