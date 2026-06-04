package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.warehouse.domain.sostenimiento.Sostenimiento;

public interface SostenimientoRepository extends CrudRepository<Sostenimiento, Integer> 
{
	@Query("select p from Sostenimiento  p  where p.idSostenimiento = :idSostenimiento")
	Optional<Sostenimiento> findById(int idSostenimiento) ;
	
	@Query("select s from Sostenimiento  s where s.responsable.numId LIKE :numId%")
	List<Sostenimiento> findAllBynit(String numId) ;
	
	@Query("select p from Sostenimiento  p where p.frenteTrabajo LIKE :frenteTrabajo%")
	List<Sostenimiento> findAllByfrenteTrabajo(String frenteTrabajo) ;
	
	@Query("select p from Sostenimiento  p where p.responsable.nomCompleto LIKE :nomCompleto%")
	List<Sostenimiento> findAllBynomCompleto(String nomCompleto) ;
	
	/*
	@Query("select p from Sostenimiento  p where p.responsable.grupo.idGrupoCliente = :idgrupo")
	List<Sostenimiento> findAllBygrupoEmpleado(int idgrupo) ;
	
	
	 * @Query("select p from Sostenimiento  p where p.responsable.grupo.idGrupoCliente   in (:idSostenimiento)"
	 * ) List<Sostenimiento> findAllBygrupoEmpleado(List<Integer> idSostenimiento) ;
	 */
	
	@Query(" select count(*) from Sostenimiento c" )
	long countByPresentacionYUnidad(String descripcion);
	
}
