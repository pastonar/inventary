package com.warehouse.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;




import com.warehouse.domain.planillas.Planilla;
import com.warehouse.dto.PlanillaDTO;
import java.time.LocalDate;
import java.util.List;

public interface PlanillaRepository extends CrudRepository<Planilla, Long> {

	
	@Query("select p from Planilla p where p.idPlanilla = :planillaId")
	Planilla findOne(Integer planillaId);
		
	
	
	@Query("select p from Planilla p")
	public List<Planilla> findAll();
	
	/*@Query
	public Iterable<Planilla> findByDate(Date d1,Date d2 );*/
	
	/*@Query
	public List<Planilla> findByDate1(LocalDate d1,LocalDate d2);
	@Query("select p from Planilla p where p.fecElaboracion between ?1 and ?2") 
	*/
	
	@Query("select p from Planilla p where p.fecElaboracion between :d1 and :d2")
	public List<Planilla> findAllByDate(LocalDate d1,LocalDate d2);
	
	/*
	 * @Query("select p from Planilla p where p.fecElaboracion = ?d1 ") public
	 * Iterable<Planilla> findOneByDate(LocalDate d1);
	 */
	
	
	/*
	 * @Query public List<Planilla> findAll(LocalDate d1,LocalDate d2);
	 */
	
	/*@Query
	public Iterable<Planilla> findAll(LocalDate d1,LocalDate d2);*/
}
