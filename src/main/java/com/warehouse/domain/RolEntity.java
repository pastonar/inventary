package com.warehouse.domain;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RolEntity  { 
	  
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "rolid") 
	  private Long id;
	  
	  @Column(name = "rol") 
	  @Enumerated(EnumType.STRING)
	  private ERol name;

  
	  public RolEntity(ERol name) {
		super();
		this.name = name;
	}

	public RolEntity() {
		super();
	}

	public RolEntity(Long id, ERol name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public ERol getName() {
		return name;
	}

	public void setName(ERol name) {
		this.name = name;
	}

	
	  
}
