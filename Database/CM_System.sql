-- CM System Database Tables Create and insert
-- Leslie Ducray (2013/2014). Final Year Project

-- Implemented in order of dependancy

Drop Table CM_Users;

Create table CM_Users (
    UserName VARCHAR(25) Not NULL unique,
    User_FName VARCHAR(20) NULL,
    User_SName VARCHAR(20) NULL,
    User_Email VARCHAR(50) NULL,
    User_Active Char check (user_active in ('Y' , 'y', 'N', 'n')),
    User_Password VARCHAR(50) NOT NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CM_UsersPK PRIMARY KEY (UserName),
    CONSTRAINT user_Created_by FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName)
);

Drop Table CM_Roles;

Create table CM_Roles (
    Role_Id numeric Not NULL,
    Role_Name VARCHAR(20) NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CM_RolesPK PRIMARY KEY (Role_Id),
    CONSTRAINT role_Created_by FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName)
);

insert into cm_roles values(1,'Admin','lducray',sysdate());
select 
    *
from
    cm_roles;

Drop Table CM_User_Roles;

Create table CM_User_Roles (
    Role_Id numeric Not NULL,
    UserName VARCHAR(25) Not NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CM_User_RolesPK PRIMARY KEY (Role_Id , UserName),
    CONSTRAINT User_role_user FOREIGN KEY (UserName)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT User_role_Created_by FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT User_role_role_Id FOREIGN KEY (Role_Id)
        REFERENCES CM_System.Cm_Roles (Role_Id)
);

insert into CM_User_Roles values(1,'lducray','lducray',sysdate());
select 
    *
from
    CM_User_Roles;


drop table CM_Clients;

create table CM_Clients (
    client_id int NOT NULL AUTO_INCREMENT,
    primary key (client_id),
    Client_Name varchar(50) not null,
    Client_DOB date not null,
    Client_Gender char null check (Gender IN ('M' , 'm', 'F', 'f')),
    Client_Contact_No varchar(14) null,
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
 
drop table CM_Streams;

create table Cm_Streams (
    Stream_Id int Not NULL Primary key,
    Stream_Name varchar(20) not null,
    Support_Level int not null,
    Stream_Active Char check (user_active in ('Y' , 'y', 'N', 'n')),
    Progression_Wait_Time int not null,
    Attendance_Requirement varchar(30),
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT Streams_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName)
);

drop table CM_Client_Stream;

create table CM_Client_Stream (
    Client_Id int Not NULL,
    Stream_Id int Not NULL,
    Created_By VARCHAR(25) Not NULL,
    Created_On DATE NOT NULL,
    CONSTRAINT CS_Created_By FOREIGN KEY (created_By)
        REFERENCES CM_System.Cm_USERS (UserName),
    CONSTRAINT CS_Client_Id FOREIGN KEY (Client_id)
        REFERENCES CM_System.CM_Clients (client_id),
    CONSTRAINT CS_Stream_Id FOREIGN KEY (Stream_Id)
        REFERENCES CM_System.Cm_Streams (Stream_Id),
    CONSTRAINT CS_PK PRIMARY KEY (Client_Id , Stream_Id)
);

drop table CM_Client_Notes;

create table CM_Client_Notes(
Id int not null auto_increment primary key,
Client_Id int Not NULL,
UserName VARCHAR(25) Not NULL,
Note  TEXT,
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

drop table CM_Client_Attnd;

create table CM_Client_Attnd(
Id int not null auto_increment primary key,
Client_Id int Not NULL,
UserName VARCHAR(25) Not NULL,
Note_Id int,
Time_date datetime,
Attended Char check (user_active in ('Y' , 'y', 'N', 'n')),
Attnd_Failed_Reason varchar(50),
Treatment_review_meeting Char check (user_active in ('Y' , 'y', 'N', 'n'))
);



