-- CM System Database Tables Create and insert
-- Leslie Ducray (2013/2014). Final Year Project

-- Implemented in order of dependancy

drop table Cm_transactions;
drop table cm_accounts;
drop table CM_Client_Substance_Accum;
drop table CM_Client_Test_Results;
drop table cm_substances;
drop table CM_Client_Date_to_Clean;
drop table CM_Date_To_Clean;
drop table CM_Client_eligibilities;
drop table CM_Eligibility;
drop table CM_Client_Attnd;
drop table CM_Client_Notes;
drop table CM_Client_Stream;
drop table CM_Streams;
drop table CM_Clients;
Drop Table CM_User_Roles;
Drop Table CM_Roles;
Drop Table CM_Users;

Create table CM_Users (
    UserName VARCHAR(25) Not NULL unique,
    User_FName VARCHAR(20) NULL,
    User_SName VARCHAR(20) NULL,
    User_Email VARCHAR(50) NULL,
	User_Profession VARCHAR(50) Not NULL,
    User_Active Char check (user_active in ('Y' , 'y', 'N', 'n')),
    User_Password VARCHAR(50) NOT NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
	Updated_By VARCHAR(25) Not NULL,
    Updated_On DATE NOT NULL,
    CONSTRAINT CM_UsersPK PRIMARY KEY (UserName),
    CONSTRAINT user_Created_by FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
	CONSTRAINT user_Updated_by FOREIGN KEY (Updated_By)
        REFERENCES CM_System.Cm_USERS (UserName)
);

insert into cm_users values('lducray','Leslie','Ducray','c10327999@mydit.ie','Psychologist','Y','5f4dcc3b5aa765d61d8327deb882cf99','lducray',sysdate(),'lducray',sysdate());
insert into cm_users values('system','Rule','System','','System','Y','5f4dcc3b5aa765d61d8327deb882cf99','system',sysdate(),'system',sysdate());
insert into cm_users values('jsmith','John','Smith','c10327784@mydit.ie','Lab technician','Y','5f4dcc3b5aa765d61d8327deb882cf99','lducray',sysdate(),'lducray',sysdate());

Create table CM_Roles (
    Role_Name VARCHAR(20) NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CM_RolesPK PRIMARY KEY (Role_Name),
    CONSTRAINT role_Created_by FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName)
);

insert into CM_Roles values('admin','lducray',sysdate());

insert into CM_Roles values('accounting','lducray',sysdate());



Create table CM_User_Roles (
    Role_Name VARCHAR(20) Not NULL,
    UserName VARCHAR(25) Not NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CM_User_RolesPK PRIMARY KEY (Role_Name , UserName),
    CONSTRAINT User_role_user FOREIGN KEY (UserName)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT User_role_Created_by FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT User_role_role_name FOREIGN KEY (Role_Name)
        REFERENCES CM_System.Cm_Roles (Role_Name)
);

insert into cm_user_roles values ('admin','lducray','lducray',curdate());
insert into cm_user_roles values('admin','jsmith','lducray',sysdate());
insert into cm_user_roles values('accounting','lducray','lducray',curdate());



create table CM_Clients (
    client_id int NOT NULL AUTO_INCREMENT,
    primary key (client_id),
    Client_FName varchar(20) not null,
	Client_SName varchar(30) not null,
    Client_DOB varchar(10) not null,
    Client_Gender char null check (Client_Gender IN ('M' , 'm', 'F', 'f')),
    Client_Contact_No varchar(14) null,
	Client_PPSNo varchar(10),
    Client_Address varchar(100) null,
    Client_Nationality varchar(40) null,
    Client_Ethnicity varchar(40) null,
    Client_Occupation varchar(30) null,
    Client_Family_Info varchar(100) null,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    Updated_By VARCHAR(25) Not NULL,
    Updated_On DATE NOT NULL,
    CONSTRAINT Clients_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT Clients_Updated_By FOREIGN KEY (Updated_By)
        REFERENCES CM_System.Cm_USERS (UserName)
);
 


create table Cm_Streams (
    Stream_Id int Not NULL Primary key,
    Stream_Name varchar(20) not null,
    Support_Level int(1) not null,
    Stream_Active Char check (Stream_Active in ('Y' , 'y', 'N', 'n')),
    Progression_Time int ,
	Regression_Time int ,
    Weekly_Max_Points numeric(2) not null,
    Points_Conversion numeric(4 , 2 ) not null,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT Streams_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName)
);

insert into cm_streams values(1,'Preliminary',1,'Y',5,null,16.50,0.40,'lducray',curdate());
insert into cm_streams values(2,'Advanced',2,'Y',null,3,22,0.60,'lducray',curdate());

create table CM_Client_Stream (
    Client_Id int Not NULL,
    Stream_Id int Not NULL,
	Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
	Updated_By VARCHAR(25) Not NULL,
    Updated_On DATE NOT NULL,
    CONSTRAINT CS_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
 CONSTRAINT CS_Updated_By FOREIGN KEY (updated_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT CS_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
    CONSTRAINT CS_Stream_Id FOREIGN KEY (Stream_Id)
        REFERENCES CM_System.Cm_Streams (Stream_Id),
    CONSTRAINT CS_PK PRIMARY KEY (Client_Id , Stream_Id)
);


create table CM_Client_Notes (
    Id int not null auto_increment primary key,
    Client_Id int Not NULL,
    UserName VARCHAR(25) Not NULL,
    Note TEXT,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    Updated_On DATE NOT NULL,
    CONSTRAINT Notes_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT Notes_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
    CONSTRAINT Notes_username FOREIGN KEY (username)
        REFERENCES CM_System.Cm_USERS (UserName)
);



create table CM_Client_Attnd (
    Id int not null auto_increment primary key,
    Client_Id int Not NULL,
    UserName VARCHAR(25) Not NULL,
    staff_profession VARCHAR(50) Not NULL,
    Time_date varchar(20),
    Attended Char check (Attended in ('Y' , 'y', 'N', 'n')),
	participated Char check (Attended in ('Y' , 'y', 'N', 'n')),
    Attnd_Failed_Reason varchar(50),
	Valid_Reason Char check (Attended in ('Y' , 'y', 'N', 'n')),
    Treatment_review_meeting Char check (user_active in ('Y' , 'y', 'N', 'n')),
	CONSTRAINT attend_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
	CONSTRAINT attend_user FOREIGN KEY (UserName)
        REFERENCES CM_System.Cm_USERS (UserName)
);

CREATE TABLE CM_Eligibility (
    Eligibility_Id int Not NULL,
    Eligibility_name varchar(25) not null,
    Active char not null check (active in ('Y' , 'y', 'N', 'n')),
	min_value int,
	max_value int,
    Stream_Id int  null,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    PRIMARY KEY (Eligibility_Id),
    CONSTRAINT Elig_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT Elig_Stream FOREIGN KEY (Stream_Id)
        REFERENCES CM_System.Cm_Streams (Stream_Id)
);

insert into cm_eligibility values(1,'Withdraw Credits', 'Y',  20,null,null,'lducray',curdate());
insert into cm_eligibility values(2,'Group Recreational Outings', 'Y',  null,null,2,'lducray',curdate());
insert into cm_eligibility values(3,'Weekday Take Away', 'Y',  null,null,null,'lducray',curdate());
insert into cm_eligibility values(4,'Weekend Take Away', 'Y',  null,null,null,'lducray',curdate());

CREATE TABLE CM_Client_eligibilities (
    Eligibility_Id int,
    Client_Id int,
	 Active char not null check (active in ('Y' , 'y', 'N', 'n')),
	Amount numeric null,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CE_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT CE_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
    CONSTRAINT CE_Elig_Id FOREIGN KEY (Eligibility_Id)
        REFERENCES CM_System.CM_Eligibility (Eligibility_Id)
);

CREATE TABLE CM_Date_To_Clean (
    Id int not null auto_increment primary key,
    Card varchar(10) unique,
    No_Days numeric not null,
    Active char not null check (active in ('Y' , 'y', 'N', 'n')),
    Order_of_Progress numeric not null,
    Stream_Id int not null,
    Days_Extension numeric,
    CONSTRAINT DTC_Stream FOREIGN KEY (Stream_Id)
        REFERENCES CM_System.Cm_Streams (Stream_Id)
);

insert into cm_date_to_clean values(1,'none',7,'Y',0,1,3);
insert into cm_date_to_clean values(2,'YELLOW',7,'Y',1,1,3);
insert into cm_date_to_clean values(3,'ORANGE',7,'Y',2,1,3);
insert into cm_date_to_clean values(4,'RED',7,'Y',3,1,3);


CREATE TABLE CM_Client_Date_to_Clean (
    Id int not null auto_increment primary key,
    Client_Id int,
    Card int,
    Extension_applied char check (Extension_applied in ('Y' , 'y', 'N', 'n')),
    Date_to_Clean date not null,
    Set_By VARCHAR(25) Not NULL,
    Set_On date not null,
    CONSTRAINT CDTC_SetBy FOREIGN KEY (Set_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT CDTC_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
    CONSTRAINT CDTC_Card FOREIGN KEY (Card)
        REFERENCES CM_System.CM_Date_To_Clean (Id)
);

CREATE TABLE CM_Substances (
    Substance varchar(30) primary key,
    Reset_value int not null,
    Max_value int not null,
	Stream_regression_substance char not null check (Stream_regression_substance in ('Y' , 'y', 'N', 'n')),
    Active char not null check (Active in ('Y' , 'y', 'N', 'n')),
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    Updated_By VARCHAR(25) Not NULL,
    Updated_On DATE NOT NULL,
    CONSTRAINT Substances_Created_by FOREIGN KEY (Created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
	CONSTRAINT Substances_Updated_by FOREIGN KEY (Updated_by)
        REFERENCES CM_System.Cm_USERS (UserName)
);

insert into cm_substances values ('Opiate', 0, 4,'Y', 'Y', 'lducray', curdate(),'lducray', curdate());
insert into cm_substances values ('Stimulant', 0, 4, 'Y','Y', 'lducray', curdate(),'lducray', curdate());
insert into cm_substances values ('Benzodiazepine', 0, 4, 'Y','Y', 'lducray', curdate(),'lducray', curdate());
insert into cm_substances values ('THC', 0, 4, 'N','Y', 'lducray', curdate(),'lducray', curdate());
insert into cm_substances values ('Alcohol', 0, 1, 'N','Y', 'lducray', curdate(),'lducray', curdate());

CREATE TABLE CM_Client_Test_Results (
    Id int not null auto_increment primary key,
    Client_Id int,
    Substance varchar(30),
    Result char check (Result in ('P' , 'p', 'F', 'f')),
    Administered_By VARCHAR(25) Not NULL,
    Administered_On DATE NOT NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CTR_Created_by FOREIGN KEY (Created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT CTR_Admin_by FOREIGN KEY (Administered_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT CTR_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
    CONSTRAINT CTR_Substance FOREIGN KEY (Substance)
        REFERENCES CM_System.CM_Substances (Substance)
);

CREATE TABLE CM_Client_Substance_Accum (
    Client_Id int,
    Substance varchar(30),
    Accum int(1),
    Updated_By VARCHAR(25) Not NULL,
    Updated_On DATE NOT NULL,
    CONSTRAINT CSA_Updated_By FOREIGN KEY (Updated_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT CSA_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
    CONSTRAINT CSA_Substance FOREIGN KEY (Substance)
        REFERENCES CM_System.CM_Substances (Substance),
	CONSTRAINT CSA_PK PRIMARY KEY (Client_Id , Substance)
);

CREATE TABLE CM_Accounts (
    Account_Id int not null auto_increment primary key,
    Account_Balance numeric(5 , 2 ),
    Active char not null check (Active in ('Y' , 'y', 'N', 'n')),
    Updated_By VARCHAR(25) Not NULL,
    Updated_On DATE NOT NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT Acc_Created_by FOREIGN KEY (Created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT Acc_Updated_By FOREIGN KEY (Updated_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT Acc_Client_Id FOREIGN KEY (Account_Id)
        REFERENCES CM_System.CM_Clients (client_id)
);


CREATE TABLE CM_Transactions (
    Id int not null auto_increment primary key,
    Account_Id int,
    Amount_Withdrawn numeric(5 , 2 ),
    Amount_Credited numeric(5 , 2 ),
    Approved_By VARCHAR(25) Not NULL,
    Date_of_Transaction date not null,
    CONSTRAINT Trans_Approved_by FOREIGN KEY (Approved_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT Trans_Account_Id FOREIGN KEY (Account_Id)
        REFERENCES CM_System.CM_Accounts (Account_id)
);



commit;


