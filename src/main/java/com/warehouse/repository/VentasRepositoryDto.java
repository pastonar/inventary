package com.warehouse.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.warehouse.domain.productos.Producto;

//import com.warehouse.domain.ventas.Venta;

import com.warehouse.dto.VentasDto;


public interface VentasRepositoryDto extends CrudRepository<VentasDto, Long> {

	@Query("select p from Ventas p")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<VentasDto> findAll() ;
	
	@Query("select e from Ventas  e where e.id_factura = :idFactura")
	List<VentasDto> findAllById(Long idFactura) ;
	
						
	@Query(value="select count(*) from facturas ",nativeQuery = true)
	long countByPresentacionYUnidad(String descripcion);
	
	// ventas por cliente
	//@Modifying 
		@Query("select v from VentasDto  v where tipoFactura = -1 and v.idCliente = :idCliente")
		List<VentasDto> findAllByidCliente(Long idCliente) ;
		
		
		@Modifying
		@Transactional	
		@Query(value="update productos set "+
					 "existencias = ROUND(existencias - ((:cantidadVendida / cantidad)),1) "+
					 "where id_producto = :idProducto ",nativeQuery = true)
					void updateExistenciasProductos1(Long idProducto,double cantidadVendida) ;
		
		
		// actualizar existencias del productos
		@Modifying
		@Transactional	
		@Query(value="update productos set "+
				 "existencias = ROUND(existencias - :cantidadVendida,1) , "+
				 "saldo_cantidad = ROUND(saldo_cantidad - :cantidadVendida,1) "+
				 "where id_producto = :idProducto ",nativeQuery = true)
				void updateExistenciasProductos0(Long idProducto,double cantidadVendida) ;
		
		/*
		 * @Query(value="update productos set "+
		 * "existencias = existencias - (:cantidadVendida / cantidad) , "+
		 * "saldo_cantidad = ROUND(saldo_cantidad - :cantidadVendida,1) "+
		 * "where id_producto = :idProducto ",nativeQuery = true) void
		 * updateExistenciasProductos0(Long idProducto,double cantidadVendida) ;
		 */
				
		
		// "existencias = existencias - existencias * ROUND(:cantidadComprada/(cantidad*existencias),1) "+
		
		@Modifying
		@Transactional	
		@Query(value="update productos set "+ 
					 "existencias = ROUND((existencias - (:cantidadComprada/cantidad)),1) "+
					 "where id_producto = :idProducto ",nativeQuery = true)
					void updateExistenciasProductos(Long idProducto,double cantidadComprada);
					// existencias = 2 + (- redondear (10 + 5,1)

		
		@Modifying
		@Transactional	
		@Query(value="update productos set "+ 
					 "existencias = ROUND((existencias + :cantidadComprada),1) ,"+
					 "COSTO_PRESENTACION =:costoPresentacion ,"+
					 "PRECIO_PRESENTACION = ROUND(:costoPresentacion * (1+(PCTAJE_GANANCIA/100)),0),"+
					 "COSTO_UNIDAD = ROUND(:costoPresentacion / (existencias * :cantidadComprada),0), "+
					 "PRECIO_UNIDAD = ROUND((PRECIO_PRESENTACION / (existencias * :cantidadComprada)),0), "+
					 "saldo_cantidad = ROUND(saldo_cantidad + :cantidadComprada,1) "+
	 				"where id_producto = :idProducto ",nativeQuery = true)
					void updateExistenciasProductos3(Long idProducto,double cantidadComprada,double costoPresentacion);
		
		 
		@Modifying
		@Transactional	 
		@Query(value="update productos set "+ 
					 "PRECIO_PRESENTACION = ROUND(COSTO_PRESENTACION * (1+(PCTAJE_GANANCIA/100)),0),"+
					 "COSTO_UNIDAD = ROUND(COSTO_PRESENTACION / (existencias * cantidad),0), "+
					 "PRECIO_UNIDAD = ROUND((PRECIO_PRESENTACION / (existencias * cantidad)),0) "+
					 "where id_producto = :idProducto ",nativeQuery = true)
					void updateExistenciasProductos2(Long idProducto);
		
		
		
		@Query(value="SELECT f.*,c.razon_social as nombre_cliente "
				+ "FROM facturas as f,clientes as c "
				+ "where f.id_cliente = c.idcliente and f.id_cliente = :idCliente",nativeQuery = true)
		List<VentasDto> findAllByidCliente1(Long idCliente) ; 
		
		
		/*
		 SELECT f.*,c.razon_social as nombre_cliente 
FROM facturas as f,clientes as c
where fecha_factura between "2025-01-02" and "2025-02-02"
and f.idcliente = c.idcliente and f.idcliente = 1
		 */
		
		
		@Query(value="SELECT f.*,c.razon_social as nombre_cliente "
				+ "FROM facturas as f,clientes as c "
				+ "where fecha_factura between :date1 and :date2 "
				,nativeQuery = true)
		List<VentasDto> findAllByfechas(LocalDate date1,LocalDate date2);
		
		
		@Query(value="SELECT f.*,c.razon_social as nombre_cliente "
				+ "FROM facturas as f,clientes as c "
				+ "where fecha_factura between :date1 and :date2 "
				+ "and f.id_cliente = c.idcliente and f.id_cliente = :idCliente"
				,nativeQuery = true)
		List<VentasDto> findAllByClienteFechas(LocalDate date1,LocalDate date2,Long idCliente);
		

}
