INSERT IGNORE INTO `security2`.`user` (`id`, `username`, `password`, `algorithm`) VALUES ('1', 'john', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT');
INSERT IGNORE INTO `security2`.`user` (`id`, `username`, `password`, `algorithm`) VALUES ('2', 'bob', '$e0801$UjkWNaqcrCHcciAqQc8FKeNGII5KlMTIMRHWKZYeeaaHkewZy/CnqTp3WLnFsUR1UAKSGYWT/Bptjl8sNIma9Q==$1kZU1CAs3K+/wM5R58gA3WYjK+PgVius30TtjDSR4QE=', 'SCRYPT');

INSERT IGNORE INTO `security2`.`authority` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
INSERT IGNORE INTO `security2`.`authority` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');
INSERT IGNORE INTO `security2`.`authority` (`id`, `name`, `user`) VALUES ('3', 'READ', '2');
INSERT IGNORE INTO `security2`.`authority` (`id`, `name`, `user`) VALUES ('4', 'WRITE', '2');

INSERT IGNORE INTO `security2`.`product` (`id`, `name`, `price`, `currency`) VALUES ('1', 'Chocolate', '10', 'USD');
