package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import  java.util.Optional;

import com.warehouse.dto.EmpleadoDTO;




public interface EmpleadoRepositoryDTO extends CrudRepository<EmpleadoDTO, Integer>{

	@Query("select e from EmpleadoDTO e")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<EmpleadoDTO> findAll() ;
	
		
		  @Query("select e from EmpleadoDTO  e where e.idEmpleado = :idEmpleado")
		  Optional<EmpleadoDTO> findById(int idEmpleado) ;
		 
	
	@Query("select e from EmpleadoDTO  e where e.numId LIKE :numId%")
	List<EmpleadoDTO> findAllByNumId(String numId) ;
	
	@Query("select e from EmpleadoDTO  e where e.numId LIKE :numId% and e.estado = :estado")
	List<EmpleadoDTO> findAllByNumIdYEstado(String numId,int estado) ;
	
	@Query("select e from EmpleadoDTO  e where e.nomCompleto LIKE :nomCompleto%")
	List<EmpleadoDTO> findAllByNomCompleto(String nomCompleto) ;
	
	@Query("select e from EmpleadoDTO  e where e.grupo = :idgrupo")
	List<EmpleadoDTO> findAllBygrupoEmpleado(int idgrupo) ;
		
	@Query("select e from EmpleadoDTO  e where e.direccion LIKE :direccion%")
	List<EmpleadoDTO> findAllByDireccion(String direccion) ;
	
	@Query("select e from EmpleadoDTO  e where e.idEmpleado not  in (:idEmpleados)")
	List<EmpleadoDTO> findByIdNoAsistentes(List<Integer> idEmpleados) ;
	
	@Query("select e from EmpleadoDTO  e where e.idEmpleado not  in (:idEmpleados) and e.estado = :estado")
	List<EmpleadoDTO> findByNumIdYEstado(List<Integer> idEmpleados, int estado) ;
	
	
	@Query("select e from EmpleadoDTO  e where e.estado = :estado")
	List<EmpleadoDTO> findAllByEstado(int estado) ;
	
	@Query(value="select count(*) from empleados as e  where e.cod_verificacion  = :password",nativeQuery = true)
	long countByPassword(String password) ;
	
	
	  @Query(value="select count(*) from empleados as e  where  e.id_empleado  = :IdEmpleado and e.nombre_completo  = :NomCompleto "
	  		+ "and cod_verificacion = :pwd "
			 ,nativeQuery = true) 
	  long countByIdNamePassword(int IdEmpleado,String NomCompleto,String pwd);
	 
	  @Query(value="select count(*) from empleados as e  where  e.id_empleado  = :IdEmpleado and cod_verificacion = :pwd "
			 ,nativeQuery = true) 
		  long countByIdYPassword(int IdEmpleado,String pwd);

	  
}
