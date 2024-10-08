package com.warehouse.domain.ventas;

public enum EstadoPago {

	PAGADO ("pagado"),
    NO_PAGADO("No pagado");
	
	private final String label;

	EstadoPago  (String label){
        this.label= label;
    }

    public String getLabel(){
        return label;
    }
	
}
