package models;

public class CreditCard {
	protected int uid;
	protected String cardNumber;
	protected short expirationDate;
	protected String cardHolder;
	protected short cvv;
	protected int billiing_address;

	


	public CreditCard(int uid, String cardNumber, short expirationDate, String cardHolder, short cvv,
			int billiing_address) {
		super();
		this.uid = uid;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cardHolder = cardHolder;
		this.cvv = cvv;
		this.billiing_address = billiing_address;
	}
	
	

	public int getBilliing_address() {
		return billiing_address;
	}

	public void setBilliing_address(int billiing_address) {
		this.billiing_address = billiing_address;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public short getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(short expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public short getCvv() {
		return cvv;
	}

	public void setCvv(short cvv) {
		this.cvv = cvv;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String toString(){
		try{
			return  "****"+getCardNumber().substring(getCardNumber().length()-4);
		}catch (Exception e) {
			// TODO: handle exception
			return "****";
		}
	}
	
	
	

}
