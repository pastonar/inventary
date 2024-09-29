package com.warehouse.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.warehouse.domain.Equipo;

public interface EquipoRepository extends CrudRepository<Equipo, Long> 
{
	//public Equipo findOne(Long Id);
// @Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	// 	Query("select * from Equipo  where codigointerno like :codigointerno||'%'")
	//@Query("select * from Equipo  where codigointerno LIKE :codigointerno%")
	
	@Query("select e from Equipo  e where e.codigoInterno LIKE :codigointerno%")
	//@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
	 List<Equipo> findAllBycodigointerno(String codigointerno) ;
	/*{
		// TODO Auto-generated method stub
		return null;
	
		//List<Equipo> findBycodigoInternoLike(String codigointerno);
	
	}*/

	
	
	
}
