drop schema if exists bricks99;





create schema bricks99;
use bricks99;
create table user_login(
user_id int primary key auto_increment,
user_name varchar(30) not null,
password varchar(100) not null,
email varchar(50) not null,
contact varchar(10) not null, 
user_role enum('ADMIN','BUYER','SELLER') not null

);



create table seller_address(
address_id int primary key auto_increment,
address_line_1 varchar(50),
address_line_2 varchar(50),
city varchar(25) not null,
state varchar(25) not null,
pincode varchar(6) not null
);
create table seller_registration(
registration_id int primary key auto_increment,
first_name varchar(30) not null,
last_name varchar(30) not null,
email_id varchar(30) unique not null,
contact varchar(10) unique not null,
address_id int   not null,
password varchar(10) not null,
status enum('APPROVED','PENDING','REJECTED'),
foreign key(address_id) references seller_address(address_id)
);

create table property_address(
 address_id int primary key auto_Increment,
 survey_no varchar(50) unique not null,
 locality varchar(50) not null,
 area_in_sqft decimal not null,
 pincode int not null,
 state varchar(50) not null
);

create table property_details (
 property_id int auto_increment primary key,
 property_name varchar(50) not null,
 property_type varchar(50) not null,
 property_price decimal not null,
 number_of_rooms varchar(50) not null,
 address_id int not null,
 foreign key(address_id) references property_address(address_id)
);
