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
    usuario_creacion character varying(50) COLLATE pg_catalog."default",
    usuario_modificacion character varying(50) COLLATE pg_catalog."default",
    direccion character varying(250) COLLATE pg_catalog."default",
    edad integer,
    genero character varying(255) COLLATE pg_catalog."default",
    identificacion character varying(100) COLLATE pg_catalog."default" NOT NULL,
    nombre character varying(250) COLLATE pg_catalog."default" NOT NULL,
    telefono character varying(250) COLLATE pg_catalog."default",
    eliminado boolean,
    CONSTRAINT persona_pkey PRIMARY KEY (id_persona)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.persona
    OWNER to postgres;
	
	
-- Table: public.cliente

-- DROP TABLE IF EXISTS public.cliente;

CREATE TABLE IF NOT EXISTS public.cliente
(
    id_cliente integer NOT NULL DEFAULT nextval('cliente_id_cliente_seq'::regclass),
    clave character varying(12) COLLATE pg_catalog."default" NOT NULL,
    id_persona integer,
    eliminado boolean,
    fecha_creacion timestamp without time zone,
    fecha_modificacion timestamp without time zone,
    usuario_creacion character varying(50) COLLATE pg_catalog."default",
    usuario_modificacion character varying(50) COLLATE pg_catalog."default",
    estado character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente),
    CONSTRAINT fklbs69o9qkvv7lgn06idak3crb FOREIGN KEY (id_persona)
        REFERENCES public.persona (id_persona) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
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
    usuario_creacion character varying(50) COLLATE pg_catalog."default",
    usuario_modificacion character varying(50) COLLATE pg_catalog."default",
    numero_cuenta integer NOT NULL,
    saldo_inicial numeric(19,2) NOT NULL,
    tipo_cuenta character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_cliente integer NOT NULL,
    eliminado boolean,
    estado character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT cuenta_pkey PRIMARY KEY (id_cuenta),
    CONSTRAINT fkmkmi3xf6wrp0y1mdn8nm4weim FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id_cliente) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
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
    usuario_creacion character varying(50) COLLATE pg_catalog."default",
    usuario_modificacion character varying(50) COLLATE pg_catalog."default",
    fecha timestamp without time zone,
    saldo numeric(19,2) NOT NULL,
    tipo_movimiento character varying(255) COLLATE pg_catalog."default" NOT NULL,
    valor numeric(19,2) NOT NULL,
    valor_retiro numeric(19,2),
    id_cuenta integer,
    CONSTRAINT movimiento_pkey PRIMARY KEY (id_movimiento),
    CONSTRAINT fk8veysyanipny5mpudj13t8873 FOREIGN KEY (id_cuenta)
        REFERENCES public.cuenta (id_cuenta) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movimiento
    OWNER to postgres;
	
	
INSERT INTO public.persona(
	id_persona, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, direccion, edad, genero, identificacion, nombre, telefono, eliminado)
	VALUES (1, '2023-01-22 17:11:38.764', '2023-01-22 17:57:18.445', 'admin', 'admin', 'La Campiña Pusuqui Pomasqui OE3N', 44, 'FEMENINO', '1714123078', 'Karla Guerrero', '025430578', false);

	
INSERT INTO public.cliente(
	id_cliente, clave, id_persona, eliminado, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, estado)
	VALUES (1, '5553333', 1, false, '2023-01-22 17:47:46.165', '2023-01-22 17:57:18.484', 'admin', 'admin', 'ACTIVO');
	

INSERT INTO public.cuenta(
	id_cuenta, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, numero_cuenta, saldo_inicial, tipo_cuenta, id_cliente, eliminado, estado)
	VALUES (2, '2023-01-22 19:37:02.41', '2023-01-22 19:40:41.09', 'admin', 'admin', 85784587, 85.20, 'AHORROS',1, false, 'ACTIVO');
	

INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 22:59:52.18','admin', '2022-01-22 22:30:00', 95.20, 'CREDITO', 95.20, 0.00,1);
INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 23:00:57.03','admin', '2022-01-22 22:40:00', 105.20, 'CREDITO', 10.00, 0.00,1);
INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 23:02:07.341','admin', '2022-01-22 22:45:00', 115.20, 'CREDITO', 10.00, 0.00,1);
INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 23:02:53.213','admin', '2022-01-22 22:48:00', 125.20, 'CREDITO', 10.00, 0.00,1);
INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 23:13:14.096','admin', '2023-01-22 15:58:00', 115.20, 'DEBITO', 10.00, 10.00,1);
INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 23:13:27.294','admin', '2023-01-22 16:08:00', 105.20, 'DEBITO', 10.00, 20.00,1);
INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 23:16:58.645','admin', '2023-01-22 16:20:00', 0.00, 'DEBITO', 105.20, 20.00,1);
INSERT INTO public.movimiento(
	id_movimiento, eliminado, fecha_creacion, usuario_creacion, fecha, saldo, tipo_movimiento, valor, valor_retiro, id_cuenta)
	VALUES (1, false, '2023-01-22 23:21:13.363	','admin', '2023-01-22 16:20:00	15.20', 95.20, 'CREDITO', 15.20, 20.00,1);
	
