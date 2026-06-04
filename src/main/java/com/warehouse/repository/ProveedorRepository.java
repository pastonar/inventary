package com.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.warehouse.domain.proveedores.Proveedor;




public interface ProveedorRepository extends CrudRepository<Proveedor, Long>{

	@Query("select c from Proveedor c")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<Proveedor> findAll() ;
	
		/*
		 * @Query("select c from Proveedor  c where c.razonSocial LIKE :descripcion%")
		 * List<Proveedor> findAllByRazonSocial(String descripcion) ;
		 */
	
	@Query("select e from Proveedor  e where e.nit LIKE :nit%")
	List<Proveedor> findAllBynit(String nit) ;
	
	@Query("select e from Proveedor  e where e.razonSocial LIKE :descripcion%")
	List<Proveedor> findAllByrazonSocial(String descripcion) ;
		
	@Query("select e from Proveedor  e where e.dirProveedor LIKE :direccion%")
	List<Proveedor> findAllByDireccion(String direccion) ;
	
	@Query(" select count(*) from Proveedor c" )
	long countByPresentacionYUnidad(String descripcion);
	
	@Query("select c from Proveedor  c where c.idProveedor not  in (:idProveedors)")
	List<Proveedor> findByIdProveedors(List<Integer> idProveedors) ;
	
}
