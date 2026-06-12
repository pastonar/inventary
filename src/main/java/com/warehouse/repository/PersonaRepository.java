package com.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.personas.Cliente;
import com.warehouse.domain.personas.Persona;




public interface PersonaRepository extends CrudRepository<Persona, Long>{

	@Query("select c from Persona c")
	Iterable<Persona> findAll() ;
	
		/*
		 * @Query("select c from Persona  c where c.razonSocial LIKE :descripcion%")
		 * List<Persona> findAllByRazonSocial(String descripcion) ;
		 */
	
	@Query("select e from Persona  e where e.nit LIKE :nit%")
	List<Persona> findAllBynit(String nit) ;
	
	@Query("select e from Persona  e where e.razonSocial LIKE :descripcion%")
	List<Persona> findAllByrazonSocial(String descripcion) ;
	
	@Query("select e from Cliente  e where e.grupo.idGrupoCliente = :idgrupo")
	List<Cliente> findAllBygrupoPersona(int idgrupo) ;
		
	@Query("select e from Persona  e where e.dirPersona LIKE :direccion%")
	List<Persona> findAllByDireccion(String direccion) ;
	
	@Query(" select count(*) from Persona c" )
	long countByPresentacionYUnidad(String descripcion);
	
	@Query("select c from Persona  c where c.idPersona not  in (:idPersonas)")
	List<Persona> findByIdPersonas(List<Integer> idPersonas) ;
	
}
