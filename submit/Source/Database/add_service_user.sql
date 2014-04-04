-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_service_user`(IN serviceUserXml varchar(1000) ,OUT okayReturn VARCHAR(2), OUT serviceuserID INT)
BEGIN

Declare _fname varchar(20);
Declare _sname varchar(30);
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


DECLARE cursor_sub VARCHAR(30);
DECLARE cursor_val VARCHAR(30);
DECLARE cursor_id VARCHAR(2);
DECLARE done INT DEFAULT 0;
DECLARE cursor_i CURSOR FOR SELECT substance, Reset_value FROM cm_substances;
DECLARE cursor_j CURSOR FOR SELECT eligibility_id FROM cm_eligibility;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;





select EXTRACTVALUE (serviceUserXml, '*/fname') into _fname  ;
select EXTRACTVALUE (serviceUserXml, '*/sname') into _sname  ;
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

insert into cm_clients (client_fname,
						client_sname,
						client_DOB ,
						client_gender ,
						client_contact_no ,
						client_address ,
						client_ethnicity ,
						client_nationality ,
						client_occupation ,
						client_family_info ,
						created_by,
						created_on,
						updated_by,
						updated_on, 
						Client_PPSNo)
values( CONCAT(UCASE(LEFT(_fname, 1)), SUBSTRING(_fname, 2)),  CONCAT(UCASE(LEFT(_sname, 1)), SUBSTRING(_sname, 2)),
						 _dob,
						_gender,
						_contactnumber,
						_address,
						_ethnicity,
						_nationality,
						_occupation,
						_familyinfo,
						_username,
						curdate(),
						_username,
						curdate(), _pps);

OPEN cursor_i;
  read_sub: LOOP
    FETCH cursor_i INTO cursor_sub,cursor_val;
	 IF done THEN
      LEAVE read_sub;
    END IF;
        INSERT INTO cm_client_substance_accum VALUES(LAST_INSERT_ID(),cursor_sub,cursor_val,_username, curdate());
  END LOOP;
  CLOSE cursor_i;

SET done = 0;

OPEN cursor_j;
  read_elig: LOOP
	FETCH cursor_j INTO cursor_id;
	 IF done THEN
	  LEAVE read_elig;
	END IF;
		INSERT INTO CM_Client_eligibilities VALUES(cursor_id,LAST_INSERT_ID(),'N',0,_username, curdate());
  END LOOP;
  CLOSE cursor_j;



insert into cm_client_stream values (LAST_INSERT_ID(),1, _username, curdate(),_username, curdate());

INSERT INTO `cm_system`.`cm_accounts`
(`Account_Id`,
`Account_Balance`,
`Active`,
`Updated_By`,
`Updated_On`,
`Created_By`,
`Created_On`)
VALUES
(LAST_INSERT_ID(),
0,
'Y',
 _username, curdate(),_username, curdate());


INSERT INTO `cm_system`.`cm_client_date_to_clean`
(`Client_Id`,`Card`,`Extension_applied`,`Date_to_Clean`,`Set_By`,`Set_On`)
VALUES
(LAST_INSERT_ID(),1,'N',DATE_ADD(curdate(),INTERVAL 10 DAY),_username, curdate());



set okayReturn := 'OK';
SELECT LAST_INSERT_ID() into serviceuserID;
END