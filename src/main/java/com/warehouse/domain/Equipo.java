package com.warehouse.domain;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.List;




import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.ano
import jakarta.validation.constraints.Size;

/*import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;*/


import jakarta.persistence.*;


@Entity
@Table(name = "equipo")
public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;
	 

	@Id
	@GeneratedValue
	@Column(name="idequipo")
	private Long id;
	
	@Column(name="descripcion")
	@Size(min=1, max = 255)
	private String descripcion;
	
	@Column(name="codigointerno")
	@Size(min=1, max = 20)
	private String codigoInterno;
	
	@Column(name="marca")
	@Size(min=0, max = 255)
	private String marca;

	
	//@Column(name="estadoequipo")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estadoequipo") 
	private EstadoEquipo estado;
	
	
	
	@Column(name="fechacompra")
	private LocalDateTime fechaCompra;
	
	@Column(name="valorinventario")
    private double  valorInventario;
	
	@Column(name="tiempocalibracion")
	private int tiempocalibracion;
	
	@Column(name="fechacalibracion")
	private LocalDateTime fechacalibracion;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "periodocalibracion") 
	private PeriodoCalibracion periodoCalibracion;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bodega") 
	private Bodega bodega;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="idequipo")
	private List<PrestamoEquipo> prestamos;
	
	//private String imagen;
	
	public EstadoEquipo getEstado() 
	  { return estado; }
	  
	  
	  public void setEstado(EstadoEquipo estado) 
	  { this.estado = estado; }
	 

      public PeriodoCalibracion getPeriodoCalibracion() {
		return periodoCalibracion;
	  }


	  public void setPeriodoCalibracion(PeriodoCalibracion periodoCalibracion) {
		this.periodoCalibracion = periodoCalibracion;
	  }

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	
	public int getTiempocalibracion() {
		return tiempocalibracion;
	}


	public void setTiempocalibracion(int tiempocalibracion) {
		this.tiempocalibracion = tiempocalibracion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}


	

	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getValorInventario() {
		return valorInventario;
	}

	public void setValorInventario(double valorInventario) {
		this.valorInventario = valorInventario;
	}


	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Equipo() {
		
		this.estado = new EstadoEquipo();
	}
	
	

	
	public LocalDateTime getFechacalibracion() {
		return fechacalibracion;
	}


	public void setFechacalibracion(LocalDateTime fechacalibracion) {
		this.fechacalibracion = fechacalibracion;
	}


	public List<PrestamoEquipo> getPrestamos() {
		return prestamos;
	}


	public void setPrestamos(List<PrestamoEquipo> prestamos) {
		this.prestamos = prestamos;
	}

	
	

	public Bodega getBodega() {
		return bodega;
	}


	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}


	public int hashCode() {
	        int hash = 7;
	        hash = 59 * hash + (String.valueOf(this.id) != null ? String.valueOf(this.id).hashCode() : 0);
	        return hash;
	    }
	

	 @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false; 
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final Equipo other = (Equipo) obj;
	       
	        
	        if ((String.valueOf(this.id) == null) ? (String.valueOf(other.id) != null) : !String.valueOf(this.id).equals(other.id)) {
	            return false;
	        }
	        return true;
	    }
		
	 
}


//@Column(name="id")
/*
* @OneToMany(mappedBy="idEstado",fetch = FetchType.EAGER)
 @JoinColumn(name = "idEstado") private */


//List<EstadoEquipo> estados; 

//RELACION UNO A MUCHOS	
//@OneToMany(mappedBy="equipo", fetch=FetchType.LAZY)
//private List<EstadoEquipo> estados;
//
//public List<EstadoEquipo> getEquipos() {
//	return estados;
//}
//
//
//public void setEquipos(List<EstadoEquipo> estados) {
//	this.estados = estados;
//}  


//@ManyToOne(fetch = FetchType.EAGER)
//@JoinColumn(name = "estado_id") 
//EstadoEquipo estado;

//RELACION UNO A UNO	
//@OneToOne(mappedBy="equipo", fetch = FetchType.EAGER)
//EstadoEquipo  estado;




//@Column(name="tiempocalibracion")
//private int tiempoCalibracion;
//
//@Column(name="estadoequipo")
//private long  estado;


/*
 @ManyToOne(fetch = FetchType.EAGER)
 
 @JoinColumn(name = "idPeriodo")

private PeriodoCalibracion periodoCalibracion;
*/

/*
* public EstadoEquipo getEstado() { return estado; }
* 
* 
* 
* public void setEstado(EstadoEquipo estado) { this.estado = estado; }
* 
*/





/*
* public PeriodoCalibracion getPeriodoCalibracion() { return
* periodoCalibracion; }
* 
* 
* 
* public void setPeriodoCalibracion(PeriodoCalibracion periodoCalibracion) {
* this.periodoCalibracion = periodoCalibracion; }
*/

/*
* public EstadoEquipo getEstado() { return estado; }
* 
* 
* 
* public void setEstado(EstadoEquipo estado) { this.estado = estado; }
*/

