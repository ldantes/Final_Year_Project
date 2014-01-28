-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_service_user`(IN serviceUserXml varchar(1000) ,OUT okayReturn VARCHAR(2), OUT serviceuserID INT)
BEGIN
DECLARE _id int;
Declare _name varchar(50);
Declare _gender char(1);
Declare _dob varchar(10);
Declare _address varchar(100);
Declare _contactnumber varchar(15);
Declare _ethnicity varchar(30);
Declare _nationality varchar(30);
Declare _familyinfo varchar(30);
Declare _occupation varchar(30);
Declare _username varchar(30);
Declare _pps varchar(10);

select EXTRACTVALUE (serviceUserXml, '*/id') into _id  ;
select EXTRACTVALUE (serviceUserXml, '*/name') into _name  ;
select EXTRACTVALUE (serviceUserXml, '*/gender') into _gender  ;
select EXTRACTVALUE (serviceUserXml, '*/dob') into _dob  ;
select EXTRACTVALUE (serviceUserXml, '*/address') into _address  ;
select EXTRACTVALUE (serviceUserXml, '*/contactnumber') into _contactnumber  ;
select EXTRACTVALUE (serviceUserXml, '*/ethnicity') into _ethnicity  ;
select EXTRACTVALUE (serviceUserXml, '*/nationality') into _nationality  ;
select EXTRACTVALUE (serviceUserXml, '*/familyinfo') into _familyinfo  ;
select EXTRACTVALUE (serviceUserXml, '*/occupation') into _occupation  ;
select EXTRACTVALUE (serviceUserXml, '*/username') into _username  ;
select EXTRACTVALUE (serviceUserXml, '*/pps') into _pps  ;

SET sql_safe_updates=0;

update cm_clients set 	client_name = _name,
						client_DOB = _dob,
						client_gender = _gender,
						client_contact_no = _contactnumber,
						client_address = _address,
						client_ethnicity = _ethnicity,
						client_nationality = _nationality,
						client_occupation = _occupation,
						client_family_info = _familyinfo,
						updated_by = _username,
						updated_on = curdate(),
						Client_PPSNo = _pps
				
 where client_id = _id;
commit;
set okayReturn := 'OK';
set serviceuserID := _id;
END