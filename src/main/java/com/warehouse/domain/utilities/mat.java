package com.warehouse.domain.utilities;

public class mat {


	public static double Redondear(double numero,double digitos)
{
      int cifras=(int) Math.pow(10,digitos);
      return Math.rint(numero*cifras)/cifras;
  }

	public static double porcentaje(double valor)
	{
		return valor /100;
	}

}
