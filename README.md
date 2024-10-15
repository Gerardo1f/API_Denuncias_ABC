BD Hecho desde PostgreSql

-- Database: ABC

-- DROP DATABASE IF EXISTS "ABC";

CREATE DATABASE "ABC"
    WITH
    OWNER = gerardo
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


	create table Empresas(
	id SERIAL PRIMARY KEY,
	nombre varchar(20) not null
	);

	insert into Empresas(nombre)
	values ('Afore Coppel'),('BanCoppel'),('Coppel');

	select * from Empresas;

	create table Pais(
	id SERIAL PRIMARY KEY,
	nombre varchar(20) not null
	);

	insert into Pais(nombre)
	values ('Argentina'),('Estados Unidos'),('Mexico');

	drop table Estados
	create table Estados(
	id SERIAL PRIMARY KEY,
	nombre varchar(20) not null,
	idPais INT references Pais(id)
	);

	insert into Estados(nombre,idPais)
	values ('Buenos Aires',1),('California',2),('Aguascalientes',3),('Campeche',3),('Cdmx',3);

	-- Crear la secuencia
	CREATE SEQUENCE folio_seq
	    START WITH 1
	    INCREMENT BY 1
	    MINVALUE 1
	    MAXVALUE 99999
	    CYCLE;

DROP TABLE DENUNCIA;
	
CREATE TABLE Denuncia (
    Id SERIAL PRIMARY KEY,  -- SERIAL crea un campo autoincremental
    Detalle TEXT not null,
    Folio VARCHAR(5) not null,
    Fecha DATE DEFAULT CURRENT_DATE  not null,
    Contrasena VARCHAR(50) not null
);

-- Crea la función que genera y actualiza el FOLIO
CREATE OR REPLACE FUNCTION generar_folio()
RETURNS TRIGGER AS $$
BEGIN
    -- Formatear el siguiente valor de la secuencia con ceros a la izquierda
    NEW.FOLIO := LPAD(nextval('folio_seq')::text, 5, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Crear el trigger que ejecuta la función después de insertar un registro
CREATE TRIGGER trg_generate_folio
BEFORE INSERT ON DENUNCIA
FOR EACH ROW
EXECUTE FUNCTION generar_folio();


INSERT INTO Denuncia (Detalle, Contrasena)
VALUES ('Descripción de la denuncia', 'hoal1234');



SELECT * FROM DENUNCIA;

drop table denuncias

create table Usuarios(
nombre varchar(100) not null,
correo_electronico varchar(100) not null,
telefono bigint not null
);

insert into Usuarios(nombre, correo_electronico, telefono)
VALUES ('roman Felix','gera@hotmail.com','6677998990');

select * from denuncias

CREATE TABLE denuncias (
    id SERIAL PRIMARY KEY,          
    empresa VARCHAR(255) NOT NULL,  
    pais VARCHAR(50) NOT NULL,      
    estado VARCHAR(100) NOT NULL,   
    centro INTEGER NOT NULL,        
    detalle TEXT NOT NULL,          
    fecha_hechos DATE NOT NULL,     
    folio VARCHAR(5) NOT NULL,     
    estatus VARCHAR(50) NOT NULL DEFAULT 'En Proceso', 
    nombre_completo VARCHAR(255),   
    correo VARCHAR(255),           
    telefono VARCHAR(50),         
    password VARCHAR(255) NOT NULL  
);
CREATE TABLE comentarios (
    id SERIAL PRIMARY KEY,         
    denuncia_id INTEGER NOT NULL,   
    texto TEXT NOT NULL,            
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    autor VARCHAR(255),           
    CONSTRAINT fk_denuncia FOREIGN KEY (denuncia_id)
);
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,          
    username VARCHAR(255) NOT NULL UNIQUE, 
    password VARCHAR(255) NOT NULL, 
    rol VARCHAR(50) NOT NULL DEFAULT 'ADMIN' 
);

select * from comentarios



CREATE TABLE estatus (
    id SERIAL PRIMARY KEY, 
    nombre VARCHAR(50) NOT NULL UNIQUE
);


INSERT INTO estatus (nombre) VALUES 
('En Proceso'),
('Finalizada'),
('Cancelada');



ALTER TABLE denuncias 
ADD COLUMN estatus_id INTEGER NOT NULL, 
ADD CONSTRAINT fk_estatus FOREIGN KEY (estatus_id) REFERENCES estatus(id) ON DELETE CASCADE;



-- 3. Agregar la columna estatus_id a la tabla denuncias permitiendo valores nulos
ALTER TABLE denuncias 
ADD COLUMN estatus_id INTEGER;

-- 4. Actualizar los registros existentes en denuncias para establecer un estatus predeterminado
UPDATE denuncias 
SET estatus_id = (SELECT id FROM estatus WHERE nombre = 'En Proceso');

-- 5. Modificar la columna estatus_id para que no acepte valores nulos
ALTER TABLE denuncias 
ALTER COLUMN estatus_id SET NOT NULL;

-- 6. Agregar la restricción de clave foránea
ALTER TABLE denuncias 
ADD CONSTRAINT fk_estatus FOREIGN KEY (estatus_id) REFERENCES estatus(id) ON DELETE CASCADE;



select * from denuncias;
select * from comentarios;

select * from estatus;


select * from usuarios

INSERT INTO usuarios (username, password, rol) 
VALUES ('admin_user', 'contraseña123', 'ADMIN');
