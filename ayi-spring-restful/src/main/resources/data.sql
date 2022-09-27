-- INSERCIÓN DE 10 PERSONAS
INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '1', '2012-01-20', '2022-09-21', 'BENJAMIN', 'WILLIAMS', '101010', 'DNI', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '1');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '2', '2002-02-02', '2022-09-21', 'DANIEL', 'THOMAS', '202020', 'PASAPORTE', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '2');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '3', '2000-03-15', '2022-09-21', 'MATEO', 'WANE', '303030', 'CEDULA', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '3');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '4', '1990-04-30', '2022-09-21', 'FABIAN', 'DAVIS', '404040', 'DNI', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '4');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '5', '1995-05-20', '2022-09-21', 'CAMILA', 'JACKSON', '505050', 'PASAPORTE', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '5');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '6', '1998-06-02', '2022-09-21', 'ANDREA', 'THOMAS', '606060', 'CEDULA', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '6');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '7', '1980-07-15', '2022-09-21', 'ELIANA', 'CARTER', '707070', 'DNI', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '7');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '8', '1986-08-30', '2022-09-21', 'MAXIMILIANO', 'BROWN', '808080', 'PASAPORTE', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '8');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '9', '1950-09-20', '2022-09-21', 'CLAUDIA', 'FOSTER', '909090', 'CEDULA', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '9');

INSERT INTO `spring_dbayi`.`persona` (`id_persona`, `fec_nacimiento`, `fec_creacion`, `nombre`, `apellido`, `numero_documento`, `tipo_documento`, `fec_modificacion`)
SELECT '10', '1970-10-02', '2022-09-21', 'ROBERTO', 'ADAMS', '111111', 'DNI', '2022-09-22'
WHERE NOT EXISTS (SELECT * FROM persona WHERE id_persona = '10');