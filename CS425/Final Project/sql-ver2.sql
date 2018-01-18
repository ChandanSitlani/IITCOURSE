DROP TABLE IF EXISTS airport CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS login_info CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS credit_card CASCADE;
DROP TABLE IF EXISTS flight CASCADE;
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS booking CASCADE;
DROP TABLE IF EXISTS air_line CASCADE;


CREATE TABLE airport(
    IATA VARCHAR(3) PRIMARY KEY NOT NULL,               --char(3)
    country VARCHAR (20) NOT NULL,
    city VARCHAR(25) NOT NULL,
    state CHAR(2),
    name TEXT NOT NULL
);

CREATE TABLE customer(
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(15) NOT NULL,
    middle_name VARCHAR(15),
    last_name VARCHAR(15) NOT NULL,
    home_airport VARCHAR(3) NOT NULL,
    FOREIGN KEY (home_airport) REFERENCES airport(IATA)
);

CREATE TABLE login_info(
    email_address VARCHAR(50) PRIMARY KEY NOT NULL,
    password VARCHAR(25) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES customer
);



CREATE TABLE address(
    address_id SERIAL PRIMARY KEY NOT NULL,
    street TEXT NOT NULL,
    dept_num NUMERIC(5),
    city VARCHAR(25) NOT NULL,
    state VARCHAR(25),
    zipcode VARCHAR(25) NOT NULL,
    country VARCHAR(25) NOT NULL,
    phone_num VARCHAR (15),
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES customer
);

CREATE TABLE credit_card(
    user_id INT NOT NULL,
    card_number NUMERIC(25) NOT NULL,
    expiration_date NUMERIC(4) NOT NULL,
    card_holder TEXT NOT NULL,
    cvv NUMERIC(3) NOT NULL,
    billing_adress INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES customer,
    FOREIGN KEY (billing_adress) REFERENCES address(address_id),
    PRIMARY KEY (user_id,card_number)
);

CREATE TABLE air_line(
    acode VARCHAR(5) PRIMARY KEY NOT NULL,
    airline_name VARCHAR(50) NOT NULL,
    country VARCHAR(20) NOT NULL
);

CREATE TABLE flight(
    acode VARCHAR(5) NOT NULL,
    flight_number NUMERIC(5) NOT NULL,
    from_airport VARCHAR(3) NOT NULL,
    to_airport VARCHAR(3) NOT NULL,
    mileage INT,
    FOREIGN KEY (acode) REFERENCES air_line,
    FOREIGN KEY (from_airport) REFERENCES airport(IATA),
    FOREIGN KEY (to_airport) REFERENCES airport(IATA),
    PRIMARY KEY (acode,flight_number)
);



CREATE TABLE schedule(
    acode VARCHAR(5) NOT NULL,
    flight_number NUMERIC(5) NOT NULL,
    schedule_date DATE NOT NULL,
    from_time TIME WITH TIME ZONE NOT NULL,
    to_time TIME WITH TIME ZONE NOT NULL,
    firstClassCapacity NUMERIC(4),
    econClassCapacity NUMERIC(4),
    firstClassPrice MONEY NOT NULL,
    econClassPrice MONEY NOT NULL, 
    passdate INT DEFAULT 0,
    FOREIGN KEY (acode, flight_number) REFERENCES flight,
    PRIMARY KEY (acode,flight_number,schedule_date)
);

CREATE TABLE booking(
    order_num INT PRIMARY KEY NOT NULL,
    price MONEY NOT NULL,
    class BIT(1) NOT NULL DEFAULT '0',
    return_order_num INT,
    user_id INT NOT NULL NOT NULL,
    card_number NUMERIC(25) NOT NULL,
    acode VARCHAR(5) NOT NULL,
    flight_number NUMERIC(5) NOT NULL,
    schedule_date DATE NOT NULL,
    FOREIGN KEY (acode,flight_number,schedule_date) REFERENCES schedule,
    FOREIGN KEY (user_id) REFERENCES customer,
    FOREIGN KEY (user_id,card_number) REFERENCES credit_card,
    FOREIGN KEY (return_order_num) REFERENCES booking(order_num)
);

INSERT INTO airport VALUES ('UDF','UnKnow','UnKnow',NULL,'UNdefine');

INSERT INTO customer(first_name,middle_name,last_name,home_airport) VALUES('tester',NULL,'tester','UDF');

INSERT INTO customer VALUES(DEFAULT,'tester2',NULL,'tester2','UDF');

SELECT currval(pg_get_serial_sequence('customer', 'user_id'));

SELECT count(*) 
FROM login_info
WHERE (email_address='t@t.com');

INSERT INTO customer VALUES(DEFAULT,'testeru',NULL,'log','UDF');
INSERT INTO login_info
VALUES('t@t.com','testlogin',(SELECT currval(pg_get_serial_sequence('customer', 'user_id'))));

SELECT * FROM customer;

SELECT * FROM login_info;

INSERT INTO address VALUES(DEFAULT,'streetName',123,'Chicago','Illinios','60616','USA','1145147039',1);

INSERT INTO credit_card VALUES(1,'1234223432344234',1222,'Si W',111,1);

INSERT INTO airport VALUES('SEA', 'United States', 'Seattle', 'WA', 'Seattle–Tacoma International Airport');
INSERT INTO airport VALUES('SFO', 'United States', 'San Francisco', 'CA', 'San Francisco International Airport');
INSERT INTO airport VALUES('SJC', 'United States', 'San Jose', 'CA', 'San Jose International Airport');
INSERT INTO airport VALUES('LAX', 'United States', 'Los Angeles', 'CA', 'Los Angeles International Airport');
INSERT INTO airport VALUES('ORD', 'United States', 'Chicago', 'IL', 'O Hare International Airport');
INSERT INTO airport VALUES('DFW', 'United States', 'Dallas', 'TX', 'Dallas/Ft. Worth International Airport');
INSERT INTO airport VALUES('IAH', 'United States', 'Houston', 'TX', 'George Bush Intercontinental Airport');
INSERT INTO airport VALUES('AUS', 'United States', 'Austin', 'TX', 'Austin Bergstrom International Airport');
INSERT INTO airport VALUES('JFK', 'United States', 'New York City', 'NY', 'John F. Kennedy International Airport');
INSERT INTO airport VALUES('BOS', 'United States', 'Boston', 'MA', 'Boston Logan International Airport');
INSERT INTO airport VALUES('BWI', 'United States', 'Linthicum', 'MD', 'Baltimore International Airport');
INSERT INTO airport VALUES('MCO', 'United States', 'Orlando', 'FL', 'Orlando International Airport');

INSERT INTO airport VALUES('PEK', 'China', 'Beijing', NULL, 'Beijing Capital International Airport');
INSERT INTO airport VALUES('HKG', 'China', 'Hong Kong', NULL, 'Hong Kong International Airport');
INSERT INTO airport VALUES('NRT', 'Japan', 'Tokyo', NULL, 'Narita International Airport');
INSERT INTO airport VALUES('LHR', 'United Kindom', 'London', NULL, 'London Heathrow Airport');
INSERT INTO airport VALUES('CDG', 'French', 'Paris', NULL, 'Paris Charles De Gaulle Airport');
INSERT INTO airport VALUES('IST', 'Turkey', 'Istanbul', NULL, 'Istanbul Ataturk Airport');


INSERT INTO air_line VALUES('AA', 'American Airlines', 'United States');
INSERT INTO air_line VALUES('DL', 'Delta Air Lines', 'United States');
INSERT INTO air_line VALUES('VX', 'Virgin America', 'United States');
INSERT INTO air_line VALUES('SW', 'Southwest Airlines', 'United States');
INSERT INTO air_line VALUES('F9', 'Frontier Airlines', 'United States');
INSERT INTO air_line VALUES('AS', 'Alaska Airlines', 'United States');

INSERT INTO air_line VALUES('BA', 'British Airways', 'United Kindom');
INSERT INTO air_line VALUES('JL', 'Japan Airlines', 'Japan');
INSERT INTO air_line VALUES('HU', 'Hainan Airlines', 'China');
INSERT INTO air_line VALUES('MU', 'China Eastern Airlines', 'China');
INSERT INTO air_line VALUES('CX', 'Cathay Pacific Airways', 'Hong Kong, China');
INSERT INTO air_line VALUES('TK', 'Turkish Airlines', 'Turkey');
INSERT INTO air_line VALUES('AF', 'Air France', 'Frence');
INSERT INTO air_line VALUES('NH', 'All Nippon Airways', 'JAPAN');
INSERT INTO air_line VALUES('AC', 'Air Canada', 'Canada');

 
SELECT ((schedule_date+passdate+to_time)-(schedule_date+from_time)) FROM schedule;