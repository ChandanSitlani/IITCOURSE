package models;

public class ScheduleBooking {
	private int order_number;
	private String acode;
	private int flight_number;
	private String date;
	private boolean firstclass;
	private boolean returnFlight;
	public ScheduleBooking(int order_number, String acode, int flight_number, String date, boolean firstclass,
			boolean returnFlight) {
		this.order_number = order_number;
		this.acode = acode;
		this.flight_number = flight_number;
		this.date = date;
		this.firstclass = firstclass;
		this.returnFlight = returnFlight;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public String getAcode() {
		return acode;
	}
	public void setAcode(String acode) {
		this.acode = acode;
	}
	public int getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(int flight_number) {
		this.flight_number = flight_number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isFirstclass() {
		return firstclass;
	}
	public void setFirstclass(boolean firstclass) {
		this.firstclass = firstclass;
	}
	public boolean isReturnFlight() {
		return returnFlight;
	}
	public void setReturnFlight(boolean returnFlight) {
		this.returnFlight = returnFlight;
	}
	
	
}
