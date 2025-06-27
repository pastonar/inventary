package com.warehouse.domain.ventas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//import com.warehouse.domain.clientes.Cliente;

import com.warehouse.domain.clientes.*;

@Entity
@Table(name = "facturas")
public class Ventas {

	@Id
	@Column(name = "ID_FACTURA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		id_factura 	= 0;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FACTURA" , length = 10)
	private LocalDate	fec_factura 		= null;
	
	@ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idcliente")
	private Cliente 	comprador 	= new Cliente();
	// persist graba uno nuevo
	// detach object references an unsaved transient instance - save the transient
	// merge detach object references an unsaved transient instance - save the transient
	// refresh detach object references an unsaved transient instance - save the transient

	@Column(name="tipo_factura")
	private int 		tipoFactura    	= 0;
	
	@Column(name="TOTAL_FACTURA")
	private double 		total_factura    	= 0;


	@Column(name="TOTAL_PAGADO")
	private double  	total_pagado	 	= 0;


	@Column(name="SALDO_FACTURA")
	private double 		saldo_factura    	= 0;


	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_FACTURA")
	private List<DetalleVentas> productos_facturados = new ArrayList<DetalleVentas>();


	public int getId_factura() {
		return id_factura;
	}


	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}
	
	public LocalDate getFec_factura() {
		return fec_factura;
	}


	public void setFec_factura(LocalDate fec_factura) {
		this.fec_factura = fec_factura;
	}

	public Cliente getComprador() {
		return comprador;
	}


	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}


	

	public double getTotal_factura() {
		return total_factura;
	}


	public void setTotal_factura(double total_factura) {
		this.total_factura = total_factura;
	}


	public double getTotal_pagado() {
		return total_pagado;
	}


	public void setTotal_pagado(double total_pagado) {
		this.total_pagado = total_pagado;
	}


	public double getSaldo_factura() {
		return saldo_factura;
	}


	public void setSaldo_factura(double saldo_factura) {
		this.saldo_factura = saldo_factura;
	}


	
	  public List<DetalleVentas> getProductos_facturados() { return
	  productos_facturados; }
	  
	  
	  public void setProductos_facturados(List<DetalleVentas> productos_facturados)
	  { this.productos_facturados = productos_facturados; }


	public int getTipoFactura() {
		return tipoFactura;
	}


	public void setTipoFactura(int tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	 


}
