package com.warehouse.domain.productos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estadoproducto")
public class EstadoProducto {

private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ESTADO_PRODUCTO")
	private int idEstadoProducto;
	
	
	@Column(name = "DESCRIPCION", length = 50)
	private String descripcion;
	

	public EstadoProducto()
	{
	this.idEstadoProducto =0;	
	this.descripcion = "";
	}


	public int getIdEstadoProducto() 
	{
	return idEstadoProducto;
	}

public void setIdEstadoProducto(int idEstadoProducto) {
	this.idEstadoProducto = idEstadoProducto;
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
