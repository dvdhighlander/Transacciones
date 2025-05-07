# Transacciones
Para instalar se necesitan:

Java 17
Spring Boot 3.4.5 o mayor
MySql 8 con configuracion de Timezone: UTC
Se debe cambiar la configuración de conección a la base de datos en el archivo appliction.properties de ambos servicios por los valores propios de la BD instalada


correr los siguientes scripts en sql para crear las tablas necesarias:

CREATE SCHEMA `tiendadb` ;
CREATE TABLE `tiendadb`.`persona` (
  `idPersona` INT NOT NULL,
  `nombre` VARCHAR(100) NULL,
  `genero` VARCHAR(20) NULL,
  `edad` INT NULL,
  `identificacion` DOUBLE NULL,
  `direccion` VARCHAR(200) NULL,
  `telefono` DOUBLE NULL,
  PRIMARY KEY (`idPersona`));
  
CREATE TABLE `tiendadb`.`cliente` (
  `idCliente` INT NOT NULL,
  `idPersona` INT NOT NULL,
  `password` VARCHAR(100) NULL,
  `estado` TINYINT NULL,
   PRIMARY KEY (`idCliente`),
  INDEX `personaid_idx` (`idPersona` ASC) VISIBLE,
  CONSTRAINT `personafk`
    FOREIGN KEY (`idPersona`)
    REFERENCES `tiendadb`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tiendadb`.`cuenta` (
  `idCuenta` INT NOT NULL,
  `numeroCuenta` INT NULL,
  `tipoCuenta` VARCHAR(45) NULL,
  `saldoInicial` DOUBLE NULL,
  `estado` TINYINT NULL,
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`idCuenta`));
  INDEX `cuentaclienteid_idx` (`idCliente` ASC) VISIBLE,
  ADD CONSTRAINT `cuentaClienteFK`
  FOREIGN KEY (`idCliente`)
  REFERENCES `tiendadb`.`cliente` (`idCliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
CREATE TABLE `tiendadb`.`movimiento` (
  `idMovimiento` INT NOT NULL,
  `fecha` TIMESTAMP(6) NULL,
  `tipoMovimiento` VARCHAR(45) NULL,
  `valor` DOUBLE NULL,
  `idCuenta` INT NOT NULL,
  `saldoInicial` DOUBLE NULL,
  PRIMARY KEY (`idMovimiento`),
  INDEX `movementFK_idx` (`idCuenta` ASC) VISIBLE,
  CONSTRAINT `movementFK`
    FOREIGN KEY (`idCuenta`)
    REFERENCES `tiendadb`.`cuenta` (`idCuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

------
Los siguientes son los endpoints y los json necesarios para consumir los servicos
....
CLIENTES: 
http://localhost:8080/clientes  para GET, PUT y POST solo se cambia el método HTTP usado, en caso de POST y PUT se usa el formato JSON:
   {
        "idCliente": 2,
        "nombre": "Manuel de la Fuente",
        "genero": "Masculino",
        "edad": 27,
        "identificacion": 13499453,
        "direccion": "Calle Falsa 123",
        "telefono": 3014456943,
        "password": "MaYuNgAtOlUs",
        "estado": true
    }

para obtener solo 1 cliente y para DELETE se usa: http://localhost:8080/clientes/ID  cambiando ID por el id del cliente que se busca o se borra

....
CUENTAS:

http://localhost:8081/cuentas  para GET, PUT y POST solo se cambia el método HTTP usado, en caso de POST y PUT se usa el formato JSON:
{
    "numeroCuenta": 4422,
    "tipoCuenta": "Corriente",
    "saldoInicial": 4411,
    "estado": true,
    "idCliente": 4
}

para obtener solo 1 cliente y para DELETE se usa: http://localhost:8081/cuentas/ID  cambiando ID por el id del cliente que se busca o se borra
.....
MOVIMIENTOS:

http://localhost:8081/movimientos  para GET, PUT y POST solo se cambia el método HTTP usado, en caso de POST y PUT se usa el formato JSON:
{
    "fecha": "2025-06-11 20:21:50",
    "tipoMovimiento": "CONSIGNACION",
    "valor": 1330,
    "idCuenta": 3
}
los tipos de movimiento permitidos son:
    RETIRO,
    CONSIGNACION, 
    REEMBOLSO,
    PAGO,
    INTERESES;
    
para obtener solo 1 cliente y para DELETE se usa: http://localhost:8081/movimientos/ID  cambiando ID por el id del cliente que se busca o se borra
... 
REPORTES

Para reportes se usa el siguiente endpoint con el método GET: 
http://localhost:8081/movimientos/reportes?cliente=2&&fechas=2025-04-11--2025-04-12
el caracter "--" se usa para separar las dos fechas 
http://localhost:8081/movimientos/reportes?cliente=2&fechas=025-06-11 20:21:50spring send a date range on parameters

