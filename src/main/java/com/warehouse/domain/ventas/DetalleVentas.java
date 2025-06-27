package com.warehouse.domain.ventas;

import com.warehouse.domain.productos.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_facturas")
public class DetalleVentas implements Cloneable{

	@Id
	@Column(name = "ID_DETALLE_FACTURA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_detalle_factura;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODUCTO")
	private Producto articulo;
	
	@Column(name = "ID_FACTURA")
	private int		id_factura 	= 0;
	
	
	@Column(name = "CANTIDAD_VENDIDA")
	private double	cantidad_vendida;
	
	@Column(name = "PRECIO_VENTA")
	private double 	precio_venta;
		
	@Column(name = "VALOR_PARCIAL")
	private double 	vr_parcial; 
	 
	
	public int getId_detalle_factura() {
		return id_detalle_factura;
	}

	public void setId_detalle_factura(int id_detalle_factura) {
		this.id_detalle_factura = id_detalle_factura;
	}

	
	
	public Producto getArticulo() {
		return articulo;
	}

	public void setArticulo(Producto articulo) {
		this.articulo = articulo;
	}

	public double getCantidad_vendida() {
		return cantidad_vendida;
	}

	public void setCantidad_vendida(double cantidad_vendida) {
		this.cantidad_vendida = cantidad_vendida;
		this.vr_parcial = this.cantidad_vendida * this.precio_venta;
	}

	public double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
		this.vr_parcial = this.cantidad_vendida * this.precio_venta;
	}

	public double getVr_parcial() {
		return vr_parcial;
	}

	public void setVr_parcial(double vr_parcial) {
		this.vr_parcial = vr_parcial;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	

	
	
}
