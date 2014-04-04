
DELIMITER $$

DROP TRIGGER IF EXISTS setUserAccumNewSub $$

CREATE TRIGGER setUserAccumNewSub AFTER INSERT ON cm_substances
FOR EACH ROW
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE srvUserId VARCHAR(10);
    DECLARE column_cursor CURSOR FOR SELECT client_id FROM cm_clients ;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN column_cursor;

    read_loop: LOOP
        FETCH column_cursor INTO srvUserId;

        IF done THEN
            LEAVE read_loop;
        END IF;

       INSERT INTO cm_client_substance_accum  VALUES
                (srvUserId, new.substance , new.reset_value, NEW.Created_By, curdate());
        

    END LOOP;

  CLOSE column_cursor;

END$$

DELIMITER ;