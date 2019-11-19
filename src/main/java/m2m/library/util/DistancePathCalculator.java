package m2m.library.util;

import java.util.List;

import m2m.library.model.GPS;
import m2m.library.polygon.Polygon;

public class DistancePathCalculator {

	private static final double DOUBLE_DEFAULT = 0.0;
	public static final String KILOMETER = "K";

	/**
	 * Calcula a distância entre pontos com um gps 
	 * @param gpsCurrent GPS atual do veículo
	 * @param gpsPrevious GPS anterior do veículo 
	 * @param lineString LineString da linha
	 * @param unit Unidade de medida - (M) - Metros / (K) - Kilômetros
	 */
	public static Double calculator(GPS gpsCurrent, GPS gpsPrevious, List<List<Double>> lineString, String unit) {
		
		if(isValid(gpsCurrent, gpsPrevious, lineString, unit)) {
			Double distanceMinCurrent = Double.MAX_VALUE;
			Double distanceMinNext = Double.MAX_VALUE;
			
			int indexCurrent = 0;
			int indexNext = 0;
			
			for (int i=0; i<lineString.size(); i++) {
				Double lon = lineString.get(i).get(0);
				Double lat = lineString.get(i).get(1);
				Double distanceCurrent = DistanceCalculator.calculate(gpsCurrent.getCoordinates().get(1), gpsCurrent.getCoordinates().get(0), lat, lon, unit);
				Double distanceNext = DistanceCalculator.calculate(gpsPrevious.getCoordinates().get(1), gpsPrevious.getCoordinates().get(0), lat, lon, unit);
				
				if(distanceCurrent < distanceMinCurrent) {
					distanceMinCurrent = distanceCurrent;
					indexCurrent = i;
				}
				
				if(distanceNext < distanceMinNext) {
					distanceMinNext = distanceNext;
					indexNext = i;
				}
			}
			
			return calculateDistance(lineString, indexCurrent,indexNext, unit);
		}
		
		return DOUBLE_DEFAULT;
	}
	
	/**
	 * Calcula a distância entre pontos com um gps 
	 * @param gpsCurrent GPS atual do veículo 
	 * @param lineString LineString da linha
	 * @param polygon Polígono do ponto
	 */
	public static Double calculatorOneGps(GPS gpsCurrent, List<List<Double>> lineString, List<List<List<Double>>> polygon, String unit) {
		
		if(isValid(gpsCurrent, new GPS(), lineString, unit)) {
			Double distanceMin = Double.MAX_VALUE;
			int index = 0;
			int indexPolygon = 0;
			
			for (int i=0; i<lineString.size(); i++) {
				Double lon = lineString.get(i).get(0);
				Double lat = lineString.get(i).get(1);
				Double distance = DistanceCalculator.calculate(gpsCurrent.getCoordinates().get(1), gpsCurrent.getCoordinates().get(0), lat, lon, KILOMETER);
				
				if(distance < distanceMin) {
					distanceMin = distance;
					index = i;
				}
				
				if(indexPolygon == 0 && Polygon.pointInPolygon(new GPS(lon, lat), polygon)) {
					indexPolygon = i;
				}
			}
			
			return calculateDistance(lineString, index, indexPolygon, KILOMETER);
		}
		
		return DOUBLE_DEFAULT;
	}
	
	private static Double calculateDistance(List<List<Double>> lineString, int indexCurrent, int indexNext, String unit) {
		
		Double distanceTotal = 0.0;
		
		try {
			List<List<Double>> newLineString = lineString.subList(indexCurrent, indexNext+1);
			
			for (int i=0; i<newLineString.size(); i++) {
				if(i != 0) {
					Double lon = newLineString.get(i).get(0);
					Double lat = newLineString.get(i).get(1);
				
					Double lonPrevious = newLineString.get(i-1).get(0);
					Double latPrevious = newLineString.get(i-1).get(1);
				
					distanceTotal += DistanceCalculator.calculate(lat, lon, latPrevious, lonPrevious, unit);
				}
			}
		}catch (IllegalArgumentException e) {
			System.out.println("Coordenada não encontrada no Polygon");
		}		
		catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		}
		
		return distanceTotal;
	}
	
	private static Boolean isValid(GPS gpsCurrent, GPS gpsPrevious, List<List<Double>> lineString, String unit) {
		if(gpsCurrent != null && gpsPrevious != null && lineString != null && lineString.size() != 0) {
			if(unit == null) unit = KILOMETER;
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
