package com.warehouse.domain.productos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.warehouse.domain.clientes.Cliente;

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
@Table(name = "kardex")
public class Kardex {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_mvto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int		id_mvto 		= 0;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODUCTO")
	private Producto articulo;
	
	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "idcliente") private Cliente cliente;
	 */
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha" , length = 10)
	private LocalDate	fecha 		= null;
	
	@Column(name="descripcion")
	private String	descripcion	= ""; 
	
	@Column(name="tipo")
	private double 	tipo	= 0;
	
	@Column(name="total")
	private double 	total	= 0;
	
	@Column(name="cantidad")
	private double 	cantidad	= 0;
	
	@Column(name="debe")
	private double 	debe	= 0;
	
	@Column(name="haber")
	private double 	haber	= 0;
	
	@Column(name="saldo_cantidad")
	private double 	saldoCantidad	= 0;

	@Column(name="saldo_total")
	private double 	saldoTotal	= 0;

	@Column(name="precio_unitario")
	private double 	precioUnitario	= 0;
	
	@Column(name="indice_manual")
	private double 	indiceManual	= 0;
	
	public Kardex() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kardex(int id_mvto, Producto articulo, LocalDate fecha, String descripcion, double tipo, double total,
			double cantidad, double debe, double haber, double saldoCantidad , double saldoTotal) {
		super();
		this.id_mvto = id_mvto;
		this.articulo = articulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.total = total;
		this.cantidad = cantidad;
		this.debe = debe;
		this.haber = haber;
		this.saldoCantidad = saldoCantidad;
		this.saldoTotal = saldoTotal;
	}

	public int getId_mvto() {
		return id_mvto;
	}

	public void setId_mvto(int id_mvto) {
		this.id_mvto = id_mvto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getTipo() {
		return tipo;
	}

	public void setTipo(double tipo) {
		this.tipo = tipo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getDebe() {
		return debe;
	}

	public void setDebe(double debe) {
		this.debe = debe;
	}

	public double getHaber() {
		return haber;
	}

	public void setHaber(double haber) {
		this.haber = haber;
	}

	

	
	public Producto getArticulo() {
		return articulo;
	}

	public void setArticulo(Producto articulo) {
		this.articulo = articulo;
	}

	public double getSaldoCantidad() {
		return saldoCantidad;
	}

	public void setSaldoCantidad(double saldoCantidad) {
		this.saldoCantidad = saldoCantidad;
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	
	
	public double getIndiceManual() {
		return indiceManual;
	}

	public void setIndiceManual(double indiceManual) {
		this.indiceManual = indiceManual;
	}

	
	
	/*
	 * public Cliente getCliente() { return cliente; }
	 * 
	 * public void setCliente(Cliente cliente) { this.cliente = cliente; }
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kardex other = (Kardex) obj;
		if (id_mvto != other.id_mvto)
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
