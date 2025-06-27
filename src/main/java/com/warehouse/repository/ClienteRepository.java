package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.warehouse.domain.clientes.Cliente;




public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	@Query("select c from Cliente c")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<Cliente> findAll() ;
	
		/*
		 * @Query("select c from Cliente  c where c.razonSocial LIKE :descripcion%")
		 * List<Cliente> findAllByRazonSocial(String descripcion) ;
		 */
	
	@Query("select e from Cliente  e where e.nit LIKE :nit%")
	List<Cliente> findAllBynit(String nit) ;
	
	@Query("select e from Cliente  e where e.razonSocial LIKE :descripcion%")
	List<Cliente> findAllByrazonSocial(String descripcion) ;
	
	@Query("select e from Cliente  e where e.grupo.idGrupoCliente = :idgrupo")
	List<Cliente> findAllBygrupoCliente(int idgrupo) ;
		
	@Query("select e from Cliente  e where e.dirCliente LIKE :direccion%")
	List<Cliente> findAllByDireccion(String direccion) ;
	
	@Query("select c from Cliente c" )
	long countByPresentacionYUnidad(String descripcion);

	
}
