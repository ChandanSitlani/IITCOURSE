package models;

public class Booking {
	protected int OrderNumber;
	protected float price;
	protected int user_id;
	protected String cardNumber,fromAP,toAP;
	protected int flightNum;
	boolean isreturn;
	protected String stdate;
	public Booking(int orderNumber, float price, int user_id, String cardNumber, String fromAP, String toAP,
			int flightNum, boolean isreturn, String stdate) {
		super();
		OrderNumber = orderNumber;
		this.price = price;
		this.user_id = user_id;
		this.cardNumber = cardNumber;
		this.fromAP = fromAP;
		this.toAP = toAP;
		this.flightNum = flightNum;
		this.isreturn = isreturn;
		this.stdate = stdate;
	}
	public int getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getFromAP() {
		return fromAP;
	}
	public void setFromAP(String fromAP) {
		this.fromAP = fromAP;
	}
	public String getToAP() {
		return toAP;
	}
	public void setToAP(String toAP) {
		this.toAP = toAP;
	}
	public int getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}
	public boolean isIsreturn() {
		return isreturn;
	}
	public void setIsreturn(boolean isreturn) {
		this.isreturn = isreturn;
	}
	public String getStdate() {
		return stdate;
	}
	public void setStdate(String stdate) {
		this.stdate = stdate;
	}
	public String getPayMethod(){
		try{
			return  "****"+getCardNumber().substring(getCardNumber().length()-4);
		}catch (Exception e) {
			// TODO: handle exception
			return "****";
		}
	}

	
}
