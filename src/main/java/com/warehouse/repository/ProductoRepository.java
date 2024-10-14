package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import com.warehouse.domain.productos.Producto;

import com.warehouse.domain.productos.Producto;


public interface ProductoRepository extends CrudRepository<Producto, Long> {

	@Query("select p from Producto p")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<Producto> findAll() ;
	
	@Query("select e from Producto  e where e.descripcion LIKE :descripcion%")
	List<Producto> findAllByDescripcion(String descripcion) ;
}
