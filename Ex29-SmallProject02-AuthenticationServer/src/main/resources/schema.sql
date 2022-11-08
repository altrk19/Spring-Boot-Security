CREATE TABLE IF NOT EXISTS `securityProject2`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` TEXT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `securityProject2`.`otp` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `code` TEXT NOT NULL,
  PRIMARY KEY (`id`));