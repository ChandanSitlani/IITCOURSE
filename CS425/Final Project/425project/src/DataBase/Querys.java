package DataBase;


import models.*;

public class Querys {
	protected final static String CheckUser=
			"SELECT count(*) FROM login_info WHERE (email_address='%s');" ;
	protected static String checkUser(String email){
		return String.format(CheckUser, email);
	}
	
	protected final static String Register = 
			"INSERT INTO customer VALUES(DEFAULT,'%S',%S,'%S',DEFAULT);\n"+
			"INSERT INTO login_info VALUES('%s','%s',(SELECT currval(pg_get_serial_sequence('customer', 'user_id'))))";
	protected static String register(UserInfo user, LoginInfo login){
		return String.format(Register, user.getFirstName(),user.getMiddleName()==null?"NULL":String.format("'%s'", user.getMiddleName()),user.getLastName(),login.getUserName(),login.getPassWord());
	}
	
	protected final static String UpdateUinfo=
			"UPDATE customer SET first_name = '%s',middle_name= %s ,last_name='%s',home_airport='%s' WHERE user_id=%d";
	protected static String updateUinfo(UserInfo user){
		return String.format(UpdateUinfo, user.getFirstName(),user.getMiddleName()==null?"NULL":String.format("'%s'", user.getMiddleName()),user.getLastName(),user.getHomeIATA(),user.getUid());
	}
	
	protected final static String UpdateLinfo=
			"UPDATE login_info SET password='%s' WHERE user_id = %d AND password='%s'";
	protected static String updateLinfo(String password,String oldpass, int uid){
		return String.format(UpdateLinfo, password,uid,oldpass);
	}
	
	protected final static String UserInfo=
			"SELECT * FROM customer \n"+
			"WHERE user_id = (SELECT user_id FROM login_info WHERE email_address = '%s' AND password='%s')";
	protected static String userInfo(LoginInfo info){
		return String.format(UserInfo, info.getUserName(),info.getPassWord());
	}
	
	protected final static String UserAddress=
			"SELECT * FROM address WHERE user_id = %d ORDER BY address_id";
	protected static String userAddress(int uid){
		return String.format(UserAddress, uid);
	}
	
	protected final static String InsertAddress = 
			"INSERT INTO address VALUES(DEFAULT,'%s','%s','%s',%s,'%s','%s','%s',%d);";
	protected static String insertAddress(Address addr,int uid){
		return String.format(InsertAddress,
				addr.getStreet(),
				addr.getDeptNum(),
				addr.getCity(),
				addr.getState()==null?"NULL":String.format("'%s'", addr.getState()),
				addr.getZipCode(),
				addr.getCountry(),
				addr.getPhoneNumber(),
				uid);
	}
	protected final static String UpdateAddress = 
			"UPDATE address SET phone_num='%s',zipcode='%s',street='%s', dept_num = '%s',City = '%s', state = %s, country= '%s' WHERE address_id=%d AND user_id =%d";
	protected static String updateAddress(Address addr, int uid){
		return String.format(UpdateAddress, 
				addr.getPhoneNumber(),
				addr.getZipCode(),
				addr.getStreet(),
				addr.getDeptNum(),
				addr.getCity(),
				addr.getState()==null?"NULL":String.format("'%s'", addr.getState()),
				addr.getCountry(),
				addr.getAddrID(),
				uid);
	}
	
	protected final static String DeleteAddress =
			"DELETE FROM address WHERE user_id=%d AND address_id=%d ";
	protected static String deleteAddress(int uid, int aid){
		return String.format(DeleteAddress, uid,aid);
	}
	
	
	protected final static String UserPayMethod=
			"SELECT * FROM credit_card WHERE user_id=%d";
	protected static String userPayMethod(int uid){
		return String.format(UserPayMethod, uid);
	}
	protected final static String InsertPayMethod= 
			"INSERT INTO credit_card VALUES(%d,'%s',%d,'%s',%d,%d)";
	protected static String insertPayMethod(CreditCard card){
		return String.format(InsertPayMethod, card.getUid(),card.getCardNumber(),card.getExpirationDate(),card.getCardHolder(),card.getCvv(),card.getBilliing_address());
	}
	protected final static String UpdateCard = 
			"UPDATE credit_card SET expiration_date = '%d', card_holder = '%s', cvv= %d, billing_address=%d, card_number='%s' WHERE user_id =%d AND card_number='%s'";
	protected static String updateCard (CreditCard old, CreditCard card){
		return String.format(UpdateCard, card.getExpirationDate(),card.getCardHolder(),card.getCvv(),card.getBilliing_address(),card.getCardNumber(),card.getUid(),old.getCardNumber());
	}
	protected final static String DeleteCard = 
			"DELETE FROM credit_card CASECADE WHERE user_id=%d AND card_number='%s'";
	protected static String deleteCard(CreditCard card){
		return String.format(DeleteCard, card.getUid(),card.getCardNumber());
	}
	
	protected final static String UserBooking = 
			"SELECT * FROM booking WHERE user_id = %d";
	protected static String userBooking(int uid){
		return String.format(UserBooking, uid);
	}
	protected final static String DeleteBooking = 
			"DELETE FROM booking where user_id = %d AND order_num = %d";
	protected static String deleteBooking (int uid,int onum){
		return String.format(DeleteBooking, uid,onum);
	}
	protected final static String InsertBooking = 
			"INSERT INTO booking VALUES(DEFAULT, %f,%d,'%s','%s','%s',%d,'%d','%s');";
	protected static String insertbooking (float price,int uid,String cardNumber,String fromAP,String toAp,int fnum,boolean ret,String date){
		return String.format(InsertBooking, price,uid,cardNumber,fromAP,toAp,fnum,ret?1:0,date);
	}
	
	protected final static String BookingSchedule = 
			"SELECT * FROM booking_schedule WHERE order_number = %d";
	protected static String bookingSchedule(int oid){
		return String.format(BookingSchedule, oid);
	}
	protected final static String DeleteBookingSchedule = 
			"DELETE FROM booking_schedule WHERE order_number = %d";
	protected static String deleteBookingSchedule(int oid){
		return String.format(DeleteBookingSchedule, oid);
	}
	
	protected final static String InsertBookingSchedule=
			"INSERT INTO booking_schedule VALUES(currval(pg_get_serial_sequence('booking', 'order_num')),'%s',%d,'%s','%d','%d')";
	protected final static String insertBookingSchedule(Schedule schedule,boolean isret){
		return String.format(InsertBookingSchedule, schedule.getAcode(),schedule.getFlightNumber(),schedule.getSchedule_date(),isret?1:0,schedule.isBookfirstclass()?1:0);
	}
	
	
	
	
	protected final static String MatchFlightLine=
			"WITH RECURSIVE flight_line AS(\n"+
					"WITH fs AS(\n"+
							"WITH sd AS(\n"+
				            "SELECT acode, flight_number, schedule_date,count(*) AS sold\n"+
				            "FROM booking_schedule\n"+
				            "GROUP BY(acode, flight_number, schedule_date)\n"+
				        ")\n"+
				        "SELECT flight.acode,flight.flight_number,from_airport,to_airport,mileage,schedule.schedule_date,from_time,to_time,firstclasscapacity,econclasscapacity,\n"+
				        "firstclassprice,econclassprice,passdate, sold\n"+
				        "FROM (flight NATURAL JOIN schedule) LEFT OUTER JOIN sd ON flight.acode=sd.acode AND flight.flight_number=sd.flight_number AND schedule.schedule_date=sd.schedule_date\n"+
				        "        WHERE (sold is NULL or sold< firstclasscapacity+econclasscapacity)"+
					")\n"+
					"SELECT \n"+ 
						"from_airport,\n"+
						"to_airport,\n"+
						"schedule_date+from_time AS from_date,\n"+
						"schedule_date+passdate+to_time AS to_date,\n"+
						"schedule_date::text AS fly_date,\n"+
				        "from_time::TEXT fly_time,\n"+
				        "(schedule_date+passdate)::TEXT AS arrive_date,\n"+
				        "to_time::TEXT arrive_time,\n"+
						"1 flight_count,\n"+
						"from_airport||'->'||to_airport AS line,\n"+
						"acode||' '||flight_number AS flights,\n"+
						"econclassprice AS price \n"+
					"FROM fs \n"+
					"WHERE \n"+
						"from_airport = '%s'\n"+											//from
						"AND (schedule_date = '%s' OR NOT %b)\n"+
					"UNION\n"+ 
					"SELECT\n"+ 
						"flight_line.from_airport,\n"+
						"ext.to_airport,\n"+
						"from_date,\n"+
						"ext.schedule_date+ext.passdate+ext.to_time AS to_date,\n"+
						"fly_date||'->'||schedule_date AS fly_date,\n"+
						"fly_time,\n"+
				        "(ext.schedule_date+ext.passdate)::TEXT AS arrive_date,\n"+
				        "ext.to_time::TEXT arrive_time,\n"+
						"flight_count+1 AS flight_count,\n"+
						"line||'->'||ext.to_airport AS line,\n"+
						"flights||'->'||ext.acode||' '||flight_number,\n"+
						"price + econclassprice AS price\n"+
					"FROM flight_line, fs ext\n"+
					"WHERE\n"+
						"flight_line.to_airport = ext.from_airport\n"+
						"AND flight_line.flight_count<%d\n"+									//max trans
						"AND flight_line.to_airport<>'%s'\n"+								//to
						"AND to_date < (ext.schedule_date+ext.from_time)-INTERVAL '%s'\n"+ 	//timeslice low
						"AND to_date > (ext.schedule_date+ext.from_time)-INTERVAL '%s'\n"+ 	//timeslice up
			")\n"+
			"SELECT *,(to_date-from_date)::TEXT AS length  FROM  flight_line\n"+
			"WHERE\n"+ 
				"flight_line.to_airport='%s'\n"+												//to
				"AND ((to_date-from_date) < INTERVAL '%s' OR NOT %b)\n"+							//total time ; use total time
				"AND (price<=%f OR NOT %b)\n%s";													//total price ; use total price
	
	protected static String matchFlightLine(String from,String to,int maxTrans,String tsLow,String tsUp,boolean limtTotalTime,String totalTime,boolean limtTotalPrice,float totalPrice,boolean selectDate, String date,String orderBy){
		return String.format(MatchFlightLine, from,date,selectDate,maxTrans,to,tsLow,tsUp,to,totalTime,limtTotalTime,totalPrice,limtTotalPrice,orderBy(orderBy));
	}
	protected static String matchFlightLine(String from,String to,int maxTrans,boolean limtTotalTime,String totalTime,boolean limtTotalPrice,float totalPrice,boolean selectDate, String date,String ob){
		return matchFlightLine(from, to, maxTrans, "3 hours", "1 day", limtTotalTime, totalTime, limtTotalPrice, totalPrice,selectDate,date,ob);
	}
	protected static String orderBy(String tag){
		if(tag==null)
			return ";";
		switch (tag) {
		case "connect":return "ORDER BY flight_count;";
		case "length" :return "ORDER BY length;";
		case "price"  :return "ORDER BY price;";
		default:
			return ";";
		}
	}
	
	protected final static String FindSchedule=
			"WITH fs AS(\n"+
				"WITH sd1 AS(\n"+
					"SELECT acode, flight_number, schedule_date,count(*) AS soldE\n"+
					"FROM booking_schedule\n"+
					"WHERE isfirstclass='0'\n"+
					"GROUP BY(acode, flight_number, schedule_date)\n"+
				"),sd2 AS(\n"+
					"SELECT acode, flight_number, schedule_date,count(*) AS soldF\n"+
					"FROM booking_schedule\n"+
					"WHERE isfirstclass='1'\n"+
					"GROUP BY(acode, flight_number, schedule_date)\n"+
				")\n"+
			"SELECT flight.acode,flight.flight_number,from_airport,to_airport,mileage,schedule.schedule_date,from_time,to_time,firstclasscapacity,econclasscapacity,firstclassprice,econclassprice,passdate, soldE,soldF,airline_name\n"+
			"FROM\n"+ 
				"(flight Natural JOIN schedule NATURAL JOIN air_line)" +
		        "LEFT OUTER JOIN sd1\n"+
		        "ON\n"+ 
		        	"flight.acode=sd1.acode AND\n"+
		        	"flight.flight_number=sd1.flight_number AND\n"+
		        	"schedule.schedule_date=sd1.schedule_date\n"+
		        "LEFT OUTER JOIN sd2\n"+
		        "ON\n"+ 
		        	"flight.acode=sd2.acode AND\n"+
		        	"flight.flight_number=sd2.flight_number AND\n"+
		        	"schedule.schedule_date=sd2.schedule_date\n"+
		   ")\n"+
		   "SELECT * FROM fs\n"+
		   "WHERE\n"+ 
		   "acode='%s' AND flight_number=%d AND schedule_date = '%s'";
	protected static String findSchedule(String acode,int fn,String date){
		return String.format(FindSchedule,acode,fn,date);
	}
	
	protected final static String AirLine = "SELECT * FROM air_line WHERE acode = '%s'";
	protected static String airLine(String acode) {
		return String.format(AirLine, acode);
	}
	
	protected final static String AirPort = "SELECT * FROM airport WHERE iata  = '%s'";
	protected static String airPort(String iata) {
		return String.format(AirPort, iata);
	}
	protected final static String AirPortName = "SELECT name FROM airport WHERE iata  = '%s'";
	protected static String airPortName(String iata) {
		return String.format(AirPortName, iata);
	}
	
	protected final static String AirPorts = "SELECT iata FROM airport WHERE iata  <> 'UDF'";
	
	protected final static String Countries = 
			"SELECT DISTINCT country FROM airport WHERE iata <>'UDF'";
	protected final static String CountryAirport = 
			"SELECT DISTINCT iata FROM airport WHERE country = '%s'";
	protected static String countryAirport(String country) {
		return String.format(CountryAirport, country);
		
	}
	
	protected final static String Mileage = 
			"SELECT airline_name, sum(mileage) AS mileage\n"+
			"FROM\n"+ 
			"booking_schedule NATURAL JOIN \n"+
			"flight NATURAL JOIN \n"+
			"air_line NATURAL JOIN\n"+
			"(SELECT order_num AS order_number, user_id FROM Booking)AS oid\n"+
			"WHERE user_id = %d\n"+
			"GROUP BY airline_name";
	protected static String mileage(int uid){
		return String.format(Mileage, uid);
	}
	
	
}
