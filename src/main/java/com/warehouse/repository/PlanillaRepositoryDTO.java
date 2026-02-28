package com.warehouse.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.dto.PlanillaDTO;

public interface PlanillaRepositoryDTO extends CrudRepository<PlanillaDTO, Long>{
	
	@Query("select p from PlanillaDTO p where p.idPlanilla = :planillaId")
	PlanillaDTO findOne(Integer planillaId);
	
	@Query("select p from PlanillaDTO p")
	public List<PlanillaDTO> findAll();
	
	
	@Query("select p from PlanillaDTO p where p.fecElaboracion between :d1 and :d2")
	public List<PlanillaDTO> findAllByDate(LocalDate d1,LocalDate d2);
	
	@Query("select p from PlanillaDTO p where p.estado = :estado")
	public List<PlanillaDTO> findAllByEstado(int estado);
	/*
	 * @Query("select p from Planilla p where p.fecElaboracion = ?d1 ") public
	 * Iterable<PlanillaDTO> findOneByDate(LocalDate d1);
	 */
	
	
}
