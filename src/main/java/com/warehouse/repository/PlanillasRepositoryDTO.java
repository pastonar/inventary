package com.warehouse.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.dto.PlanillasDTO;

public interface PlanillasRepositoryDTO extends CrudRepository<PlanillasDTO, Long>{
	
	@Query("select p from PlanillasDTO p where p.idPlanilla = :planillaId")
	PlanillasDTO findOne(Integer planillaId);
	
	@Query("select p from PlanillasDTO p")
	public List<PlanillasDTO> findAll();
	
	
	@Query("select p from PlanillasDTO p where p.fecElaboracion between :d1 and :d2")
	public List<PlanillasDTO> findAllByDate(LocalDate d1,LocalDate d2);
	
	@Query("select p from PlanillasDTO p where p.estado = :estado")
	public List<PlanillasDTO> findAllByEstado(int estado);
	/*
	 * @Query("select p from Planilla p where p.fecElaboracion = ?d1 ") public
	 * Iterable<PlanillasDTO> findOneByDate(LocalDate d1);
	 */
	
	
}
