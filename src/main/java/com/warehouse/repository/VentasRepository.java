package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import com.warehouse.domain.ventas.Venta;

import com.warehouse.domain.ventas.Ventas;


public interface VentasRepository extends CrudRepository<Ventas, Long> {

	@Query("select v from Ventas v")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<Ventas> findAll() ;
	
	@Query("select v from Ventas  v where v.id_factura = :idFactura")
	List<Ventas> findAllById(Long idFactura) ;
	
						
	@Query(value="select count(*) from facturas ",nativeQuery = true)
	long countByPresentacionYUnidad(String descripcion);
	
	// ventas por cliente
	@Query("select v from Ventas  v where comprador.idCliente = :idCliente")
	List<Ventas> findAllByidCliente(Long idCliente) ;
	
	
}
