package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.empleados.EstadoEmpleado;

public interface EstadoEmpleadoRepository extends CrudRepository<EstadoEmpleado, Integer> {

	@Query("select p from EstadoEmpleado p where idEstado = :estadoId")
	Optional<EstadoEmpleado> findById(int estadoId);
	
	
	@Query("select p from EstadoEmpleado p")
	 List<EstadoEmpleado> findAll() ;
	
	@Query("select e from EstadoEmpleado  e where e.descripcion LIKE :descripcion%")
		 List<EstadoEmpleado> findAllByDescripcion(String descripcion) ;

	
}
