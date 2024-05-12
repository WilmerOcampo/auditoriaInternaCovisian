create database AuditoriaCovisianPeru;
use AuditoriaCovisianPeru;

create table users(
id BIGINT primary key	auto_increment,
username varchar(255),
password varchar(255),
role varchar(255)
);
-- tabla auditores
create table Auditores (
dni_auditor int (8) primary key,
nombre_auditor varchar(50),
apellido_auditor varchar (50),
id BIGINT,
foreign key (id) references users(id)
);

-- tabla Clientes
create table Cliente (
dni_cliente int(8) primary key,
nombre_cliente varchar(50),
apellido_cliente varchar(50),
numero_cliente int(9)
);

-- tabla Empelados 
create table Empleados(
dni_empleado int (8) primary key,
area ENUM ('Atención al Cliente' , 'Ventas', 'Cross-selling', 'Hogar'),
nombre_empleado varchar(50),
apellido_empleado varchar (50),
id BIGINT,
foreign key (id) references users(id)
);

-- tabla llamadas
create table Llamadas (
numero_orden int(5) primary key,
tipo varchar(50),
area varchar(50),
subarea varchar(50),
fechahora datetime,
estado enum ('Cerrado'),  
estado_auditoria tinyint(1) default 0,-- SI FUE AUDITADA O NO
url_llamada varchar(500),
observaciones text,
dni_empleado int,
foreign key (dni_empleado)references Empleados(dni_empleado),
dni_cliente int(8),
foreign key (dni_cliente) references cliente(dni_cliente)
);

-- tabla Evaluaciones
create table Evaluaciones(
id_evaluacion int primary key auto_increment,
nota int,
fechahora datetime,
estado_lider tinyint(1) default 0, -- LIDER (SI VIO LA EVALUACION QUE LE HICIERON A SU ASESOR)
estado_firma tinyint(1) default 0, -- EMPLEADO (SI SE TOMO LA MOLESTIA DE VER LA EVALUACION QUE LE HICIERON)
observaciones_evaluacion text,
dni_empleado int(8),
foreign key (dni_empleado)references Empleados(dni_empleado), -- m a n -- MUCHAS EVALUACIONES TIENE UN EMPLEADO
dni_auditor int(8),
foreign key (dni_auditor)references Auditores(dni_auditor), -- m a n -- MUCHAS EVALUACIONES TIENE UN AUDITOR
numero_orden int(5), 
foreign key (numero_orden)references Llamadas(numero_orden) -- n a n -- UNA EVALUACION TIENE UNA LLAMADA
);

create table lider(
dni_lider int(8) primary key,
nombre_lider varchar(50),
apellido_lider varchar (50),
area ENUM ('Atencion al Cliente' , 'Ventas', 'Cross-selling', 'Hogar'),
id BIGINT,
foreign key (id) references users(id)
);

CREATE TABLE feedbacks(
id_feedback INT PRIMARY KEY	AUTO_INCREMENT,
motivo VARCHAR(500),
dni_empleado INT,
dni_lider INT,
CONSTRAINT FK_Feedbacks_Empleados FOREIGN KEY (dni_empleado) REFERENCES empleados(dni_empleado),
CONSTRAINT FK_Feedbacks_Lider FOREIGN KEY(dni_lider) REFERENCES lider(dni_lider)
);

CREATE TABLE memorandums(
id_memorandum INT PRIMARY KEY	AUTO_INCREMENT,
asunto VARCHAR(500),
cuerpo TEXT,
fecha DATETIME,
id_feedback INT,
CONSTRAINT FK_Memorandums_Feedbacks FOREIGN KEY (id_feedback) REFERENCES feedbacks(id_feedback)
);

CREATE TABLE salas(
id_sala INT PRIMARY KEY AUTO_INCREMENT,
nro_sala CHAR(6)
);

CREATE TABLE capacitaciones(
id_capacitacion INT PRIMARY KEY AUTO_INCREMENT,
tema VARCHAR(155),
detalle VARCHAR(300),
dni_lider INT,
id_sala INT,
CONSTRAINT FK_Capacitaciones_Lideres FOREIGN KEY(dni_lider) REFERENCES lider(dni_lider),
CONSTRAINT FK_Capacitaciones_Salas FOREIGN KEY(id_sala) REFERENCES salas(id_sala)
);

CREATE TABLE asistencias(
id_capacitacion INT,
dni_empleado INT,
modalidad ENUM('Presencial') DEFAULT 'Presencial',
fecha DATETIME,
CONSTRAINT FK_Asistencias_Capacitaciones FOREIGN KEY(id_capacitacion) REFERENCES capacitaciones(id_capacitacion),
CONSTRAINT FK_Asistencias_Empleados FOREIGN KEY (dni_empleado) REFERENCES empleados(dni_empleado),
PRIMARY KEY (id_capacitacion, dni_empleado)
);

/***********************************CREANDO CUENTAS***********************************/
-- ADMINISTRADOR: Contraseña: 054 
INSERT INTO users (id, username, password, role)
VALUES 
(1000,'admin', '$2y$10$guoQBDPRuQjJo0KgjrKaL.RD2.2CK6b2o9aQnCTo11RsNf8P..ZgC','ADMIN');

-- EMPLEADOS: Contraseña: 054
INSERT INTO users (id, username, password, role)
VALUES 
(1001,'sabricarpenter', '$2y$10$guoQBDPRuQjJo0KgjrKaL.RD2.2CK6b2o9aQnCTo11RsNf8P..ZgC','EMPLEADO'),
(1002,'theweeknd', '$2y$10$guoQBDPRuQjJo0KgjrKaL.RD2.2CK6b2o9aQnCTo11RsNf8P..ZgC','EMPLEADO');

-- AUDITOR: Contraseña: 054
INSERT INTO users (id, username, password, role)
VALUES 
(1003,'hcastroleo', '$2y$10$guoQBDPRuQjJo0KgjrKaL.RD2.2CK6b2o9aQnCTo11RsNf8P..ZgC','AUDITOR');

-- LIDER: Contraseña: 054
INSERT INTO users (id, username, password, role)
VALUES 
(1004,'genezi', '$2y$10$guoQBDPRuQjJo0KgjrKaL.RD2.2CK6b2o9aQnCTo11RsNf8P..ZgC','LIDER');


/***********************************CREANDO EMPLEADOS***********************************/
INSERT INTO Empleados (dni_empleado, area, nombre_empleado, apellido_empleado, id) 
VALUES 
(10203040, 'Ventas', 'Sabrina Annlynn', 'Carpenter', 1001),
(11223344, 'Ventas', 'Abel Makkonen', 'Tesfaye', 1002);


/************************************CREANDO AUDITOR***********************************/
INSERT INTO Auditores (dni_auditor, nombre_auditor, apellido_auditor, id) 
VALUES (74226963, 'Hugo Nathanael', 'Castro Leon', 1003);


/************************************CREANDO LIDER***********************************/
INSERT INTO lider (dni_lider, nombre_lider, apellido_lider, area, id) 
VALUES (50302111, 'Genesis', 'Despoux Arica', 'Ventas', 1004);


/***********************************CREANDO CLIENTES***********************************/
INSERT INTO Cliente (dni_cliente, nombre_cliente, apellido_cliente, numero_cliente)
VALUES 
(23456789,'Juan', 'Pérez',910307808),
(34567890,'Pedro', 'Martínez',913456789),
(45678901,'Laura', 'Rodríguez',915607891),
(56789012,'Diego', 'López',919081234),
(67890123,'Carolina', 'Sánchez',919872345),
(78901234,'Manuel', 'Fernández',919034567),
(89012345,'Sofía', 'Gómez',919890123);


/****CREANDO LLAMADAS****/
INSERT INTO Llamadas (numero_orden, tipo, area, subarea, estado , fechahora , url_llamada, observaciones, dni_empleado, dni_cliente)
VALUES 
(14230110, 'Venta', 'Facturacion', 'Aplicar dscto por retencion', 'Cerrado','2024-04-05 18:25:00', '~/audios/audioEjemplo1.mp3', 'Se otorgo beneficio de descuento del 20% por 3 meses.', 10203040, 23456789),
(14230111, 'Consulta', 'Procedimiento', 'Suspencion por robo', 'Cerrado','2024-04-03 17:45:00', '~/audios/audioEjemplo1.mp3', 'Suspencion Temporal.', 11223344, 45678901),
(14230112, 'Venta', 'Facturacion', 'Aplica descuento por retencion', 'Cerrado','2024-04-01 13:11:00', '~/audios/audioEjemplo1.mp3', 'Upselling + 20% por 3 meses.', 10203040, 67890123);


/***********************************CREANDO SALAS***********************************/
INSERT INTO salas(nro_sala) VALUES 
('SALA01'),('SALA02'),('SALA03'),('SALA04'),('SALA05'),('SALA06'),('SALA07'),('SALA08'),('SALA09'),('SALA10');
