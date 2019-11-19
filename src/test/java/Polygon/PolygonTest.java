package Polygon;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import m2m.library.model.GPS;
import m2m.library.polygon.Polygon;

public class PolygonTest {
	
	@Test
	public void pointIsInsideInPolygon() {
		Boolean pointInPolygon = Polygon.pointInPolygon(new GPS(-43.207667, -22.900097), createPolygon());
		assertEquals(Boolean.TRUE, pointInPolygon);
	}
	
	@Test
	public void pointIsNotInsideInPolygon() {
		Boolean pointInPolygon = Polygon.pointInPolygon(new GPS(-43.3557768, -22.9991061), createPolygon());
		assertEquals(Boolean.FALSE, pointInPolygon);
	}
	
	@Test
	public void pointInPolygonWithPolygonEmpty() {
		Boolean pointInPolygon = Polygon.pointInPolygon(new GPS(-43.207667, -22.900097), createPolygonEmpty());
		assertEquals(Boolean.FALSE, pointInPolygon);
	}
	
	@Test
	public void pointInPolygonWithPolygonNull() {
		Boolean pointInPolygon = Polygon.pointInPolygon(new GPS(-43.207667, -22.900097), null);
		assertEquals(Boolean.FALSE, pointInPolygon);
	}
	
	private static List<Double> createCoordinate(Double lon, Double lat) {
		List<Double> coordinate = new ArrayList<Double>();
		coordinate.add(lon);
		coordinate.add(lat);
		return coordinate;
	}
	
	private static List<List<List<Double>>> createPolygon(){
		List<List<Double>> coordinate = new ArrayList<List<Double>>();
		coordinate.add(createCoordinate(-43.20496916770935, -22.90196266226934));
		coordinate.add(createCoordinate(-43.20889592170715, -22.902071376371772));
		coordinate.add(createCoordinate(-43.209099769592285, -22.89935349766891));
		coordinate.add(createCoordinate(-43.20402503013611, -22.89905699851575));
		coordinate.add(createCoordinate(-43.203896284103394, -22.900826100526235));
		coordinate.add(createCoordinate(-43.20501208305359, -22.90087551645225));
		coordinate.add(createCoordinate(-43.20496916770935, -22.90196266226934));
		
		List<List<List<Double>>> polygon = new ArrayList<List<List<Double>>>();
		polygon.add(coordinate);
		return polygon;
	}
	
	private static List<List<List<Double>>> createPolygonEmpty(){
		List<List<Double>> coordinate = new ArrayList<List<Double>>();
		List<List<List<Double>>> polygon = new ArrayList<List<List<Double>>>();
		polygon.add(coordinate);
		return polygon;
	}
}
