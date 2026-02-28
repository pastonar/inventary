package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.warehouse.domain.planillas.DetallePlanilla;

public interface DetallePlanillaRepository extends CrudRepository<DetallePlanilla, Integer>{

	@Query("select dp from DetallePlanilla dp where dp.idPlanilla = ?1")
	public List<DetallePlanilla> findByIdPlanilla(Integer planillaId);

	@Query("select v from DetallePlanilla v")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<DetallePlanilla> findAll() ;
	
	@Query("select e from DetallePlanilla  e where e.idDetallePlanilla = :idDetalle")
	List<DetallePlanilla> findAllById(Integer idDetalle) ;
	
	@Query("select e from DetallePlanilla  e where e.idPlanilla  = :idFactura")
	List<DetallePlanilla> findByIdFactura(Long idFactura) ; 
	
	@Query("select e from DetallePlanilla  e where e.idPlanilla  in (:idFacturas)")
	//@Query(value="select * from detalle_facturas  as e where e.id_factura  in  (:idFacturas)",nativeQuery = true)

	List<DetallePlanilla>findByIdPlanillas (List<Integer> idFacturas) ;
	
	@Query(value="select count(*) from Planilla ",nativeQuery = true)
	long countByPresentacionYUnidad(String descripcion);
	
	// ventas por cliente
	@Query("select p from DetallePlanilla  p where  p.idDetallePlanilla = :idDetalle")
	List<DetallePlanilla> findAllByidCliente(Long idDetalle) ;
	
}
