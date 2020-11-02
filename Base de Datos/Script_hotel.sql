-- MySQL Script generated by MySQL Workbench
-- Sun Nov  1 16:11:40 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema proyecto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema proyecto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyecto` DEFAULT CHARACTER SET utf8 ;
USE `proyecto` ;

-- -----------------------------------------------------
-- Table `proyecto`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(50) NOT NULL,
  `apellido_paterno` VARCHAR(45) NOT NULL,
  `apellido_materno` VARCHAR(45) NOT NULL,
  `tipo_documento` VARCHAR(20) NOT NULL,
  `num_documento` VARCHAR(15) NOT NULL,
  `celular` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyecto`.`habitacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`habitacion` (
  `idHabitacion` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(3) NOT NULL,
  `piso` VARCHAR(1) NOT NULL,
  `precio` DECIMAL(10,0) NOT NULL,
  `tipo_habitacion` VARCHAR(20) NOT NULL,
  `estado_habitacion` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idHabitacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyecto`.`trabajador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`trabajador` (
  `idTrabajador` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL,
  `apellido_paterno_trabajador` VARCHAR(45) NOT NULL,
  `apellido_materno_trabajador` VARCHAR(45) NOT NULL,
  `tipo_documento_trabajador` VARCHAR(20) NOT NULL,
  `num_documento_trabajador` VARCHAR(15) NOT NULL,
  `celular_trabajador` VARCHAR(15) NOT NULL,
  `email_trabajador` VARCHAR(45) NOT NULL,
  `sueldo` DECIMAL NOT NULL,
  `acceso` VARCHAR(20) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `estado_trabajador` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idTrabajador`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyecto`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`reserva` (
  `idReserva` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `idTrabajador` INT NOT NULL,
  `idHabitacion` INT NOT NULL,
  `tipo_reserva` VARCHAR(20) NOT NULL,
  `fecha_reserva` DATE NOT NULL,
  `fecha_ingreso` DATE NOT NULL,
  `fecha_salida` DATE NOT NULL,
  `costo_alojamiento` DECIMAL(10,0) NOT NULL,
  `estado_reserva` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idReserva`),
  INDEX `fk_reserva_habitacion_idx` (`idHabitacion` ASC) VISIBLE,
  INDEX `fk_reserva_cliente_idx` (`idCliente` ASC) VISIBLE,
  INDEX `fk_reserva_trabajador_idx` (`idTrabajador` ASC) VISIBLE,
  CONSTRAINT `fk_reserva_cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `proyecto`.`cliente` (`idCliente`),
  CONSTRAINT `fk_reserva_habitacion`
    FOREIGN KEY (`idHabitacion`)
    REFERENCES `proyecto`.`habitacion` (`idHabitacion`),
  CONSTRAINT `fk_reserva_trabajador`
    FOREIGN KEY (`idTrabajador`)
    REFERENCES `proyecto`.`trabajador` (`idTrabajador`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyecto`.`pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`pago` (
  `idPago` INT NOT NULL AUTO_INCREMENT,
  `idReserva` INT NOT NULL,
  `tipo_comprobante` VARCHAR(20) NOT NULL,
  `num_comprobante` VARCHAR(20) NOT NULL,
  `igv` DECIMAL(10,0) NOT NULL,
  `total_pago` DECIMAL(10,0) NOT NULL,
  `fecha_emision` DATE NOT NULL,
  `fecha_pago` DATE NOT NULL,
  PRIMARY KEY (`idPago`),
  INDEX `fk_pago_reserva_idx` (`idReserva` ASC) VISIBLE,
  CONSTRAINT `fk_pago_reserva`
    FOREIGN KEY (`idReserva`)
    REFERENCES `proyecto`.`reserva` (`idReserva`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proyecto`.`movimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`movimiento` (
  `idMovimiento` INT NOT NULL AUTO_INCREMENT,
  `nombreCliente` VARCHAR(45) NULL,
  `apellido_p_Cliente` VARCHAR(45) NULL,
  `apellido_m_Cliente` VARCHAR(45) NULL,
  `habitacionCliente` VARCHAR(45) NULL,
  `fecha_hora_ingreso` TIMESTAMP NULL,
  `fecha_hora_salida` TIMESTAMP NULL,
  `estado_movimiento` TINYINT(1) NULL,
  PRIMARY KEY (`idMovimiento`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TRIGGER mov_trig AFTER INSERT ON cliente FOR EACH ROW INSERT INTO movimiento (idMovimiento, nombreCliente, apellido_p_Cliente, apellido_m_Cliente, habitacionCliente, fecha_hora_ingreso, fecha_hora_salida, estado_movimiento)
VALUES (null, NEW.nombres, NEW.apellido_paterno, NEW.apellido_materno, null, null,null,1);

INSERT INTO `proyecto`.`trabajador`(`idTrabajador`,
`nombre`,
`apellido_paterno_trabajador`,
`apellido_materno_trabajador`,
`tipo_documento_trabajador`,
`num_documento_trabajador`,
`celular_trabajador`,
`email_trabajador`,
`sueldo`,
`acceso`,
`login`,
`password`,
`estado_trabajador`)
VALUES
(null,
"Diego",
"Tasaico",
"Freundt",
"DNI",
"73194743",
"966107790",
"tasaicod4@gmail.com",
1500,
"Administrador",
"userdiego",
"1234",
1);

INSERT INTO `proyecto`.`trabajador`(`idTrabajador`,
`nombre`,
`apellido_paterno_trabajador`,
`apellido_materno_trabajador`,
`tipo_documento_trabajador`,
`num_documento_trabajador`,
`celular_trabajador`,
`email_trabajador`,
`sueldo`,
`acceso`,
`login`,
`password`,
`estado_trabajador`)
VALUES
(null,
"Mauricio",
"Villanueva",
"Yaranga",
"DNI",
"73191743",
"966106790",
"mauvill@gmail.com",
1500,
"Administrador",
"usermau",
"1234",
1);

INSERT INTO `proyecto`.`trabajador`(`idTrabajador`,
`nombre`,
`apellido_paterno_trabajador`,
`apellido_materno_trabajador`,
`tipo_documento_trabajador`,
`num_documento_trabajador`,
`celular_trabajador`,
`email_trabajador`,
`sueldo`,
`acceso`,
`login`,
`password`,
`estado_trabajador`)
VALUES
(null,
"Olenka",
"Lazo",
"Pichi",
"DNI",
"73194793",
"966109790",
"ole4@gmail.com",
1500,
"Administrador",
"userole",
"1234",
1);

INSERT INTO `proyecto`.`trabajador`(`idTrabajador`,
`nombre`,
`apellido_paterno_trabajador`,
`apellido_materno_trabajador`,
`tipo_documento_trabajador`,
`num_documento_trabajador`,
`celular_trabajador`,
`email_trabajador`,
`sueldo`,
`acceso`,
`login`,
`password`,
`estado_trabajador`)
VALUES
(null,
"Abihail",
"Zapata",
"Zamora",
"DNI",
"73194143",
"916107790",
"abih@gmail.com",
1500,
"Administrador",
"userabi",
"1234",
1);