package com.warehouse.domain.planillas; 

public enum EstadoPlanilla {

	INICIADO ("Iniciado"),
    TERMINADO("Terminado");
	
	

    private final String label;

    EstadoPlanilla  (String label){
        this.label= label;
    }

    public String getLabel(){
        return label;
    }
}
