-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_attendance`(IN serviceUserXml varchar(1000) ,OUT okayReturn VARCHAR(2))
BEGIN

Declare _serviceUser varchar(5);
Declare _username varchar(30);
Declare _timedate varchar(30);
Declare _attended char(1);
Declare _missedreason varchar(50);
Declare _validreason char(1);
Declare _reviewmeeting char(1);
Declare _participated char(1);


select EXTRACTVALUE (serviceUserXml, '*/serviceuser') into _serviceUser  ;
select EXTRACTVALUE (serviceUserXml, '*/username') into _username  ;
select EXTRACTVALUE (serviceUserXml, '*/timedate') into _timedate  ;
select EXTRACTVALUE (serviceUserXml, '*/attended') into _attended  ;
select EXTRACTVALUE (serviceUserXml, '*/missedreason') into _missedreason  ;
select EXTRACTVALUE (serviceUserXml, '*/validreason') into _validreason  ;
select EXTRACTVALUE (serviceUserXml, '*/reviewmeeting') into _reviewmeeting  ;
select EXTRACTVALUE (serviceUserXml, '*/participation') into _participated  ;


INSERT INTO `cm_system`.`cm_client_attnd`(
		`Client_Id`,
		`UserName`,
		`Time_date`,
		`Attended`,
		`Attnd_Failed_Reason`,
		valid_reason,
		participated,
		`Treatment_review_meeting`)
VALUES(
		_serviceUser,
		_username,
		_timedate,
		_attended,
		_missedreason,
		_validreason,
		_participated,
		_reviewmeeting);
commit;

set okayReturn := 'OK';

END