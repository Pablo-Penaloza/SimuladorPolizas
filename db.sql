-- poliza_db.usuario definition

CREATE TABLE `usuario` (
                           `idUsuario` int NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(100) DEFAULT NULL,
    `apellido` varchar(100) DEFAULT NULL,
    `cedula` varchar(15) DEFAULT NULL,
    `correo` varchar(200) DEFAULT NULL,
    `celular` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`idUsuario`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- poliza_db.poliza definition

CREATE TABLE `poliza` (
                          `idPoliza` int NOT NULL AUTO_INCREMENT,
                          `montof` decimal(10,2) DEFAULT NULL,
    `capitali` decimal(10,2) DEFAULT NULL,
    `tasaInteres` decimal(5,2) DEFAULT NULL,
    `capitalizacion` int DEFAULT NULL,
    `tiempo` int DEFAULT NULL,
    `idUsuario` int DEFAULT NULL,
    PRIMARY KEY (`idPoliza`),
    KEY `fk_usuario_poliza` (`idUsuario`),
    CONSTRAINT `fk_usuario_poliza` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
    ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;