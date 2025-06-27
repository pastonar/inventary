package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

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
	
	@Query("select p from Producto  p where p.id_producto = :idproducto")
	Optional<Producto> findById(Long idproducto) ;
						
	@Query(value="select count(*) from productos as p,presentacion as pr,unidad  as u where  p.descripcion = :descripcion and p.id_presentacion=pr.id_presentacion and p.id_unidad=u.id_unidad",nativeQuery = true)
	long countByPresentacionYUnidad(String descripcion);

		
}
