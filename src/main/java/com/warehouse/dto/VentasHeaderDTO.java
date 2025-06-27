package com.warehouse.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.warehouse.domain.clientes.Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "facturas")

public class VentasHeaderDTO {
	
		@Id
		@Column(name = "ID_FACTURA")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int		id_factura 		= 0;

		@DateTimeFormat(pattern="dd/MM/yyyy")
		@Temporal(TemporalType.DATE)
		@Column(name="FECHA_FACTURA" , length = 10)
		private LocalDate	fec_factura 		= null;
		
		@Column(name="tipo_factura")
		private int 		tipoFactura    	= 0;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "idcliente")
		private Cliente 	comprador;
		
		@Column(name="TOTAL_FACTURA")
		private double 		total_factura    	= 0;


		@Column(name="TOTAL_PAGADO")
		private double  	total_pagado	 	= 0;


		@Column(name="SALDO_FACTURA")
		private double 		saldo_factura    	= 0;

		@Column(name="ESTADO_PAGO")
		private int 		estado_pago    	= 0;

		
		public VentasHeaderDTO() {
			this.id_factura = 0;
			this.fec_factura = LocalDate. now();
			this.comprador = new Cliente();
			this.total_factura = 0;
			this.total_pagado = 0;
			this.saldo_factura = 0;
			this.estado_pago = 0;
		}

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

		public int getEstado_pago() {
			return estado_pago;
		}

		public void setEstado_pago(int estado_pago) {
			this.estado_pago = estado_pago;
		}

		public Cliente getComprador() {
			return comprador;
		}

		public void setComprador(Cliente comprador) {
			this.comprador = comprador;
		}

		public int getTipoFactura() {
			return tipoFactura;
		}

		public void setTipoFactura(int tipoFactura) {
			this.tipoFactura = tipoFactura;
		}

	

}
