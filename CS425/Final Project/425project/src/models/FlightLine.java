package models;


public class FlightLine {
	private String fly_date;
	private String fly_time,arrive_date,arrive_time;
	private int flightCount;
	private String line;
	private String flights;
	private float price;
	private String length;
	

	public FlightLine(String fly_date, String fly_time, String arrive_date, String arrive_time, int flightCount,
			String line, String flights, float price, String length) {
		super();
		this.fly_date = fly_date;
		this.fly_time = fly_time;
		this.arrive_date = arrive_date;
		this.arrive_time = arrive_time;
		this.flightCount = flightCount;
		this.line = line;
		this.flights = flights;
		this.price = price;
		this.length = length;
	}

	public String getFly_date() {
		return fly_date;
	}

	public void setFly_date(String fly_date) {
		this.fly_date = fly_date;
	}

	public String getFly_time() {
		return fly_time;
	}public String getFlyTime_wo(){
		return fly_time.split("\\+|-")[0];
	}

	public void setFly_time(String fly_time) {
		this.fly_time = fly_time;
	}

	public String getArrive_date() {
		return arrive_date;
	}
	public String getArrive_time_wo(){
		return arrive_time.split("\\+|-")[0];
	}

	public void setArrive_date(String arrive_date) {
		this.arrive_date = arrive_date;
	}

	public String getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}

	public int getFlightCount() {
		return flightCount;
	}

	public void setFlightCount(int flightCount) {
		this.flightCount = flightCount;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getFlights() {
		return flights;
	}

	public void setFlights(String flights) {
		this.flights = flights;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String[] getFlightS(){
		return flights.split("->");
	}
	
	public String[] getDates(){
		return fly_date.split("->");
	}
	public String[] getIATAs(){
		return line.split("->");
	}
	public String getDeptDate(){
		return getDates()[0];
	}
	

	
	
}
