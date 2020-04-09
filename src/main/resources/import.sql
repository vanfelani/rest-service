INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (1, '2020-03-04 12:55:37.000000', NULL, 'Beras');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (2, '2020-03-04 12:55:37.000000', NULL, 'Kopi Hitam');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (3, '2020-03-04 12:55:37.000000', NULL, 'Mi Instan');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (4, '2020-03-04 12:55:37.000000', NULL, 'Mi Goreng Instan');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (5, '2020-03-04 12:55:37.000000', NULL, 'Minyak Goreng');

INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (1, '2020-03-04 12:55:37.000000', NULL, 'kg', 'kilogram');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (2, '2020-03-04 12:55:37.000000', NULL, 'g', 'gram');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (3, '2020-03-04 12:55:37.000000', NULL, 'l', 'litre');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (4, '2020-03-04 12:55:37.000000', NULL, 'pack', 'pack');

INSERT INTO stock(`created_date`,`modified_date`,`quantity`,`item_id`,`unit_id`) VALUES ('2020-03-04 06:08:28','2020-03-04 06:08:28',40,1,1);