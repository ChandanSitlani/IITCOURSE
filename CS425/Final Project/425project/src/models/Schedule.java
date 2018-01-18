package models;


public class Schedule {
	protected String acode;
	protected int flightNumber;
	protected String from_airport,to_airport;
	protected int mileage;
	protected String schedule_date;
	protected String fromTime,toTime;
	protected int firstClassCapLeft,econClassCapLeft;
	protected float firstClassPrice,econClassPrice;
	protected int passdate;
	protected String airName;
	
	protected boolean bookfirstclass;

	
	public Schedule(String acode, int flightNumber, String from_airport, String to_airport, int mileage,
			String schedule_date, String fromTime, String toTime, int firstClassCapLeft, int econClassCapLeft,
			float firstClassPrice, float econClassPrice, int passdate, String airName) {
		super();
		this.acode = acode;
		this.flightNumber = flightNumber;
		this.from_airport = from_airport;
		this.to_airport = to_airport;
		this.mileage = mileage;
		this.schedule_date = schedule_date;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.firstClassCapLeft = firstClassCapLeft;
		this.econClassCapLeft = econClassCapLeft;
		this.firstClassPrice = firstClassPrice;
		this.econClassPrice = econClassPrice;
		this.passdate = passdate;
		this.airName = airName;
		checkCap();
	}

	private void checkCap(){
		if(econClassCapLeft<=0)
			bookfirstclass=true;
		else
			bookfirstclass=false;
	}
	
	public String getAcode() {
		return acode;
	}
	public void setAcode(String acode) {
		this.acode = acode;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFrom_airport() {
		return from_airport;
	}
	public void setFrom_airport(String from_airport) {
		this.from_airport = from_airport;
	}
	public String getTo_airport() {
		return to_airport;
	}
	public void setTo_airport(String to_airport) {
		this.to_airport = to_airport;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getSchedule_date() {
		return schedule_date;
	}
	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getFromTimeWo(){
		return fromTime.split("\\+|-")[0];
	}
	public String getToTime() {
		return toTime;
	}
	public String getToTimeWo(){
		return toTime.split("\\+|-")[0];
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public int getFirstClassCapLeft() {
		return firstClassCapLeft;
	}
	public void setFirstClassCapLeft(int firstClassCapLeft) {
		this.firstClassCapLeft = firstClassCapLeft;
	}
	public int getEconClassCapLeft() {
		return econClassCapLeft;
	}
	public void setEconClassCapLeft(int econClassCapLeft) {
		this.econClassCapLeft = econClassCapLeft;
	}
	public float getFirstClassPrice() {
		return firstClassPrice;
	}
	public void setFirstClassPrice(float firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}
	public float getEconClassPrice() {
		return econClassPrice;
	}
	public void setEconClassPrice(float econClassPrice) {
		this.econClassPrice = econClassPrice;
	}
	public int getPassdate() {
		return passdate;
	}
	public void setPassdate(int passdate) {
		this.passdate = passdate;
	}

	public String getAirName() {
		return airName;
	}

	public void setAirName(String airName) {
		this.airName = airName;
	}

	public boolean isBookfirstclass() {
		return bookfirstclass;
	}

	public void setBookfirstclass(boolean bookfirstclass) {
		this.bookfirstclass = bookfirstclass;
	}
	
	public float getSelectPrice(){
		return bookfirstclass?firstClassPrice:econClassPrice;
	}
	
}
