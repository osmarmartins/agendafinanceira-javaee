package br.com.futura.agendafinanceira.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtil {
	
	public static String dataDMA(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	public static String dataAMD(Date data) {
		return new SimpleDateFormat("yyyy-MM-dd").format(data);
	}

	public static String dataHoraSegundo(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
	}

	public static String dataHora(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data);
	}

	public static String diaDaSemana(Date data) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
	}
	
	public static Date adicionaDias(Date data, int dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DATE, dias);
		data = cal.getTime();
		return data;
	}
	
	public static Date hoje() {
		return new Date();
	}
	
	public static void main(String[] args) {

		
		System.out.println("HOJE >>>>>>>>>>>>>>>>>> " + hoje());
		System.out.println("AMANHÃ >>>>>>>>>>>>>>>> " + adicionaDias(hoje(), 1));
		System.out.println("ONTEM >>>>>>>>>>>>>>>>> " + adicionaDias(hoje(), -1));
		System.out.println();
		
		System.out.println(diaDaSemana(adicionaDias(hoje(), 3)));
	}

}
