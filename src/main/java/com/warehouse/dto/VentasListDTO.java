package com.warehouse.dto;

import java.util.ArrayList;
import java.util.List;

public class VentasListDTO {
	
	private int idList=0;
	
	private List<VentaHeaderDTO> relacion_facturas = new ArrayList<VentaHeaderDTO>();

	public int getIdList() {
		return idList;
	}

	public void setIdList(int idList) {
		this.idList = idList;
	}

	public List<VentaHeaderDTO> getRelacion_facturas() {
		return relacion_facturas;
	}

	public void setRelacion_facturas(List<VentaHeaderDTO> relacion_facturas) {
		this.relacion_facturas = relacion_facturas;
	}

	
	
	
}
