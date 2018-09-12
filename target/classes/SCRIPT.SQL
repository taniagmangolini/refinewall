-- Database: refinedb

-- DROP DATABASE refinedb;
--DROP SCHEMA IF EXISTS refine CASCADE;
--CREATE SCHEMA refine;
GRANT SELECT, UPDATE, INSERT, DELETE ON ALL TABLES IN SCHEMA refine TO PUBLIC;

CREATE DATABASE refinedb
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


-- Table: public.sucest

-- DROP TABLE public.sucest;

CREATE TABLE public.sucest
(
  id character varying(255) NOT NULL,
  created_at timestamp without time zone NOT NULL,
  description character varying(255),
  sequence character varying(255),
  CONSTRAINT sucest_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.sucest
  OWNER TO postgres;

-- Table: public.search_history

-- DROP TABLE public.search_history;

CREATE TABLE public.search_history
(
  id bigint NOT NULL DEFAULT nextval('search_history_id_seq'::regclass),
  created_at timestamp without time zone NOT NULL,
  sequence character varying(255),
  CONSTRAINT search_history_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.search_history
  OWNER TO postgres;

-- Table: public.blast_result

-- DROP TABLE public.blast_result;

CREATE TABLE public.blast_result
(
  id bigint NOT NULL DEFAULT nextval('blast_result_id_seq'::regclass),
  created_at timestamp without time zone NOT NULL,
  db character varying(255),
  disabled_at timestamp without time zone,
  entry_name character varying(1024),
  evalue character varying(255),
  fulltext character varying(10000),
  gaps integer,
  gene_name character varying(1024),
  identities integer,
  length integer,
  organism_identifier character varying(1024),
  organism_name character varying(1024),
  positives integer,
  protein_existence integer,
  protein_name character varying(1024),
  score integer,
  sequence_version integer,
  sucest_busca character varying(255),
  unique_identifier character varying(1024),
  search_history_id bigint,
  sucest_id character varying(255),
  CONSTRAINT blast_result_pkey PRIMARY KEY (id),
  CONSTRAINT fk4ecqw6f7a0x7x65u40sx6uhs9 FOREIGN KEY (search_history_id)
      REFERENCES public.search_history (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fks9b7al4c8694j99ggcasmau5l FOREIGN KEY (sucest_id)
      REFERENCES public.sucest (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.blast_result
  OWNER TO postgres;


