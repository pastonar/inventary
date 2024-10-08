package com.warehouse.domain.productos;

public enum EstadoProducto {

	EN_USO ("EN USO"),
    EN_DESUSO("EN DESUSO");
	
	private final String label;

    EstadoProducto  (String label){
        this.label= label;
    }

    public String getLabel(){
        return label;
    }
}
