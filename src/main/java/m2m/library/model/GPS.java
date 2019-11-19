package m2m.library.model;

import java.util.ArrayList;
import java.util.List;

public class GPS {
	
    private List<Double> coordinates;

    public GPS(){}

    public GPS(double lon, double lat){
        this.coordinates = new ArrayList<Double>();
        this.coordinates.add(lon);
        this.coordinates.add(lat);
    }

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
    
}
