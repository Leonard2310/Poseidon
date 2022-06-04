CREATE ROLE poseidon WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  REPLICATION
  ENCRYPTED PASSWORD 'SCRAM-SHA-256$4096:UmvWDFTEp6cUK1ZJ7CiGKw==$eMZbtJDuKgseMKIVcpkqgQeFBicKuQKF6y75ewcr56E=:TmESUiBDE/uF/tu3yMiGW7ddqLM9udwQoFIhQgjPT84=';

 

 


CREATE DATABASE poseidon_db
    WITH
    OWNER = poseidon
    ENCODING = 'UTF8'
    LC_COLLATE = 'Italian_Italy.1252'
    LC_CTYPE = 'Italian_Italy.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

 

GRANT CREATE, CONNECT ON DATABASE poseidon_db TO poseidon;
GRANT TEMPORARY ON DATABASE poseidon_db TO poseidon WITH GRANT OPTION;

 

GRANT TEMPORARY, CONNECT ON DATABASE poseidon_db TO PUBLIC;

 

ALTER DEFAULT PRIVILEGES FOR ROLE postgres
GRANT ALL ON TABLES TO poseidon WITH GRANT OPTION;

 

 


CREATE TABLE public.biglietto
(
    "codiceBiglietto" integer NOT NULL,
    data date NOT NULL,
    ora time without time zone NOT NULL,
    "codiceCorsa" integer NOT NULL,
    "codiceImpiegato" integer NOT NULL,
    tipo character varying NOT NULL,
    targa character varying DEFAULT NULL,
    CONSTRAINT biglietto_pkey PRIMARY KEY ("codiceBiglietto")
)

 

CREATE TABLE public.corsa
(
    "codiceCorsa" integer NOT NULL,
    "orarioPartenza" time without time zone NOT NULL,
    "orarioArrivo" time without time zone NOT NULL,
    "portoPartenza" character varying NOT NULL,
    "portoArrivo" character varying NOT NULL,
    CONSTRAINT corsa_pkey PRIMARY KEY ("codiceCorsa")
);

 

CREATE TABLE public.porto
(
    citta character varying NOT NULL,
    CONSTRAINT porto_pkey PRIMARY KEY (citta)
);

 

CREATE TABLE public.nave
(
    nome character varying NOT NULL,
    "capienzaAutoveicoli" integer DEFAULT 0,
    "capienzaPassegeri" integer DEFAULT 0,
    prezzo double precision DEFAULT 0.0,
    categoria character varying NOT NULL,
    "codiceCorsa" integer NOT NULL,
    CONSTRAINT nave_pkey PRIMARY KEY (nome)
);

 

CREATE TABLE public.cronologiaacquisti
(
    "codiceCliente" integer NOT NULL,
    "codiceCorsa" integer NOT NULL,
    "orarioPartenza" time without time zone NOT NULL,
    "orarioArrivo" time without time zone NOT NULL,
    "portoPartenza" character varying NOT NULL,
    "portoArrivo" character varying NOT NULL,
    "codiceBiglietto" integer NOT NULL,
    data date NOT NULL,
    ora time without time zone NOT NULL,
    "codiceImpiegato" integer NOT NULL,
    ricevuta integer NOT NULL,
    CONSTRAINT cronologiaacquisti_pkey PRIMARY KEY ("codiceCliente")
);

 

CREATE TABLE public.dipendente
(
    "codiceImpiegato" integer NOT NULL,
    cognome character varying NOT NULL,
    nome character varying NOT NULL,
    password character varying NOT NULL,
    CONSTRAINT dipendente_pkey PRIMARY KEY ("codiceImpiegato")
);

 

CREATE TABLE public.cliente
(
    "codiceCliente" integer NOT NULL,
    cognome character varying NOT NULL,
    nome character varying NOT NULL,
    password character varying NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY ("codiceCliente")
);

 

 

ALTER TABLE IF EXISTS public.biglietto
    ADD CONSTRAINT biglietto_dipendente_fkey FOREIGN KEY ("codiceImpiegato")
    REFERENCES public.dipendente ("codiceImpiegato") MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION;

 

ALTER TABLE IF EXISTS public.biglietto
    ADD CONSTRAINT biglietto_corsa_fkey FOREIGN KEY ("codiceCorsa")
    REFERENCES public.corsa ("codiceCorsa") MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.corsa
    ADD CONSTRAINT corsa_portopar_fkey FOREIGN KEY ("portoPartenza")
    REFERENCES public.porto (citta) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.corsa
    ADD CONSTRAINT corsa_portoarr_fkey FOREIGN KEY ("portoArrivo")
    REFERENCES public.porto (citta) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.cronologiaacquisti
    ADD CONSTRAINT crono_biglietto_fkey FOREIGN KEY ("codiceBiglietto")
    REFERENCES public.biglietto ("codiceBiglietto") MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.cronologiaacquisti
    ADD CONSTRAINT crono_corsa_fkey FOREIGN KEY ("codiceCorsa")
    REFERENCES public.corsa ("codiceCorsa") MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;