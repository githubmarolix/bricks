drop schema if exists bricks99_new;





create schema bricks99_new;
use bricks99_new;
    create table property_address (
       address_id integer not null auto_increment,
        address_line varchar(255),
        city varchar(255),
        pincode varchar(255) not null,
        state varchar(255) not null,
        survey_no varchar(255) not null,
        primary key (address_id)
    );
    
    
     create table property_details (
       property_id integer not null auto_increment,
        area_in_sqft double precision not null,
        bath_rooms integer not null,
        bed_rooms integer not null,
        building_age integer not null,
        category varchar(255) not null,
        description varchar(255) not null,
        no_of_photos integer not null,
        number_of_rooms varchar(255),
        property_name varchar(255),
        property_price double precision,
        property_type varchar(255),
        address_id integer,
        registration_id integer,
        primary key (property_id)
    );
       create table seller (
       seller_id integer not null auto_increment,
        contact varchar(255),
        email_id varchar(255) not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        status varchar(255),
        address_id integer,
        user_id integer,
        primary key (seller_id)
    );
    
    
       create table seller_address (
       address_id integer not null auto_increment,
        state varchar(255),
        address_line_1 varchar(255),
        address_line_2 varchar(255),
        city varchar(255),
        pincode varchar(255),
        primary key (address_id)
    );
    
CREATE TABLE user (
    user_id integer not null auto_increment,
    contact varchar(255),
    contact_verified TINYINT(1), 
    email varchar(255),
    email_verified TINYINT(1),  
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    registered_at datetime(6),
    user_type_id integer,
    primary key (user_id)
);
    
       create table user_backup_data (
       user_id integer not null,
        email varchar(255),
        password varchar(255),
        phone_number bigint not null,
        user_name varchar(255),
        user_role varchar(255),
        primary key (user_id)
    );
        create table user_login (
       user_id integer not null auto_increment,
        contact varchar(255),
        email varchar(255),
        password varchar(255),
        user_name varchar(255),
        user_role varchar(255),
        primary key (user_id)
    );
     create table user_type (
       user_type_id integer not null auto_increment,
        user_type_name varchar(255),
        primary key (user_type_id)
    );
    alter table property_address 
       add constraint UK_sv0ik8l234l085p2peuvfw52h unique (survey_no);
       alter table seller 
       add constraint UK_3q7dt5p3fxuas155qtigpcyga unique (contact);
       
        alter table seller 
       add constraint UK_oo27njgogd5779fn4pxo9sv7u unique (email_id);
       
    
       
         alter table seller 
       add constraint UK_etfpl3vymasxfqc4ri4r3euf6 unique (user_id);
       
        alter table property_details 
       add constraint FKgv3utjelfp5guicq3leo19r5f 
       foreign key (address_id) 
       references property_address (address_id);
       
       
       alter table seller 
       add constraint FKgnr6au2hrwjbgr9891hd3brus 
       foreign key (address_id) 
       references seller_address (address_id);
       
       alter table seller 
       add constraint FK6rgw0e6tb24n93c27njlv0wcl 
       foreign key (user_id) 
       references user (user_id);
       
          alter table user 
       add constraint FKlrk9xrdps0emd6d5rx5x3ib6h 
       foreign key (user_type_id) 
       references user_type (user_type_id); 
           CREATE TABLE States (
    state_id INT AUTO_INCREMENT PRIMARY KEY,
    state_name VARCHAR(255)
   
);   
       
       CREATE TABLE Districts (
    district_id INT AUTO_INCREMENT PRIMARY KEY,
    district_name VARCHAR(255),
    state_id INT,
    FOREIGN KEY (state_id) REFERENCES States(state_id)
);

       CREATE TABLE Pincodes (
    pincode_id INT AUTO_INCREMENT PRIMARY KEY,
    pincode VARCHAR(255),
    district_id INT,
    FOREIGN KEY (district_id) REFERENCES Districts(district_id)
);