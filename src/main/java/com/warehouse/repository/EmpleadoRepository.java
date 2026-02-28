package com.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import  java.util.Optional;
import com.warehouse.domain.empleados.Empleado;




public interface EmpleadoRepository extends CrudRepository<Empleado, Integer>{

	@Query("select e from Empleado e")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<Empleado> findAll() ;
	
		
		  @Query("select e from Empleado  e where e.idEmpleado = :idEmpleado")
		  Optional<Empleado> findById(int idEmpleado) ;
		 
	
	@Query("select e from Empleado  e where e.numId LIKE :numId%")
	List<Empleado> findAllByNumId(String numId) ;
	
	@Query("select e from Empleado  e where e.numId LIKE :numId% and e.estado = :estado")
	List<Empleado> findAllByNumIdYEstado(String numId,int estado) ;
	
	@Query("select e from Empleado  e where e.nomCompleto LIKE :nomCompleto%")
	List<Empleado> findAllByNomCompleto(String nomCompleto) ;
	
	@Query("select e from Empleado  e where e.grupo.idGrupoEmpleado = :idgrupo")
	List<Empleado> findAllBygrupoEmpleado(int idgrupo) ;
		
	@Query("select e from Empleado  e where e.direccion LIKE :direccion%")
	List<Empleado> findAllByDireccion(String direccion) ;
	
	@Query("select e from Empleado  e where e.idEmpleado not  in (:idEmpleados)")
	List<Empleado> findByIdNoAsistentes(List<Integer> idEmpleados) ;
	
	@Query("select e from Empleado  e where e.idEmpleado not  in (:idEmpleados) and e.estado.idEstado = :estado")
	List<Empleado> findByNumIdYEstado(List<Integer> idEmpleados, int estado) ;
	
	
	@Query("select e from Empleado  e where e.estado = :estado")
	List<Empleado> findAllByEstado(int estado) ;
	
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
