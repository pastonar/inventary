package com.warehouse.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "detalle_facturas")
public class DetalleVentasDTO implements Cloneable{

	@Id
	@Column(name = "ID_DETALLE_FACTURA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_detalle_factura;
	
	
    @Column(name = "ID_PRODUCTO")
	private int idProducto;
    
    @Transient
    private double 	costoPresentacion	;
    
    /* nombre completo, 
     @Column(name = "NOM_PRODUCTO")
	private String nomProducto;
     */
    
    
    @Column(name = "ID_FACTURA")
	private int idfactura;
	
	@Column(name = "CANTIDAD_VENDIDA")
	private double	cantidad_vendida;
	
	@Column(name = "PRECIO_VENTA")
	private double 	precio_venta;
	
	@Column(name = "VALOR_PARCIAL")
	private double 	valor_parcial;
	 
	
	//@Column(name="COSTO_UNIDAD")
	 @Transient
	private double 	costoUnidad	= 0;
	
	

	public int getId_detalle_factura() {
		return id_detalle_factura;
	}

	public void setId_detalle_factura(int id_detalle_factura) {
		this.id_detalle_factura = id_detalle_factura;
	}

	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	
	public int getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}

	public double getValor_parcial() {
		return valor_parcial;
	}

	public void setValor_parcial(double valor_parcial) {
		this.valor_parcial = valor_parcial;
	}

	public double getCantidad_vendida() {
		return cantidad_vendida;
	}

	public void setCantidad_vendida(double cantidad_vendida) {
		this.cantidad_vendida = cantidad_vendida;
		this.valor_parcial = this.cantidad_vendida * this.precio_venta;
	}

	public double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
		this.valor_parcial = this.cantidad_vendida * this.precio_venta;
	}

	public double getVr_parcial() {
		return valor_parcial;
	}

	public void setVr_parcial(double vr_parcial) {
		this.valor_parcial = vr_parcial;
	}

	@Override
	public String toString() {
		return "DetalleVentasDto [idProducto=" + idProducto + ", cantidad_vendida=" + cantidad_vendida
				+ ", precio_venta=" + precio_venta + ", valor_parcial=" + valor_parcial + "]";
	}

	public double getCostoPresentacion() {
		return costoPresentacion;
	}

	public void setCostoPresentacion(double costoPresentacion) {
		this.costoPresentacion = costoPresentacion;
	}

	public double getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(double costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	
	
	
}
