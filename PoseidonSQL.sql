CREATE ROLE poseidon WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  REPLICATION
  PASSWORD 'project';

 


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
    codicebiglietto integer NOT NULL,
    data date NOT NULL,
    ora time without time zone NOT NULL,
    codicecorsa integer NOT NULL,
    codiceimpiegato integer NOT NULL,
    tipo character varying NOT NULL,
    targa character varying DEFAULT NULL,
    CONSTRAINT biglietto_pkey PRIMARY KEY (codicebiglietto, codicecorsa)
);

 

CREATE TABLE public.corsa
(
    codicecorsa integer NOT NULL,
    orariopartenza time without time zone NOT NULL,
    orarioarrivo time without time zone NOT NULL,
    portopartenza character varying NOT NULL,
    portoarrivo character varying NOT NULL,
    prezzo double precision DEFAULT 0.0,
    CONSTRAINT corsa_pkey PRIMARY KEY (codicecorsa)
);

 

CREATE TABLE public.porto
(
    citta character varying NOT NULL,
    CONSTRAINT porto_pkey PRIMARY KEY (citta)
);

 

CREATE TABLE public.nave
(
    nome character varying NOT NULL,
    capienzaautoveicoli integer DEFAULT 0,
    capienzapassegeri integer DEFAULT 0,
    categoria character varying NOT NULL,
    codicecorsa integer NOT NULL,
    CONSTRAINT nave_pkey PRIMARY KEY (nome)
);

 

CREATE TABLE public.cronologiaacquisti
(
    codicecliente integer NOT NULL,
    codicecorsa integer NOT NULL,
    orariopartenza time without time zone NOT NULL,
    orarioarrivo time without time zone NOT NULL,
    portopartenza character varying NOT NULL,
    portoarrivo character varying NOT NULL,
    prezzo double precision NOT NULL,
    codicebiglietto integer DEFAULT NULL,
    data date DEFAULT NULL,
    ora time without time zone DEFAULT NULL,
    codiceimpiegato integer DEFAULT NULL,
    ricevuta integer NOT NULL,
    CONSTRAINT cronologiaacquisti_pkey PRIMARY KEY (codicecliente, codicecorsa, ricevuta)
);

 

CREATE TABLE public.dipendente
(
    codiceimpiegato integer NOT NULL,
    cognome character varying NOT NULL,
    nome character varying NOT NULL,
    password character varying NOT NULL,
    CONSTRAINT dipendente_pkey PRIMARY KEY (codiceimpiegato)
);

 

CREATE TABLE public.cliente
(
    codicecliente integer NOT NULL,
    cognome character varying NOT NULL,
    nome character varying NOT NULL,
    password character varying NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (codicecliente)
);

 

 

ALTER TABLE IF EXISTS public.biglietto
    ADD CONSTRAINT biglietto_dipendente_fkey FOREIGN KEY (codiceimpiegato)
    REFERENCES public.dipendente (codiceimpiegato) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION;

 

ALTER TABLE IF EXISTS public.biglietto
    ADD CONSTRAINT biglietto_corsa_fkey FOREIGN KEY (codicecorsa)
    REFERENCES public.corsa (codicecorsa) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.corsa
    ADD CONSTRAINT corsa_portopar_fkey FOREIGN KEY (portopartenza)
    REFERENCES public.porto (citta) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.corsa
    ADD CONSTRAINT corsa_portoarr_fkey FOREIGN KEY (portoarrivo)
    REFERENCES public.porto (citta) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.cronologiaacquisti
    ADD CONSTRAINT crono_biglietto_fkey FOREIGN KEY (codicebiglietto, codicecorsa)
    REFERENCES public.biglietto (codicebiglietto, codicecorsa) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;

 

ALTER TABLE IF EXISTS public.cronologiaacquisti
    ADD CONSTRAINT crono_corsa_fkey FOREIGN KEY (codicecorsa)
    REFERENCES public.corsa (codicecorsa) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;



ALTER TABLE IF EXISTS public.cronologiaacquisti
    ADD CONSTRAINT crono_cliente_fkey FOREIGN KEY (codicecliente)
    REFERENCES public.cliente (codicecliente) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE;



insert into dipendente values (1001, 'Catello', 'Leonardo', 'acquisto');
insert into dipendente values (1002, 'Cipollaro', 'Daiana', 'inserimento');
insert into dipendente values (1003, 'Di Serio', 'Francesco', 'emissione');

insert into cliente values (10001, 'Catello', 'Leonardo', 'acquisto');
insert into cliente values (10002, 'Cipollaro', 'Daiana', 'inserimento');
insert into cliente values (10003, 'Di Serio', 'Francesco', 'emissione');

insert into porto values ('Napoli');
insert into porto values ('Ischia');
insert into corsa values (101, '10:00', '11:00', 'Napoli', 'Ischia', 20.00);
insert into corsa values (102, '16:00', '17:00', 'Ischia', 'Napoli', 20.00);