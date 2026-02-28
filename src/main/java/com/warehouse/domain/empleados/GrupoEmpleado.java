package com.warehouse.domain.empleados;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo_empleado")
public class GrupoEmpleado {

private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GRUPO_EMPLEADO")
	private int idGrupoEmpleado;
	 
	
	@Column(name = "DESCRIPCION", length = 50)
	private String descripcion;

	@Column(name = "abreviatura", length = 50)
	private String abreviatura;

	
	public int getIdGrupoEmpleado() {
		return idGrupoEmpleado;
	}


	public void setIdGrupoEmpleado(int idGrupoEmpleado) {
		this.idGrupoEmpleado = idGrupoEmpleado;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getAbreviatura() {
		return abreviatura;
	}


	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Object clone(){
	    Object obj=null;
	    try{
	        obj=super.clone();
	    }catch(CloneNotSupportedException ex){
	        System.out.println(" no se puede duplicar");
	    }
	    return obj;

	}


	@Override
	public String toString() {
		return descripcion ;
	}
	

}
