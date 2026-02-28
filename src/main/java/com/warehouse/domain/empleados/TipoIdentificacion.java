package com.warehouse.domain.empleados;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_identificacion")
public class TipoIdentificacion {

private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_id")
	private int idTipoIdentificacion;
	
	
	@Column(name = "descripcion", length = 50)
	private String descripcion;

	@Column(name = "abreviatura", length = 10)
	private String abreviatura;

	public int getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}

	public void setIdTipoIdentificacion(int idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
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
