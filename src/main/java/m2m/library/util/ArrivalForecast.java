package m2m.library.util;

import java.util.List;

import m2m.library.model.GPS;

public class ArrivalForecast {

	private static final int MINUTES = 60;

	/**
	 * Retorna a distância em Metros ou Kilômetros entre coordenadas
	 * @param gpsCurrent GPS atual do veículo 
	 * @param lineString LineString da linha
	 * @param speed Velocidade atual do veículo
	 * @param polygon Polígono do ponto
	 */
	public static String forecast(GPS gpsCurrent, List<List<Double>> lineString, Double speed, List<List<List<Double>>> polygon, String unit) {
		Double distance = DistancePathCalculator.calculatorOneGps(gpsCurrent, lineString, polygon, unit);
		return calculateTime(distance, speed);
	}
	
	private static String calculateTime(Double distance, Double speed) {
		return DateFormat.convertDoubleToTime((distance / speed) * MINUTES);
	}
}
