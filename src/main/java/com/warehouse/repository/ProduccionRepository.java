package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.empleados.Empleado;
import com.warehouse.domain.produccion.Produccion;
import com.warehouse.domain.sostenimiento.Sostenimiento;

public interface ProduccionRepository extends CrudRepository<Produccion, Integer> 
{
	@Query("select p from Produccion  p  where p.idProduccion = :idProduccion")
	Optional<Produccion> findById(int idProduccion) ;
	
	@Query("select s from Produccion  s where s.responsable.numId LIKE :numId%")
	List<Produccion> findAllBynit(String numId) ;
	
	@Query("select p from Produccion  p where p.frenteTrabajo LIKE :frenteTrabajo%")
	List<Produccion> findAllByfrenteTrabajo(String frenteTrabajo) ;
	
	@Query("select p from Produccion  p where p.responsable.nomCompleto LIKE :nomCompleto%")
	List<Produccion> findAllBynomCompleto(String nomCompleto) ;
	
	
	/*
	 * @Query("select p from Produccion  p where p.responsable.grupo.idGrupoEmpleado   in (:idProduccion)"
	 * ) List<Produccion> findAllBygrupoEmpleado(List<Integer> idProduccion) ;
	 */
	
	@Query(" select count(*) from Produccion c" )
	long countByPresentacionYUnidad(String descripcion);
	
}
