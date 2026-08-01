package com.warehouse.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.warehouse.domain.planillas.ReporteAsistencia;

import java.time.LocalDate;
import java.util.List;

public interface ReporteAsistenciaRepository extends CrudRepository<ReporteAsistencia, Integer> {

	
	@Query(value= "SELECT id_empleado,abreviatura,num_identificacion,nombre_completo,"+
			"count(id_empleado) as dias_trabajados "+
			"FROM  reporte_asistencia "+
			"where fecha_elaboracion between :fecha1 and :fecha2  " +
			"group by id_empleado;",nativeQuery = true)
	
	 List<ReporteAsistencia> findAllByDate(LocalDate fecha1,LocalDate fecha2);
}
