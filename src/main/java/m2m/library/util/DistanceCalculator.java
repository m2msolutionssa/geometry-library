package m2m.library.util;

public class DistanceCalculator {

	private static final double EQUATORIAL_EARTH_RADIUS = 6378.1370;
    private static final double DEG_TO_RAD = Math.PI / 180;
    public static final String KILOMETER = "K";
    public static final String METERS = "M";

    public DistanceCalculator() {}

    /**
	 * Retorna a distância em Metros ou Kilômetros entre dois pontos
	 * @param currentLat Latitude atual 
	 * @param currentLng Longitude atual
	 * @param previousLat Latitude anterior
	 * @param previousLng Longitude anterior
	 * @param unit Unidade de medida - (M) - Metros / (K) - Kilômetros
	 */
    public static double calculate(double currentLat, double currentLng, double previousLat, double previousLng, String unit) {
    	if(isValid(currentLat, currentLng, previousLat, previousLng, unit)) {
	    	try {
	            double dlong = (previousLng - currentLng) * DEG_TO_RAD;
	            double dlat = (previousLat - currentLat) * DEG_TO_RAD;
	            double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(currentLat * DEG_TO_RAD) * Math.cos(previousLat * DEG_TO_RAD) * Math.pow(Math.sin(dlong / 2), 2);
	            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	            double d = EQUATORIAL_EARTH_RADIUS * c;
	
	            if (METERS.equalsIgnoreCase(unit))
	                return d * 1000;
	
	            return d;
	        }
	        catch (Exception e) {
	            return 0d;
	        }
    	}
    	return 0d;
    }

	private static Boolean isValid(double currentLat, double currentLng, double previousLat, double previousLng, String unit) {
		if(currentLat != 0 && currentLng != 0 && previousLat != 0 && previousLng != 0) {
			if(unit == null) unit = KILOMETER;
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
