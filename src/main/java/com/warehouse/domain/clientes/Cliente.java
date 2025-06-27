package com.warehouse.domain.clientes;

import java.time.LocalDate;

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


public class Cliente {

	@Id
	@Column(name = "IDCLIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	

	@Column(name="NIT")
	private String	nit	= "";   
	
	
	@Column(name="RAZON_SOCIAL")
   private String 	razonSocial;
	
	@Column(name="DIR_CLIENTE")
   private String 	dirCliente;
	
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_GRUPO_CLIENTE")
	private GrupoCliente grupo ;
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public String getDirCliente() {
		return dirCliente;
	}

	public void setDirCliente(String dirCliente) {
		this.dirCliente = dirCliente;
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

	public GrupoCliente getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoCliente grupo) {
		this.grupo = grupo;
	}

	public int getTipoId() {
		return tipoId;
	}

	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}

	
}
