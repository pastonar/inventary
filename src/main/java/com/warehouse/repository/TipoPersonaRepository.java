package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.proveedores.TipoPersona;

public interface TipoPersonaRepository extends CrudRepository<TipoPersona, Integer> {

	
	@Query("select p from TipoPersona p where idTipoPersona = :tipoId")
	Optional<TipoPersona> findById(int tipoId);
	
	
	@Query("select p from TipoPersona p")
	 List<TipoPersona> findAll() ;
	
	@Query("select e from TipoPersona  e where e.descripcion LIKE :descripcion%")
		 List<TipoPersona> findAllByDescripcion(String descripcion) ;

}
