package com.warehouse.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



//import com.warehouse.domain.productos.Kardex;


import com.warehouse.dto.KardexDTO;


public interface KardexDTORepository extends CrudRepository<KardexDTO, Long> {

	@Query("select k from Kardex k")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<KardexDTO> findAll() ;
	
	@Query("select k from Kardex  k where k.articulo.descripcion LIKE :descripcion%")
	List<KardexDTO> findAllByDescripcion(String descripcion) ;
	
	@Query("select k from Kardex  k where k.articulo.id_producto = :idproducto")
	Optional<KardexDTO> findById(Long idproducto) ;
						
	@Query(value="SELECT k.*,p.descripcion as descripcion "
			+ "FROM kardex as k,productos as p "
			+ "where fecha between :date1 and :date2 "
			,nativeQuery = true)
	List<KardexDTO> findAllByfechas(LocalDate date1,LocalDate date2);
	
	@Query(value="SELECT saldo_cantidad "
			+ "FROM kardex "
			+ "where id_producto = :idProducto and fecha <= :date  order by id_mvto desc  limit 1"
			,nativeQuery = true)
	Optional<Double> saldoCantidad(Long idProducto,LocalDate date);
	//+ " and id_mvto <= :index "
	@Query(value="SELECT saldo_total "
			+ "FROM kardex "
			+ "where id_producto = :idProducto and fecha  <= :date  order by id_mvto desc  limit 1"
			,nativeQuery = true)
	Optional<Double>  saldoTotal(Long idProducto,LocalDate date); 
	
	@Query(value="SELECT f.*,p.descripcion as descripcion " 
			+ "FROM kardex as k,productos as p "
			+ "where fecha between :date1 and :date2 "
			+ "and k.articulo.id_producto = p.id_producto " 
			+ "and k.articulo.id_producto = :idProducto"
			,nativeQuery = true)
	List<KardexDTO> findAllByProductoFechas(LocalDate date1,LocalDate date2,Long idProducto);
	
	@Query(value="select count(*) from kardex as k where k.id_producto = :idProducto",nativeQuery = true)
	Optional<Long> countRecords(Long idProducto) ;	
}
