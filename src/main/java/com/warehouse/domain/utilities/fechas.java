package com.warehouse.domain.utilities;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fechas {

	 public static Date ParseFecha(String fecha)
	 {
	     SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	     Date fechaDate = null;
	     try {
	         fechaDate = formato.parse(fecha);
	     } 
	     catch (ParseException ex) 
	     {
	         System.out.println(ex);
	     }
	     return fechaDate;
	 }

	 public java.sql.Date StringToDate(java.util.Date fecha_a_convertir)
	 {
	 //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 	
	 	java.sql.Date sqlDate = new java.sql.Date(fecha_a_convertir.getTime());
	 return sqlDate;
	 }
	 
	 
}
