-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `adjust_balance`(IN srvId varchar(5) ,IN credit numeric(5 , 2 ),IN withdraw numeric(5 , 2 ),IN username varchar(30),OUT okayReturn VARCHAR(2))
BEGIN

Declare _serviceUser varchar(5);
Declare _withdrawn numeric(5 , 2 );
declare _credited numeric(5 , 2 );
Declare _username varchar(30);

select srvId into _serviceUser  ;
select withdraw into _withdrawn  ;
select credit into _credited  ;
select username into _username  ;

INSERT INTO `cm_system`.`cm_transactions`
(
`Account_Id`,
`Amount_Withdrawn`,
`Amount_Credited`,
`Approved_By`,
`Date_of_Transaction`)
VALUES
(
_serviceUser,
_withdrawn,
_credited,
_username,
curdate());

update cm_accounts set account_Balance = (account_Balance + _credited - _withdrawn),
						updated_by = _username,
						updated_on = curdate() 
					where account_id = _serviceUser;

set okayReturn := 'OK';

END