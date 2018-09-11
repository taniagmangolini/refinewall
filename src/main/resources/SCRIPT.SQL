-- Database: refinedb

-- DROP DATABASE refinedb;

CREATE DATABASE refinedb
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


CREATE TABLE sucest
(
  id varchar (255) PRIMARY KEY,
  sequence text NOT NULL,
  description varchar (255) NOT NULL,
  acc varchar (255) NULL,
  created_at timestamp NOT NULL
);

CREATE TABLE search_history
(
  id bigserial PRIMARY KEY,
  sequence text NOT NULL,
  created_at timestamp NOT NULL
);

CREATE TABLE blast_result
(
  id bigserial PRIMARY KEY,
  id_sucest  varchar (255),
  id_search bigserial,
  db VARCHAR (255) NOT NULL,
  unique_identifier VARCHAR (255) NOT NULL,
  entry_name VARCHAR (255) NOT NULL,
  protein_name VARCHAR (255) NOT NULL,
  organism_name VARCHAR (255) NOT NULL,
  organism_identifier integer NOT NULL,
  protein_existence integer NOT NULL,
  sequence_version integer NOT NULL,
  score integer NOT NULL,
  evalue VARCHAR (255) NOT NULL,
  identities integer NOT NULL,
  length integer NOT NULL,
  positives integer NOT NULL,
  gaps integer NOT NULL,
  gene_name VARCHAR (255) NULL,
  full_text VARCHAR (255) NOT NULL,
  created_at  TIMESTAMP NOT NULL,
  disabled_at  TIMESTAMP,
  CONSTRAINT sucest_id_sucest_fkey FOREIGN KEY (id_sucest)
      REFERENCES sucest (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT search_history_id_search_fkey FOREIGN KEY (id_search)
      REFERENCES search_history (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
