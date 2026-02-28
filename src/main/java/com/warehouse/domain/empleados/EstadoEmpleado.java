package com.warehouse.domain.empleados;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_empleado")
public class EstadoEmpleado {

private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado_empleado")
	private int idEstado;
	
	
	@Column(name = "descripcion", length = 50)
	private String descripcion;

	@Column(name = "abreviatura", length = 10)
	private String abreviatura;

	
	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
		public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
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
