package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.empleados.TipoIdentificacion;

public interface TipoIdentificacionEmpleadoRepository extends CrudRepository<TipoIdentificacion, Integer> {

	
	@Query("select p from TipoIdentificacion p where idTipoIdentificacion = :tipoId")
	Optional<TipoIdentificacion> findById(int tipoId);
	
	
	@Query("select p from TipoIdentificacion p")
	 List<TipoIdentificacion> findAll() ;
	
	@Query("select e from TipoIdentificacion  e where e.descripcion LIKE :descripcion%")
		 List<TipoIdentificacion> findAllByDescripcion(String descripcion) ;

}
