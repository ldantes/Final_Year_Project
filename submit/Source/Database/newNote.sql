-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `newNote`(IN _srvId varchar(5) , IN _noteId varchar(5) ,IN _note varchar(500),IN _username varchar(30),OUT okayReturn VARCHAR(2))
BEGIN

if _noteId =0 then
	INSERT INTO `cm_system`.`cm_client_notes`
	(
	`Client_Id`,
	`UserName`,
	`Note`,
	`Created_By`,
	`Created_On`,
	`Updated_On`)
	VALUES
	(_srvId,
	_username,
	_note,
	_username,
	curdate(),
	curdate());

else 
	update cm_client_notes
	set note = _note,
		updated_on = curdate()
	where id = _noteId;

end if;
set okayReturn := 'OK';

END