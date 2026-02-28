package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.empleados.GrupoEmpleado;



public interface GrupoEmpleadoRepository extends CrudRepository<GrupoEmpleado, Long>{

	@Query("select p from GrupoEmpleado p")
	 List<GrupoEmpleado> findAll() ;
	
	@Query("select e from GrupoEmpleado  e where e.descripcion LIKE :descripcion%")
	List<GrupoEmpleado> findAllByDescripcion(String descripcion) ;

	//Optional<GrupoEmpleado> findById(Long grupoId);

	

	
}
