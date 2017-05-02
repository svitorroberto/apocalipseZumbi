-- Table: public.inventario

-- DROP TABLE public.inventario;

CREATE TABLE public.inventario
(
    id_usuario bigint NOT NULL,
    id_item bigint NOT NULL,
    id integer NOT NULL DEFAULT nextval('inventario_id_seq'::regclass),
    CONSTRAINT inventario_pkey PRIMARY KEY (id),
    CONSTRAINT fk_id_item FOREIGN KEY (id_item)
        REFERENCES public.item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.inventario
    OWNER to postgres;
    
    
-- Table: public.item

-- DROP TABLE public.item;

CREATE TABLE public.item
(
    descricao character varying(100) COLLATE pg_catalog."default" NOT NULL,
    pontos bigint NOT NULL,
    id integer NOT NULL DEFAULT nextval('item_id_seq'::regclass),
    CONSTRAINT item_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.item
    OWNER to postgres;
    
-- Table: public.report

-- DROP TABLE public.report;

CREATE TABLE public.report
(
    id integer NOT NULL DEFAULT nextval('report_id_seq'::regclass),
    id_usuario_infectado bigint NOT NULL,
    id_usuario_reporter bigint NOT NULL,
    CONSTRAINT report_pkey PRIMARY KEY (id),
    CONSTRAINT fk_id_infectado FOREIGN KEY (id_usuario_infectado)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT k_id_reporter FOREIGN KEY (id_usuario_reporter)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.report
    OWNER to postgres;
    
-- Table: public.usuario

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario
(
    nome character varying(200) COLLATE pg_catalog."default" NOT NULL,
    idade bigint NOT NULL,
    localizacao character varying(100) COLLATE pg_catalog."default" NOT NULL,
    sexo "char" NOT NULL,
    infectado "char" NOT NULL,
    id integer NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;
