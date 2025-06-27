package com.warehouse.dto;

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
import com.warehouse.domain.ventas.DetalleVentas;

@Entity
@Table(name = "facturas")
public class VentasDto {

	@Id
	@Column(name = "ID_FACTURA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		id_factura 		= 0;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FACTURA" , length = 10)
	private LocalDate	fec_factura 		= null;
	
	
	  @Column(name = "idcliente") 
	  private int idCliente ;
	 
		@Column(name="TOTAL_FACTURA")
	private double 		total_factura    	= 0;


	@Column(name="TOTAL_PAGADO")
	private double  	total_pagado	 	= 0;


	@Column(name="SALDO_FACTURA")
	private double 		saldo_factura    	= 0;

	@Column(name="ESTADO_PAGO")
	private int 		estado_pago    	= 0;
	
	/*
	 * @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "ID_FACTURA") private List<DetalleVentasDto>
	 * productos_facturados = new ArrayList<DetalleVentasDto>();
	 */
	
	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FACTURA")
	private List<DetalleVentasDto> productos_facturados = new ArrayList<DetalleVentasDto>();

	
	@Column(name="tipo_factura")
	private int 		tipoFactura    	= 0;
	
	


	@Override
	public String toString() {
		return "VentasDto [id_factura=" + id_factura + ", fec_factura=" + fec_factura + ", idCliente=" + idCliente
				+ ", total_factura=" + total_factura + ", total_pagado=" + total_pagado + ", saldo_factura="
				+ saldo_factura + ", estado_pago=" + estado_pago + ", productos_facturados=" + productos_facturados
				+ ", tipoFactura=" + tipoFactura + "]";
	}


	public VentasDto() {
		super();
	}
 

	/*
	 * public VentasDto(int id_factura, LocalDate fec_factura, int idCliente, double
	 * total_factura, double total_pagado, double saldo_factura, int estado_pago,
	 * List<DetalleVentasDto> productos_facturados, int tipoFactura, int idFactura,
	 * int indiceManual) { super(); this.id_factura = id_factura; this.fec_factura =
	 * fec_factura; this.idCliente = idCliente; this.total_factura = total_factura;
	 * this.total_pagado = total_pagado; this.saldo_factura = saldo_factura;
	 * this.estado_pago = estado_pago; this.productos_facturados =
	 * productos_facturados; this.tipoFactura = tipoFactura;
	 * 
	 * }
	 */


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

	
	
	  public int getIdCliente(){ 
		  return idCliente; }
	  
	  
	  public void setIdCliente(int idCliente){ 
		  this.idCliente = idCliente; }
	 


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

	
	public List<DetalleVentasDto> getProductos_facturados() {
		return productos_facturados;
	}


	public void setProductos_facturados(List<DetalleVentasDto> productos_facturados) {
		this.productos_facturados = productos_facturados;
	}


	public int getEstado_pago() {
		return estado_pago;
	}


	public void setEstado_factura(int estado_pago) {
		this.estado_pago = estado_pago;
	}


	public int getTipoFactura() {
		return tipoFactura;
	}


	public void setTipoFactura(int tipoFactura) {
		this.tipoFactura = tipoFactura;
	}


	public void setEstado_pago(int estado_pago) {
		this.estado_pago = estado_pago;
	}
	 


}
