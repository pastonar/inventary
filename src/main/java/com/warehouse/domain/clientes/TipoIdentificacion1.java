package com.warehouse.domain.clientes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_identificacion")
public class TipoIdentificacion1 {

private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_IDENTIFICACION")
	private int idTipoIdentificacion;
	
	
	@Column(name = "DESCRIPCION", length = 50)
	private String descripcion;


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
