package com.warehouse.domain.clientes;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")


public class Cliente {

	@Id
	@Column(name = "ID_CLIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cliente;
	

	@Column(name="NIT")
	private String	nit	= "";   
	
	@Column(name="DV")
   private int 		dv;
   
	
	@Column(name="RAZON_SOCIAL")
   private String 	razon_social;
	
	@Column(name="DIR_CLIENTE")
   private String 	dir_cliente;
	
	@Column(name="CEL_CLIENTE")
   private String 	cel_cliente;
	
	@Column(name="EMAIL")
   private String 	email;
	
	
	@Column(name="ACTIVO")
   private Boolean 	activo = true;
   
	@Column(name="FECHA_INGRESO")
	   private LocalDate fecingreso;

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public int getDv() {
		return dv;
	}

	public void setDv(int dv) {
		this.dv = dv;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getDir_cliente() {
		return dir_cliente;
	}

	public void setDir_cliente(String dir_cliente) {
		this.dir_cliente = dir_cliente;
	}

	public String getCel_cliente() {
		return cel_cliente;
	}

	public void setCel_cliente(String cel_cliente) {
		this.cel_cliente = cel_cliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public LocalDate getFecingreso() {
		return fecingreso;
	}

	public void setFecingreso(LocalDate fecingreso) {
		this.fecingreso = fecingreso;
	}
	
	
	
	
}
