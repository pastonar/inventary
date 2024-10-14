package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.productos.EstadoProducto;
import com.warehouse.domain.productos.Producto;

public interface EstadoProductoRepository extends CrudRepository<EstadoProducto, Long> {

	@Query("select p from EstadoProducto p")
	 List<EstadoProducto> findAll() ;
	
	@Query("select e from EstadoProducto  e where e.descripcion LIKE :descripcion%")
	List<EstadoProducto> findAllByDescripcion(String descripcion) ;

}
