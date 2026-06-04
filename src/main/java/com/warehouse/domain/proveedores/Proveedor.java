package com.warehouse.domain.proveedores;

//import java.time.LocalDate;

//import com.tk.main.entity.clientes.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores")


public class Proveedor implements Cloneable {

	@Id
	@Column(name = "IDPROVEEDOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProveedor;
	

	@Column(name="NIT")
	private String	nit	= "";   
	
	
	@Column(name="RAZON_SOCIAL")
   private String 	razonSocial;
	
	@Column(name="DIR_Proveedor")
   private String 	dirProveedor;
	
	@Column(name="CEL_CONTACTO")
   private String 	celContacto;
	
	/*
	 * @Column(name="EMAIL") private String email;
	 */
	
	@Column(name="ESTADO")
   private int 	estado = 1;
	
	@Column(name="tipoid")
	private int 	tipoId = 1;
	
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

	
	public Object clone(){
        Proveedor obj=null;
        try{
            obj=(Proveedor)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }

	
	public int getTipoId() {
		return tipoId;
	}

	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getDirProveedor() {
		return dirProveedor;
	}

	public void setDirProveedor(String dirProveedor) {
		this.dirProveedor = dirProveedor;
	}
	
}
