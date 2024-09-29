package com.warehouse.domain;

import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name="users")

public class UserEntity {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	

	@Column(name="userid")
	private Long id;
	
	@Column(name="username")
	
	private String username;
	
	@Column(name="password")
	@NotEmpty
	@NotBlank
	
	private String password;
	
	@Column(name="tipoid")
	private int tipoid; 
	
	@Column(name="numid")
	private String numid;
	
	@Column(name="nombres")
	@NotEmpty
	private String firstName;
	
	@Column(name="apellidos")
	@NotEmpty
	private String lastName;

	@ManyToMany(fetch=FetchType.EAGER,targetEntity=RolEntity.class,cascade=CascadeType.PERSIST)
	@JoinTable(
			  name = "rol_user", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "rol_id"))
	
	
	private Set <RolEntity> roles;
	
	
	
	
	

public UserEntity( @NotEmpty @NotBlank String username, @NotEmpty @NotBlank String password, int tipoid,
			String numid, @NotEmpty String firstName, @NotEmpty String lastName, Set<RolEntity> roles) {
		
	
		this.username = username;
		this.password = password;
		this.tipoid = tipoid;
		this.numid = numid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}

	public UserEntity() {
	
}

	public UserEntity( @NotEmpty @NotBlank String username, @NotEmpty @NotBlank String password, int tipoid,
			String numid, @NotEmpty String firstName, @NotEmpty String lastName) {
	
		this.username = username;
		this.password = password;
		this.tipoid = tipoid;
		this.numid = numid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public int getTipoid() {
		return tipoid;
	}

	public void setTipoid(int tipoid) {
		this.tipoid = tipoid;
	}

	public String getNumid() {
		return numid;
	}

	public void setNumid(String numid) {
		this.numid = numid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	public Set<RolEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolEntity> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


   

	
	

	
	
	
	
}