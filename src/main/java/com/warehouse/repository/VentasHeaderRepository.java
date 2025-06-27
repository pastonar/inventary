package com.warehouse.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.warehouse.dto.VentasDto;
import com.warehouse.dto.VentasHeaderDTO;

public interface VentasHeaderRepository extends CrudRepository<VentasHeaderDTO, Long>{

	@Query("select v from Ventas v")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<VentasHeaderDTO> findAll() ;
	
	@Query("select e from Ventas  e where e.id_factura = :idFactura")
	List<VentasHeaderDTO> findAllById(Long idFactura) ;
	
						
	@Query(value="select count(*) from facturas ",nativeQuery = true)
	long countByPresentacionYUnidad(String descripcion);
	
	
		@Query("select v from VentasHeaderDTO  v where tipoFactura = :tipoFactura and comprador.idCliente = :idCliente")
		List<VentasHeaderDTO> findAllByidCliente(int tipoFactura,Long idCliente) ;
		
		// actualizar existencias del productos 
		@Modifying
		@Transactional	
		@Query(value="update productos set  existencias = ROUND(existencias- :cantidadVendida / cantidad,1) where id_producto = :idProducto ",nativeQuery = true)
					void updateExistenciasProductos(Long idProducto,double cantidadVendida) ;
				
	
					/*
					 * @Query(value="SELECT f.*,c.razon_social as cliente " +
					 * "FROM facturas as f,clientes as c " +
					 * "where f.id_cliente = c.idcliente and f.id_cliente = :idCliente",nativeQuery
					 * = true)
					 */
		
		
		@Query(value="SELECT f.*,c.razon_social as cliente "
				+ "FROM facturas as f,clientes as c "
				+ "where f.id_cliente = c.idcliente and f.id_cliente = :idCliente and tipo_factura = :tipoFactura",nativeQuery = true)
		List<VentasHeaderDTO> findAllByidCliente1(int tipoFactura,Long idCliente) ; 

		/*
		 * @Query(value="SELECT f.*,c.razon_social as nombre_cliente " +
		 * "FROM facturas as f,clientes as c " +
		 * "where fecha_factura between :date1 and :date2 " ,nativeQuery = true)
		 */
		
		@Query("select v from VentasHeaderDTO  v where   tipoFactura = :tipoFactura and fec_factura between :date1 and :date2 ")
		
		
		List<VentasHeaderDTO> findAllByfechas(int tipoFactura,LocalDate date1,LocalDate date2);
		
		
		/*
		 * @Query(value="SELECT f.*,c.razon_social as nombre_cliente " +
		 * "FROM facturas as f,clientes as c " +
		 * "where fecha_factura between :date1 and :date2 " +
		 * "and f.id_cliente = c.idcliente and f.id_cliente = :idCliente" ,nativeQuery =
		 * true)
		 */
		
		@Query("select v from VentasHeaderDTO  v where  tipoFactura = :tipoFactura and comprador.idCliente = :idCliente and "
				+ "fec_factura between :date1 and :date2 ")
			List<VentasHeaderDTO> findAllByClienteFechas(int tipoFactura,Long idCliente,LocalDate date1,LocalDate date2);
		
		@Modifying
		@Transactional	
		@Query(value="update facturas set estado_pago = 1 where ID_FACTURA = :set",nativeQuery = true)
		int updateventa(int set);
		//update facturas set estado_pago = 1 where ID_FACTURA in (61,62,63,64)
		
}
