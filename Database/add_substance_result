-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_substance_result`(IN serviceUserXml varchar(1000) ,OUT okayReturn VARCHAR(2))
BEGIN

Declare _serviceUser varchar(5);
Declare _substance varchar(30);
Declare _result char(1);
Declare _administeredBy varchar(30);
Declare _administeredOn varchar(15);
Declare _createdBy varchar(30);



select EXTRACTVALUE (serviceUserXml, '*/serviceuser') into _serviceUser  ;
select EXTRACTVALUE (serviceUserXml, '*/substance') into _substance  ;
select EXTRACTVALUE (serviceUserXml, '*/result') into _result  ;
select EXTRACTVALUE (serviceUserXml, '*/adminby') into _administeredBy  ;
select EXTRACTVALUE (serviceUserXml, '*/adminon') into _administeredOn  ;
select EXTRACTVALUE (serviceUserXml, '*/createdby') into _createdBy  ;


insert into cm_client_test_results (client_id,
						substance ,
						result ,
						administered_By ,
						administered_On ,
						created_by ,
						created_on )
values(
						 _serviceUser,
						_substance,
						_result,
						_administeredBy,
						_administeredOn,
						_createdBy,
						curdate());
commit;

set okayReturn := 'OK';

END