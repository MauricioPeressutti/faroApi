--Perfil
INSERT INTO `profile` (`code`) VALUES ('Admin');
--Persona
INSERT INTO `person` (`doc_number`, `email`, `first_name`, `last_name`, `phone`) VALUES ('11222333', 'admin@eventmanagement.com', 'Admin', 'Admin', '1122334455');
--user
INSERT INTO `user` (`default_pass`, `password`, `username`, `person_id`, `profile_id`) VALUES
('0', 'Y1fvv73vv73vv70kImzvv71e77+977+9VCnvv73vv70=', '11222333', 1, 1);
--Tipo de evento
INSERT INTO `type_event` (`id`, `description`, `code`) VALUES (NULL, 'Graduacion', 'GRAD');
INSERT INTO `type_event` (`id`, `description`, `code`) VALUES (NULL, 'Boda', 'BODA');
INSERT INTO `type_event` (`id`, `description`, `code`) VALUES (NULL, '15 años', '15CP');