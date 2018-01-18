package DataBase;

import java.sql.*;
import java.util.ArrayList;


import models.*;

public class DataHelper {
	private String connectURL;
	private Connection con;
	private Statement statement;
	
	public DataHelper(String url){
		connectURL=url;
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			con=DriverManager.getConnection(connectURL, "postgres", "");
			statement=con.createStatement();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public DataHelper(){
		this("jdbc:postgresql://192.168.241.129:5433/cs425");
	}
	
	public ResultSet query(String query){
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean execute(String update) {
		try {
			return statement.executeUpdate(update)>0;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
	}
	
	public void close(){
		try {
			statement.close();
			con.close();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public boolean isEmailAddrFree(LoginInfo loginInfo){
		ResultSet result=query(Querys.checkUser(loginInfo.getUserName()));
		try {
			while(result.next()){
				boolean res =result.getInt(1)==0;
				result.close();
				return res;
			}
			result.close();
			return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
	}
	public boolean register (LoginInfo login,UserInfo user){
		return execute(Querys.register(user, login));
	}
	public boolean updateUserInfo(UserInfo info){
		return execute(Querys.updateUinfo(info));
	}
	public boolean updateLoginInfo(String password,String oldpass,int uid){
		return execute(Querys.updateLinfo(password,oldpass, uid));
	}
	public boolean insertAddress(Address addr,int uid){
		return execute(Querys.insertAddress(addr, uid));
	}
	public boolean updateAddress(Address addr, int uid){
		return execute(Querys.updateAddress(addr, uid));
	}
	public boolean deleteAddress(int uid, int aid){
		return execute(Querys.deleteAddress(uid, aid));
	}
	public boolean insertPayMethod(CreditCard card){
		return execute(Querys.insertPayMethod(card));
	}
	public boolean updateCard(CreditCard old,CreditCard card){
		return execute(Querys.updateCard(old, card));
	}
	public boolean deleteCard(CreditCard card){
		return execute(Querys.deleteCard(card));
	}
	public boolean deleteBooking(int uid, int onum){
		return execute(Querys.deleteBooking(uid, onum));
	}
	public boolean insertBooking(float price,int uid,String cardNumber,String fromAP,String toAp,int fnum,boolean ret,String date){
		return execute(Querys.insertbooking(price,uid,cardNumber,fromAP,toAp,fnum,ret,date));
	}
	public boolean deleteBookingSchedule(int oid){
		return execute(Querys.deleteBookingSchedule(oid));
	}
	
	public boolean insertBookingSchedule(Schedule schedule,boolean isret){
		return execute(Querys.insertBookingSchedule(schedule, isret));
	}
	
	public UserInfo getUserInfo(LoginInfo loginInfo){
		ResultSet result=query(Querys.userInfo(loginInfo));
		try{
			while (result.next()){
				UserInfo info= new UserInfo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
				result.close();
				return info;
			}
			result.close();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Booking> getUserBooking(int uid){
		ResultSet result=query(Querys.userBooking(uid));
		ArrayList<Booking> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(new Booking(
						result.getInt(1),
						result.getFloat(2),
						result.getInt(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getInt(7),
						result.getInt(8)==1,
						result.getString(9)));
			}
			result.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<AirlineMileage> getMileage(UserInfo info){
		ResultSet result=query(Querys.mileage(info.getUid()));
		ArrayList<AirlineMileage> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(new AirlineMileage(result.getString(1), result.getInt(2)));
			}
			result.close();
			return list;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Address> getUserAddress(UserInfo info){
		ResultSet result=query(Querys.userAddress(info.getUid()));
		ArrayList<Address> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(new Address(
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getString(7),
						result.getString(8)));
			}
			result.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<ScheduleBooking> getScheduleBooking(int oid){
		ResultSet result=query(Querys.bookingSchedule(oid));
		ArrayList<ScheduleBooking> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(new ScheduleBooking(
						result.getInt(1),
						result.getString(2),
						result.getInt(3),
						result.getString(4),
						result.getInt(5)==1,
						result.getInt(6)==1));
			}
			result.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CreditCard> getUserCards(UserInfo info){
		ResultSet result=query(Querys.userPayMethod(info.getUid()));
		ArrayList<CreditCard> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(new CreditCard(
						result.getInt(1),
						result.getString(2),
						result.getShort(3),
						result.getString(4),
						result.getShort(5),
						result.getInt(6)));
			}
			result.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<FlightLine> searchFlights(
			String from,
			String to,
			int maxTrans,
			String tsLow,
			String tsUp,
			boolean limtTotalTime,
			String totalTime,
			boolean limtTotalPrice,
			float totalPrice,
			boolean selectDate,
			String date,
			String ob){
		ResultSet result=query(Querys.matchFlightLine(from, to, maxTrans, tsLow, tsUp, limtTotalTime, totalTime, limtTotalPrice, totalPrice,selectDate,date,ob));
		ArrayList<FlightLine> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(new FlightLine(
						result.getString(5),
						result.getString(6),
						result.getString(7), 
						result.getString(8), 
						result.getInt(9), 
						result.getString(10), 
						result.getString(11), 
						result.getFloat(12),
						result.getString(13)));
			}
			result.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<FlightLine> searchFlights(
			String from,
			String to,
			int maxTrans,
			boolean limtTotalTime,
			String totalTime,
			boolean limtTotalPrice,
			float totalPrice,
			boolean selectDate,
			String date,
			String ob){
		ResultSet result=query(Querys.matchFlightLine(from, to, maxTrans, limtTotalTime, totalTime, limtTotalPrice, totalPrice,selectDate,date,ob));
		ArrayList<FlightLine> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(new FlightLine(
						result.getString(5),
						result.getString(6),
						result.getString(7), 
						result.getString(8), 
						result.getInt(9), 
						result.getString(10), 
						result.getString(11), 
						result.getFloat(12),
						result.getString(13)));
			}
			result.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Schedule getSchedule(String acode,int fn,String date){
		ResultSet result=query(Querys.findSchedule(acode, fn, date));
		try{
			while (result.next()){
				Schedule res = new Schedule(
						result.getString(1),
						result.getInt(2),
						result.getString(3),
						result.getString(4),
						result.getInt(5),
						result.getString(6),
						result.getString(7),
						result.getString(8), 
						result.getInt(9)-result.getInt(15),//-
						result.getInt(10)-result.getInt(14),//-
						result.getFloat(11),
						result.getFloat(12),
						result.getInt(13),
						result.getString(16));
				result.close();
				return res;
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public AirLine getAirline(String acode){
		ResultSet result=query(Querys.airLine(acode));
		try{
			while (result.next()){
				AirLine res = new AirLine(
						result.getString(1),
						result.getString(2),
						result.getString(3));
				result.close();
				return res;
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Airport getAirport(String iata){
		ResultSet result=query(Querys.airPort(iata));
		try{
			while (result.next()){
				Airport res = new Airport(
						result.getString(1),
						result.getString(2),
						result.getString(3),
						result.getString(4), 
						result.getString(5));
				result.close();
				return res;
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<String> iatas(){
		ResultSet result=query(Querys.AirPorts);
		ArrayList<String> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(result.getString(1));
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<String> countries(){
		ResultSet result=query(Querys.Countries);
		ArrayList<String> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(result.getString(1));
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<String> countryAirport(String country){
		ResultSet result=query(Querys.countryAirport(country));
		ArrayList<String> list = new ArrayList<>();
		try{
			while (result.next()){
				list.add(result.getString(1));
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		DataHelper helper = new DataHelper(); 
		ArrayList<Booking> bookings = helper.getUserBooking(1);
		System.out.println(bookings.size());
		helper.close();
	}
	
	

}
