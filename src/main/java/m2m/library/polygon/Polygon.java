package m2m.library.polygon;

import java.util.List;

import m2m.library.model.GPS;

public class Polygon {
	
	/**
	 * Verifica se o Gps está dentro do polígono
	 * @param gps GPS do veículo 
	 * @param coordinates Polígono do ponto
	 */
	public static boolean pointInPolygon(GPS gps, List<List<List<Double>>> coordinates) {

        // ray-casting algorithm based on
        // http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
		boolean inside = false;
		
		try {
			if(coordinates != null && coordinates.size() != 0) {
		        double x = gps.getCoordinates().get(0), y = gps.getCoordinates().get(1);
		        
		        List<List<Double>> vs = coordinates.get(0);
		        for (int i = 0, j = vs.size() - 1; i < vs.size(); j = i++) {
		            double xi = vs.get(i).get(0), yi = vs.get(i).get(1);
		            double xj = vs.get(j).get(0), yj = vs.get(j).get(1);
		
		            boolean intersect = ((yi > y) != (yj > y))
		                    && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
		            if (intersect) inside = !inside;
		        }
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Coordinates inválidas");
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		}    

        return inside;
    }


}
