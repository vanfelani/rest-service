INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (1, '2020-03-04 12:55:37.000000', NULL, 'Beras');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (2, '2020-03-04 12:55:37.000000', NULL, 'Kopi Hitam');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (3, '2020-03-04 12:55:37.000000', NULL, 'Mi Instan');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (4, '2020-03-04 12:55:37.000000', NULL, 'Mi Goreng Instan');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (5, '2020-03-04 12:55:37.000000', NULL, 'Minyak Goreng');

INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`) VALUES (1, '2020-03-04 12:55:37.000000', NULL, 'kg');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`) VALUES (2, '2020-03-04 12:55:37.000000', NULL, 'g');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`) VALUES (3, '2020-03-04 12:55:37.000000', NULL, 'l');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`) VALUES (4, '2020-03-04 12:55:37.000000', NULL, 'pack');

INSERT INTO transaksi(`id`,`amount`,`type`,`created_date`,`modified_date`) VALUES (1,500,'penjualan','2020-03-04 06:08:28','2020-03-04 06:08:28');

INSERT INTO stock(`created_date`,`modified_date`,`quantity`,`transaction_id`,`item_id`,`unit_id`) VALUES ('2020-03-04 06:08:28','2020-03-04 06:08:28',40,1,1,1);