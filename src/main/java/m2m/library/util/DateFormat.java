package m2m.library.util;

public class DateFormat {

	private static final String ZERO = "0";

	/**
	 * Converte um double em tempo 
	 * @param time Tempo no formato HH:MM:SS
	 */
	public static String convertDoubleToTime(Double time) {
		String newTime = String.valueOf(time);
		String[] split = newTime.split("\\.");

		Integer hours = 0;
		Integer minutes = Integer.valueOf(split[0]);
		Integer seconds = Integer.valueOf(split[1].substring(0, split[1].length() == 1 ? 1 : 2));
		
		if(seconds >= 60) {
			seconds -= 60;
			minutes += 1;
		}
		
		if(minutes >= 60) {
			hours = minutes / 60;
			minutes = minutes % 60;
		}
		
		return formatDecimal(hours) + ":" + formatDecimal(minutes) + ":" + formatDecimal(seconds);
	}
	
	private static String formatDecimal(Integer decimal) {
		String newDecimal = String.valueOf(decimal);
		if(newDecimal.length() == 1) return ZERO + newDecimal;
		return newDecimal;
	}
	
}
