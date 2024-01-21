package br.com.futura.agendafinanceira.utils;

public class NumberConversionUtil {
	
	public static int getIntegerOrZero(String string){
		int numberInteger;
		try {
			numberInteger = Integer.parseInt(string);
		} catch (Exception e) {
			numberInteger = 0;
		}
		return numberInteger;
	}
	
	public static double getDoubleOrZero(String string){
		double numberDouble;
		try {
			numberDouble = Double.parseDouble(string);
		} catch (Exception e) {
			numberDouble = 0;
		}
		return numberDouble;
	}
	

}
