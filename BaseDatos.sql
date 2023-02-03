-- Database: bancoFuturo

-- DROP DATABASE IF EXISTS "bancoFuturo";

CREATE DATABASE "bancoFuturo"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
	
-- Table: public.persona

-- DROP TABLE IF EXISTS public.persona;

CREATE TABLE IF NOT EXISTS public.persona
(
    id_persona integer NOT NULL DEFAULT nextval('persona_id_persona_seq'::regclass),
    fecha_creacion timestamp without time zone,
    fecha_modificacion timestamp without time zone,
    usuario_creacion character varying(50),
    usuario_modificacion character varying(50),
    direccion character varying(250),
    edad integer,
    genero character varying(255),
    identificacion character varying(100)NOT NULL,
    nombre character varying(250) NOT NULL,
    telefono character varying(250),
    eliminado boolean,
    CONSTRAINT persona_pk PRIMARY KEY (id_persona)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.persona
    OWNER to postgres;
	
	
-- Table: public.cliente

-- DROP TABLE IF EXISTS public.cliente;

CREATE TABLE IF NOT EXISTS public.cliente
(
    id_cliente integer NOT NULL DEFAULT nextval('cliente_id_cliente_seq'::regclass),
    clave character varying(12) NOT NULL,
    id_persona integer,
    eliminado boolean,
    fecha_creacion timestamp without time zone,
    fecha_modificacion timestamp without time zone,
    usuario_creacion character varying(50),
    usuario_modificacion character varying(50)
    estado character varying(255),
    CONSTRAINT cliente_pk PRIMARY KEY (id_cliente),
    CONSTRAINT fk_persona FOREIGN KEY (id_persona)
        REFERENCES public.persona (id_persona) 
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cliente
    OWNER to postgres;
	
	
	
-- Table: public.cuenta

-- DROP TABLE IF EXISTS public.cuenta;

CREATE TABLE IF NOT EXISTS public.cuenta
(
    id_cuenta integer NOT NULL DEFAULT nextval('cuenta_id_cuenta_seq'::regclass),
    fecha_creacion timestamp without time zone,
    fecha_modificacion timestamp without time zone,
    usuario_creacion character varying(50),
    usuario_modificacion character varying(50),
    numero_cuenta integer NOT NULL,
    saldo_inicial numeric(19,2) NOT NULL,
    tipo_cuenta character varying(255) NOT NULL,
    id_cliente integer NOT NULL,
    eliminado boolean,
    estado character varying(255),
    CONSTRAINT cuenta_pk PRIMARY KEY (id_cuenta),
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id_cliente) 
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cuenta
    OWNER to postgres;
	
	
-- Table: public.movimiento

-- DROP TABLE IF EXISTS public.movimiento;

CREATE TABLE IF NOT EXISTS public.movimiento
(
    id_movimiento integer NOT NULL DEFAULT nextval('movimiento_id_movimiento_seq'::regclass),
    eliminado boolean,
    fecha_creacion timestamp without time zone,
    fecha_modificacion timestamp without time zone,
    usuario_creacion character varying(50) ,
    usuario_modificacion character varying(50) ,
    fecha timestamp without time zone,
    saldo numeric(19,2) NOT NULL,
    tipo_movimiento character varying(255) NOT NULL,
    valor numeric(19,2) NOT NULL,
    valor_retiro numeric(19,2),
    id_cuenta integer,
    CONSTRAINT movimiento_pk PRIMARY KEY (id_movimiento),
    CONSTRAINT fk_cuenta FOREIGN KEY (id_cuenta)
        REFERENCES public.cuenta (id_cuenta) 
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movimiento
    OWNER to postgres;
	
	

INSERT INTO public.persona(
	id_persona, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, direccion, edad, genero, identificacion, nombre, telefono, eliminado)
	VALUES (1, '2023-01-22 17:11:38.764', '2023-01-22 17:57:18.445', 'admin', 'admin', 'Otavalo sn y principal', 24, 'MASCULINO', '0718793078', 'José Lema', '098254785', false);
INSERT INTO public.persona(
	id_persona, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, direccion, edad, genero, identificacion, nombre, telefono, eliminado)
	VALUES (2, '2023-01-22 17:11:38.764', '2023-01-22 17:57:18.445', 'admin', 'admin', 'Amazonas y NNUU', 34, 'FEMENINO', '1518793078', 'Marianela Montalvo', '097548965', false);
INSERT INTO public.persona(
	id_persona, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, direccion, edad, genero, identificacion, nombre, telefono, eliminado)
	VALUES (3, '2023-01-22 17:11:38.764', '2023-01-22 17:57:18.445', 'admin', 'admin', '13 junio y Equinoccial', 42, 'MASCULINO', '0118793078', 'Juan Osorio', '098874587', false);
INSERT INTO public.persona(
	id_persona, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, direccion, edad, genero, identificacion, nombre, telefono, eliminado)
	VALUES (4, '2023-01-22 17:11:38.764', '2023-01-22 17:57:18.445', 'admin', 'admin', 'La Campiña Pusuqui Pomasqui OE3N', 44, 'FEMENINO', '1714123078', 'Karla Guerrero', '025430578', false);



INSERT INTO public.cliente(
	id_cliente, clave, id_persona, eliminado, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, estado)
	VALUES (1, '1234', 1, false, '2023-01-22 17:47:46.165', '2023-01-22 17:57:18.484', 'admin', 'admin', 'ACTIVO');
INSERT INTO public.cliente(
	id_cliente, clave, id_persona, eliminado, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, estado)
	VALUES (2, '5678', 2, false, '2023-01-22 17:47:46.165', '2023-01-22 17:57:18.484', 'admin', 'admin', 'ACTIVO');
INSERT INTO public.cliente(
	id_cliente, clave, id_persona, eliminado, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, estado)
	VALUES (3, '1245', 3, false, '2023-01-22 17:47:46.165', '2023-01-22 17:57:18.484', 'admin', 'admin', 'ACTIVO');
INSERT INTO public.cliente(
	id_cliente, clave, id_persona, eliminado, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, estado)
	VALUES (4, '112233', 4, false, '2023-01-22 17:47:46.165', '2023-01-22 17:57:18.484', 'admin', 'admin', 'ACTIVO');



INSERT INTO public.cuenta(
	id_cuenta, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, numero_cuenta, saldo_inicial, tipo_cuenta, id_cliente, eliminado, estado)
	VALUES (1, '2023-01-22 19:37:02.41', '2023-01-22 19:40:41.09', 'admin', 'admin', 478758, 2000.00, 'AHORROS',1, false, 'ACTIVO');
INSERT INTO public.cuenta(
	id_cuenta, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, numero_cuenta, saldo_inicial, tipo_cuenta, id_cliente, eliminado, estado)
	VALUES (2, '2023-01-22 19:37:02.41', '2023-01-22 19:40:41.09', 'admin', 'admin', 225487, 100.00, 'CORRIENTE',2, false, 'ACTIVO');
INSERT INTO public.cuenta(
	id_cuenta, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, numero_cuenta, saldo_inicial, tipo_cuenta, id_cliente, eliminado, estado)
	VALUES (3, '2023-01-22 19:37:02.41', '2023-01-22 19:40:41.09', 'admin', 'admin', 495878, 0, 'AHORROS',3, false, 'ACTIVO');
INSERT INTO public.cuenta(
	id_cuenta, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, numero_cuenta, saldo_inicial, tipo_cuenta, id_cliente, eliminado, estado)
	VALUES (4, '2023-01-22 19:37:02.41', '2023-01-22 19:40:41.09', 'admin', 'admin', 496825, 540.00, 'AHORROS',2, false, 'ACTIVO');
	

