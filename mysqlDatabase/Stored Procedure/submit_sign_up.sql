USE `account`;
DROP procedure IF EXISTS `submit_sign_up`;
USE `account`;
DROP procedure IF EXISTS `account`.`submit_sign_up`;
;
DELIMITER $$ USE `account` $$ CREATE DEFINER = `root` @`localhost` PROCEDURE `submit_sign_up`(
    p_username varchar(100),
    p_firstName varchar(100),
    p_lastName varchar(100),
    p_email varchar(100),
    p_password varchar(100)
) BEGIN
INSERT INTO users (
        username,
        firstName,
        lastName,
        email,
        `password`
    )
VALUES (
        p_username,
        p_firstName,
        p_lastName,
        p_email,
        p_password
    );
END $$ DELIMITER;
;