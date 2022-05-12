USE `account`;
DROP procedure IF EXISTS `get_all_users`;
USE `account`;
DROP procedure IF EXISTS `account`.`get_all_users`;
;
DELIMITER $$ USE `account` $$ CREATE DEFINER = `root` @`localhost` PROCEDURE `get_all_users`() BEGIN
select *
from users
order by firstName,
    lastName;
END $$ DELIMITER;
;