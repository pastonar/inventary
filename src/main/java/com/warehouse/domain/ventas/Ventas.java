package com.warehouse.domain.ventas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;



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
@Table(name = "ventas")

public class Ventas {

	@Id
	@Column(name = "ID_FACTURA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		id_factura 		= 0;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FACTURA" , length = 10)
	private Date	fec_factura 		= null;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente 	comprador 	= new Cliente();

	@Column(name="TOTAL_COMPRAS" , length = 150)
	private double		total_compras 	 	= 0;


	@Column(name="TOTAL_FACTURA")
	private double 		total_factura    	= 0;


	@Column(name="TOTAL_PAGADO")
	private double  	total_pagado	 	= 0;



	@Column(name="TOTAL_DEVUELTO" )
	private double  	total_devuelto	 	= 0;


	@Column(name="SALDO_FACTURA")
	private double 		saldo_factura    	= 0;


	@OneToMany(fetch = FetchType.LAZY)

	@JoinColumn(name = "ID_FACTURA")
	private List<Detalle_venta> productos_facturados = new ArrayList<Detalle_venta>();


	public int getId_factura() {
		return id_factura;
	}


	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}
	
	public Date getFec_factura() {
		return fec_factura;
	}


	public void setFec_factura(Date fec_factura) {
		this.fec_factura = fec_factura;
	}

	public Cliente getComprador() {
		return comprador;
	}


	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}


	public double getTotal_compras() {
		return total_compras;
	}


	public void setTotal_compras(double total_compras) {
		this.total_compras = total_compras;
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


	public double getTotal_devuelto() {
		return total_devuelto;
	}


	public void setTotal_devuelto(double total_devuelto) {
		this.total_devuelto = total_devuelto;
	}


	public double getSaldo_factura() {
		return saldo_factura;
	}


	public void setSaldo_factura(double saldo_factura) {
		this.saldo_factura = saldo_factura;
	}


	public List<Detalle_venta> getProductos_facturados() {
		return productos_facturados;
	}


	public void setProductos_facturados(List<Detalle_venta> productos_facturados) {
		this.productos_facturados = productos_facturados;
	}


}
