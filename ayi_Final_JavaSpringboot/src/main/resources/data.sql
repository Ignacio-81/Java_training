
--CUSTOMERS
INSERT INTO `ayi_final_javaspringboot`.`customers` (`id`, `doc_number`, `date_created`, `date_modified`, `first_name`, `last_name`) SELECT 1, '30123456', '2022-10-11', '2022-10-11', 'Benjamín','White'
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id = 1);
INSERT INTO `ayi_final_javaspringboot`.`customers` (`id`, `doc_number`, `date_created`, `date_modified`, `first_name`, `last_name`) SELECT 2, '31234567', '2022-10-11', '2022-10-11', 'Flavia', 'Gray'
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id = 2);
INSERT INTO `ayi_final_javaspringboot`.`customers` (`id`, `doc_number`, `date_created`, `date_modified`, `first_name`,  `last_name`) SELECT 3, '32345678', '2022-10-11', '2022-10-11', 'Alberto', 'Red'
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id = 3);
INSERT INTO `ayi_final_javaspringboot`.`customers` (`id`, `doc_number`, `date_created`, `date_modified`, `first_name`, `last_name`) SELECT 4, '33568941', '2022-10-11', '2022-10-11', 'Claudia','Purpule'
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id = 4);
INSERT INTO `ayi_final_javaspringboot`.`customers` (`id`, `doc_number`, `date_created`, `date_modified`, `first_name`, `last_name`) SELECT 5, '34652840', '2022-10-11', '2022-10-11', 'Hugo', 'Orange'
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id = 5);
INSERT INTO `ayi_final_javaspringboot`.`customers` (`id`, `doc_number`, `date_created`, `date_modified`, `first_name`,  `last_name`) SELECT 6, '35069054', '2022-10-11', '2022-10-11', 'Fabian', 'Blue'
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id = 6);
--CUSTOMERS DETAILS
INSERT INTO `ayi_final_javaspringboot`.`customer_details` (`id`, `is_vip`,  `total_points`, `customer_detail_id`) SELECT 1, 1,'3000', 1
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id = 1);
INSERT INTO `ayi_final_javaspringboot`.`customer_details` (`id`, `is_vip`,  `total_points`, `customer_detail_id`) SELECT 2, 0, '1000', 2
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id = 2);
INSERT INTO `ayi_final_javaspringboot`.`customer_details` (`id`, `is_vip`,  `total_points`, `customer_detail_id`) SELECT 3, 1, '500', 3
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id = 3);
INSERT INTO `ayi_final_javaspringboot`.`customer_details` (`id`, `is_vip`,  `total_points`, `customer_detail_id`) SELECT 4, 1, '300', 4
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id = 4);
INSERT INTO `ayi_final_javaspringboot`.`customer_details` (`id`, `is_vip`,  `total_points`, `customer_detail_id`) SELECT 5, 0, '750', 5
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id = 5);
INSERT INTO `ayi_final_javaspringboot`.`customer_details` (`id`, `is_vip`,  `total_points`, `customer_detail_id`) SELECT 6, 1, '10', 6
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id = 6);
--TICKETS
INSERT INTO `ayi_final_javaspringboot`.`tickets` (`id`, `description`,  `total`, `id_customer`) SELECT '1', 'TV Smart Samsung','750', '1'
WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE id = 1);
INSERT INTO `ayi_final_javaspringboot`.`tickets` (`id`, `description`,  `total`, `id_customer`) SELECT '2', 'Heladera Samsung','690', '2'
WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE id = 2);
INSERT INTO `ayi_final_javaspringboot`.`tickets` (`id`, `description`,  `total`, `id_customer`) SELECT '3', 'Aspiradora Samsung', '120', '3'
WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE id = 3);
INSERT INTO `ayi_final_javaspringboot`.`tickets` (`id`, `description`,  `total`, `id_customer`) SELECT '4', 'Escritorio','230', '1'
WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE id = 4);
INSERT INTO `ayi_final_javaspringboot`.`tickets` (`id`, `description`,  `total`, `id_customer`) SELECT '5', 'Lavarropas Samsung','480', '4'
WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE id = 5);
INSERT INTO `ayi_final_javaspringboot`.`tickets` (`id`, `description`,  `total`, `id_customer`) SELECT '6', 'Licuadora','140', '5'
WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE id = 6);
INSERT INTO `ayi_final_javaspringboot`.`tickets` (`id`, `description`,  `total`, `id_customer`) SELECT '7', 'Perchero', '90', '6'
WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE id = 7);

--ADDRESSES
INSERT INTO `ayi_final_javaspringboot`.`address` (`id`, `apartment_letter`, `city`, `country`, `floor`,  `street_number`, `postal_code`, `state`, `street_name`, `id_customer`)
SELECT '1', 'B', 'Rosario', 'Argentina', '3', '5561', '2000', 'Santa Fe', 'Uquiza', '1'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id = 1);
INSERT INTO `ayi_final_javaspringboot`.`address` (`id`, `apartment_letter`, `city`, `country`, `floor`,  `street_number`, `postal_code`, `state`, `street_name`, `id_customer`)
SELECT '2', 'A', 'Posadas', 'Argentina', '10', '3421', '3360', 'Misiones', 'Dorrego', '2'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id = 2);
INSERT INTO `ayi_final_javaspringboot`.`address` (`id`, `apartment_letter`, `city`, `country`, `floor`,  `street_number`, `postal_code`, `state`, `street_name`, `id_customer`)
SELECT '3', 'D', 'Córdoba', 'Argentina', '6', '341', '5000', 'Córdoba', 'Moreno', '3'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id = 3);
INSERT INTO `ayi_final_javaspringboot`.`address` (`id`, `apartment_letter`, `city`, `country`, `floor`,  `street_number`, `postal_code`, `state`, `street_name`, `id_customer`)
SELECT '4', 'C', 'Rosario', 'Argentina', '1', '256', '2000', 'Santa Fe', 'Belgrano', '1'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id = 4);
INSERT INTO `ayi_final_javaspringboot`.`address` (`id`, `apartment_letter`, `city`, `country`, `floor`,  `street_number`, `postal_code`, `state`, `street_name`, `id_customer`)
SELECT '5', 'E', 'Corrientes', 'Argentina', '7', '694', '3478', 'Corrientes', 'Güemes', '4'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id = 5);
INSERT INTO `ayi_final_javaspringboot`.`address` (`id`, `apartment_letter`, `city`, `country`, `floor`,  `street_number`, `postal_code`, `state`, `street_name`, `id_customer`)
SELECT '6', 'R', 'Resistencia', 'Argentina', '9', '8451', '3501', 'Chaco', 'Saavedra', '5'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id = 6);
INSERT INTO `ayi_final_javaspringboot`.`address` (`id`, `apartment_letter`, `city`, `country`, `floor`,  `street_number`, `postal_code`, `state`, `street_name`, `id_customer`)
SELECT '7', 'F', 'Mendoza', 'Argentina', '2', '1067', '5500', 'Mendoza', 'Cabral', '6'
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id = 7);

