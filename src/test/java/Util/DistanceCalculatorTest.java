package Util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import m2m.library.util.DistanceCalculator;

public class DistanceCalculatorTest {

	@Test
	public void calculateDistanceInKilometers() {
		double distance = DistanceCalculator.calculate(-22.9991061, -43.3557768, -22.9710091, -43.3744577, "K");
		assertEquals(3.6671357617649716, distance, 0.00001);
	}
	
	@Test
	public void calculateDistanceInMeters() {
		double distance = DistanceCalculator.calculate(-22.9991061, -43.3557768, -22.9710091, -43.3744577, "M");
		assertEquals(3667.1357617649714, distance, 0.00001);
	}
	
	@Test
	public void calculateDistanceInMetersWithOutUnit() {
		double distance = DistanceCalculator.calculate(-22.9991061, -43.3557768, -22.9710091, -43.3744577, null);
		assertEquals(3.6671357617649716, distance, 0.00001);
	}
	
	@Test
	public void calculateDistanceInMetersWithOutUnitWithCoordenadaZero() {
		double distance = DistanceCalculator.calculate(0, -43.3557768, -22.9710091, -43.3744577, null);
		assertEquals(0.0, distance, 0.00001);
	}
	
	@Test
	public void calculateDistanceInMetersWithOutUnitWithAllCoordenadaZero() {
		double distance = DistanceCalculator.calculate(0, 0, 0, 0, null);
		assertEquals(0.0, distance, 0.00001);
	}
	
}
