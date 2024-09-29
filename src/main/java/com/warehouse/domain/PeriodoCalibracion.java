package com.warehouse.domain;

import java.io.Serializable;



import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "periodocalibracion")
public class PeriodoCalibracion implements Serializable { 
	  
	  private static final long serialVersionUID = 1L;
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "idPeriodo") 
	  private Long id;
	  
	  @Column(name = "descripcion", length = 50) 
	  private String descripcion;
	  
	  public Long getId() { return id; }
	  
	  public void setId(Long id) { this.id = id; }
	  
	  public String getDescripcion() { return descripcion; }
	  
	  public void setDescripcion(String descripcion) { this.descripcion =  descripcion; }
	 
	  public int hashCode() {
	        int hash = 7;
	        hash = 59 * hash + (String.valueOf(this.id) != null ? String.valueOf(this.id).hashCode() : 0);
	        return hash;
	    }
	
	 public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final PeriodoCalibracion other = (PeriodoCalibracion) obj;
	       
	        
	        if ((String.valueOf(this.id) == null) ? (String.valueOf(other.id) != null) : !String.valueOf(this.id).equals(other.id)) {
	            return false;
	        }
	        return true;
	    }
	

}
