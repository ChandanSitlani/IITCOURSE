package models;

public class AirlineMileage {
	protected String airline;
	protected int mileage;
	public AirlineMileage(String airline, int mileage) {
		super();
		this.airline = airline;
		this.mileage = mileage;
	}
	public String getAirline() {
		return airline;
	}
	public int getMileage() {
		return mileage;
	}
	
}
