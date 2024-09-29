package com.warehouse.dto;

import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserEntityDto {

	
	@NotEmpty
	@NotBlank
	private String username;
	

	@NotEmpty
	@NotBlank
	@JsonIgnore
	private String password;
	
	
	private int tipoid;
	
	
	private String numid;
	
	
	@NotEmpty
	private String firstName;
	
	
	@NotEmpty
	private String lastName;


	private Set <String> roles;
	
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


	public UserEntityDto(@NotEmpty @NotBlank String username, @NotEmpty @NotBlank String password, int tipoid,
			String numid, @NotEmpty String firstName, @NotEmpty String lastName) {
	
		this.username = username;
		this.password = password;
		this.tipoid = tipoid;
		this.numid = numid;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public Set <String> getRoles() {
		return roles;
	}


	public void setRoles(Set <String> roles) {
		this.roles = roles;
	}
	
	
	
	
}
	
