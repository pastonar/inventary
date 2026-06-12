package com.warehouse.domain.personas;

//import java.time.LocalDate;

//import com.tk.main.entity.clientes.Persona;

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
@Table(name = "clientes")


public class Persona implements Cloneable {

	@Id
	@Column(name = "IDCLIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona;
	

	@Column(name="NIT")
	private String	nit	= "";   
	
	
	@Column(name="RAZON_SOCIAL")
   private String 	razonSocial;
	
	@Column(name="DIR_CLIENTE")
   private String 	dirPersona;
	
	@Column(name="CEL_CONTACTO")
   private String 	celContacto;
	
	/*
	 * @Column(name="EMAIL") private String email;
	 */
	
	@Column(name="ESTADO")
   private int 	estado = 1;
	
	@Column(name="tipoid")
	   private int 	tipoId = 1;
   
	/*
	 * @Column(name="FECHA_INGRESO") private LocalDate fecingreso;
	 */
	
	
	
	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDirPersona() {
		return dirPersona;
	}

	public void setDirPersona(String dirPersona) {
		this.dirPersona = dirPersona;
	}

	public String getCelContacto() {
		return celContacto;
	}

	public void setCelContacto(String celContacto) {
		this.celContacto = celContacto;
	}

	/*
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 */

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	/*
	 * public LocalDate getFecingreso() { return fecingreso; }
	 * 
	 * public void setFecingreso(LocalDate fecingreso) { this.fecingreso =
	 * fecingreso; }
	 */

	

	public int getTipoId() {
		return tipoId;
	}

	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}

	public Object clone() throws CloneNotSupportedException{
        Persona obj=null;
        try{
            obj=(Persona)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
	
}
