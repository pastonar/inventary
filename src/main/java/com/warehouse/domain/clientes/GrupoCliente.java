package com.warehouse.domain.clientes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupocliente")
public class GrupoCliente {

private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GRUPO_CLIENTE")
	private int idGrupoCliente;
	
	
	@Column(name = "DESCRIPCION", length = 50)
	private String descripcion;


	public int getIdGrupoCliente() {
		return idGrupoCliente;
	}




	public void setIdGrupoCliente(int idGrupoCliente) {
		this.idGrupoCliente = idGrupoCliente;
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
