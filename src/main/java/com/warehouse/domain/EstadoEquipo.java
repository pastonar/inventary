package com.warehouse.domain;

import java.io.Serializable;




import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;



@Entity
@Table(name = "estadoequipo", schema = "PUBLIC")
public class EstadoEquipo implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstado")
	private Long id;

	@Column(name = "descripcion", length = 255)
	private String descripcion;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
	
	

}



//public Equipo getEquipo() {
//	return equipo;
//}
//
//
//public void setEquipo(Equipo equipo) {
//	this.equipo = equipo;
//}

//	@OneToOne
//@JoinColumn(name = "id_equipo")
//Equipo equipo;


//  @JoinColumn(name = "id", nullable = false)
/*
 * @OneToMany(mappedBy="estado", fetch=FetchType.LAZY) private List<Equipo>
 * equipos;
 * 
 * public List<Equipo> getEquipos() { return equipos; }
 * 
 * 
 * public void setEquipos(List<Equipo> equipos) { this.equipos = equipos; }
 */



 
  //@JoinColumn(name = "tutorial_id", nullable = false)

/*
 * @OneToOne
 * 
 * @JoinColumn(name = "equipo_id")
 */
/*
 * @ManyToOne(fetch = FetchType.EAGER)
 * 
 * @JoinColumn(name = "equipo_id") private Equipo equipo;
 * 
 * public Equipo getEquipo() { return equipo; }
 * 
 * 
 * public void setEquipo(Equipo equipo) { this.equipo = equipo; }
 */

