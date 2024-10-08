package com.warehouse.domain.productos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity()
@Table(name = "UNIDAD")
public class Unidad implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_UNIDAD")
	private int idunidad;
	
	
	@Column(name = "DESCRIPCION", length = 50)
	private String descripcion;
	

public Unidad()
	{
	this.idunidad = 0;	
	this.descripcion = "";
	}
	
	
	public Unidad(int idcategoria ,String nomcategoria)
			{
		this.idunidad = idcategoria;
		this.descripcion = nomcategoria;
		
	
	}

public int getIdunidad() {
		return idunidad;
	}


	public void setIdunidad(int idunidad) {
		this.idunidad = idunidad;
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
		return this.descripcion;
	}

}
