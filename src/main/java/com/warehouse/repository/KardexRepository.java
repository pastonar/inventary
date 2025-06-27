package com.warehouse.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import com.warehouse.domain.productos.Kardex;

import com.warehouse.domain.productos.Kardex;



public interface KardexRepository extends CrudRepository<Kardex, Long> {

	@Query("select k from Kardex k")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<Kardex> findAll() ;
	
	@Query("select k from Kardex  k where k.articulo.descripcion LIKE :descripcion%")
	List<Kardex> findAllByDescripcion(String descripcion) ;
	
	@Query("select k from Kardex  k where k.articulo.id_producto = :idProducto")
	List<Kardex> findAllByProducto(Long idProducto);
	
	
	
	@Query(value="SELECT f.*,p.descripcion as descripcion "
			+ "FROM Kardex as k,productos as p "
			+ "where fecha between :date1 and :date2 "
			+ "and k.articulo.id_producto = p.id_producto " 
			+ "and k.articulo.id_producto = :idProducto"
			,nativeQuery = true)
	List<Kardex> findAllByProductoFechas(Long idProducto,LocalDate date1,LocalDate date2);
	
		
}
