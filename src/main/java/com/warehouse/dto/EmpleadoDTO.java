package com.warehouse.dto;

import com.warehouse.domain.empleados.EstadoEmpleado;

//import java.time.LocalDate;

//import com.tk.main.entity.clientes.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleados")


public class EmpleadoDTO implements Cloneable {

	@Id
	@Column(name = "id_empleado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpleado;
	
	@Column(name="num_identificacion")
	private String	numId	= "";   
	
	@Column(name="nombre_completo")
	private String 	nomCompleto;
	
	@Column(name="direccion")
	private String 	direccion;
	
	@Column(name="cel_contacto")
	private String 	numCelular;
	
	@Column(name="cod_verificacion")
	private String 	codVerificacion;
	
	@Column(name="tipo_id")
	private int 	tipoId = 1;
   
	@Column(name="grupo_trabajo")
	private int grupo ;
	
	
	@Column(name = "estado_actividad")
	private int estado ;

	
	public String getCodVerificacion() {
		return codVerificacion;
	}

	public void setCodVerificacion(String codVerificacion) {
		this.codVerificacion = codVerificacion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getTipoId() {
		return tipoId;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	public String getNomCompleto() {
		return nomCompleto;
	}

	public void setNomCompleto(String nomCompleto) {
		this.nomCompleto = nomCompleto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}

	public Object clone(){
        EmpleadoDTO obj=null;
        try{
            obj=(EmpleadoDTO)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
	
}
