TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER setUserAccumNewSub AFTER INSERT ON cm_substances\nFOR EACH ROW\nBEGIN\n    DECLARE done INT DEFAULT FALSE;\n    DECLARE srvUserId VARCHAR(10);\n    DECLARE column_cursor CURSOR FOR SELECT client_id FROM cm_clients ;\n    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;\n\n    OPEN column_cursor;\n\n    read_loop: LOOP\n        FETCH column_cursor INTO srvUserId;\n\n        IF done THEN\n            LEAVE read_loop;\n        END IF;\n\n       INSERT INTO cm_client_substance_accum  VALUES\n                (srvUserId, new.substance , new.reset_value, NEW.Created_By, curdate());\n        \n\n    END LOOP;\n\n  CLOSE column_cursor;\n\nEND'
sql_modes=0
definers='root@localhost'
client_cs_names='utf8'
connection_cl_names='utf8_general_ci'
db_cl_names='utf8_general_ci'
