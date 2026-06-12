package com.warehouse.domain.personas;



import jakarta.persistence.FetchType;

//import java.time.LocalDate;

//import com.tk.main.entity.clientes.Cliente;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



public class Cliente extends Persona implements Cloneable {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_GRUPO_CLIENTE")
	private GrupoTrabajo grupo ;
	
	
	
	public GrupoTrabajo getGrupo() {
		return grupo;
	}



	public void setGrupo(GrupoTrabajo grupo) {
		this.grupo = grupo;
	}



	public Object clone() throws CloneNotSupportedException{
        Cliente obj=null;
        obj=(Cliente)super.clone();
        return obj;
    }
	
}
