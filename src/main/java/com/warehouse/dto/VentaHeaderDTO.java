package com.warehouse.dto;

public class VentaHeaderDTO {
	
	private int		tipoFactura	= 0;
	
	private int		id_factura 	= 0;
	
	private int 	estado_pago = 0;

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public int getEstado_pago() {
		return estado_pago;
	}

	public void setEstado_pago(int estado_pago) {
		this.estado_pago = estado_pago;
	}

	public int getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(int tipoFactura) {
		this.tipoFactura = tipoFactura;
	}
	
	
	
}
