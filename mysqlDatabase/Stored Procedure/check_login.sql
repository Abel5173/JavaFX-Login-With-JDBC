USE `account`;
DROP procedure IF EXISTS `check_login`;
USE `account`;
DROP procedure IF EXISTS `account`.`check_login`;
;
DELIMITER $$ USE `account` $$ CREATE DEFINER = `root` @`localhost` PROCEDURE `check_login`(
    username varchar(100),
    `password` varchar(100)
) BEGIN
Select *
from users u
where u.username = username
    AND u.`password` = `password`;
end $$ DELIMITER;
;