package com.warehouse.domain;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "prestamos", schema = "PUBLIC")
public class PrestamoEquipo implements Serializable {

	private static final long serialVersionUID = 1L;
	 

	@Id
	@GeneratedValue
	@Column(name="idprestamo")
	private Long id;
	
	@Column(name="idresponsable")
	private Long idresponsable;
	
	@Column(name="idrecibe")
	private Long idrecibe;
	
	@Column(name="fecPrestamo")
	private LocalDateTime fecPrestamo;
	
	@Column(name="fecDevolucion")
	private LocalDateTime fecDevolucion;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdresponsable() {
		return idresponsable;
	}


	public void setIdresponsable(Long idresponsable) {
		this.idresponsable = idresponsable;
	}


	public Long getIdrecibe() {
		return idrecibe;
	}


	public void setIdrecibe(Long idrecibe) {
		this.idrecibe = idrecibe;
	}


	public LocalDateTime getFecPrestamo() {
		return fecPrestamo;
	}


	public void setFecPrestamo(LocalDateTime fecPrestamo) {
		this.fecPrestamo = fecPrestamo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int hashCode() {
	        int hash = 7;
	        hash = 59 * hash + (String.valueOf(this.id) != null ? String.valueOf(this.id).hashCode() : 0);
	        return hash;
	    }
	

	
	
	 public LocalDateTime getFecDevolucion() {
		return fecDevolucion;
	}


	public void setFecDevolucion(LocalDateTime fecDevolucion) {
		this.fecDevolucion = fecDevolucion;
	}


	@Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false; 
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final PrestamoEquipo other = (PrestamoEquipo) obj;
	       
	        
	        if ((String.valueOf(this.id) == null) ? (String.valueOf(other.id) != null) : !String.valueOf(this.id).equals(other.id)) {
	            return false;
	        }
	        return true;
	    }
		
	 
}
