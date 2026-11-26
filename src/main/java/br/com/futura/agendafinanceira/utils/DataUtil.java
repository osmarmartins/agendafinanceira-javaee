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

	public static Date primeiroDiaDoMes() {
		Calendar cal = inicioDoDia();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date ultimoDiaDoMes() {
		Calendar cal = inicioDoDia();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	private static Calendar inicioDoDia() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static void main(String[] args) {

		
		System.out.println("HOJE >>>>>>>>>>>>>>>>>> " + hoje());
		System.out.println("AMANHÃ >>>>>>>>>>>>>>>> " + adicionaDias(hoje(), 1));
		System.out.println("ONTEM >>>>>>>>>>>>>>>>> " + adicionaDias(hoje(), -1));
		System.out.println();
		
		System.out.println(diaDaSemana(adicionaDias(hoje(), 3)));
	}

}
