package com.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.clientes.GrupoCliente;



public interface GrupoClienteRepository extends CrudRepository<GrupoCliente, Long>{

	@Query("select p from GrupoCliente p")
	 List<GrupoCliente> findAll() ;
	
	@Query("select e from GrupoCliente  e where e.descripcion LIKE :descripcion%")
	List<GrupoCliente> findAllByDescripcion(String descripcion) ;

	//Optional<GrupoCliente> findById(Long grupoId);

	

	
}
