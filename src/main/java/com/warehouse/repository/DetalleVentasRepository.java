package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.ventas.DetalleVentas;
import com.warehouse.domain.ventas.Ventas;


public interface DetalleVentasRepository extends CrudRepository<DetalleVentas, Long>{

	@Query("select v from DetalleVentas v")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<DetalleVentas> findAll() ;
	
	@Query("select e from DetalleVentas  e where e.id_detalle_factura = :idDetalle")
	List<DetalleVentas> findAllById(Long idDetalle) ;
	
	@Query("select e from DetalleVentas  e where e.id_factura  = :idFactura")
	List<DetalleVentas> findByIdFactura(Long idFactura) ;
	
	
	@Query(value="select count(*) from Ventas ",nativeQuery = true)
	long countByPresentacionYUnidad(String descripcion);
	
	// ventas por cliente
	@Query("select v from DetalleVentas  v where  v.id_detalle_factura = :idDetalle")
	List<DetalleVentas> findAllByidCliente(Long idDetalle) ;
	
		
}
