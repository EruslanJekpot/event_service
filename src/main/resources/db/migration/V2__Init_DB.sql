SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE IF NOT EXISTS public.event (
    event_id uuid NOT NULL,
    city varchar(255),
    type varchar(255),
    image bytea,
    info varchar(255),
    max_mem_quantity integer,
    name  varchar(255),
    prize varchar(255),
    start_date date,
    organization_id uuid
);

ALTER TABLE public.event OWNER TO postgres;

CREATE TABLE public.organization (
    organization_id uuid NOT NULL,
    email varchar(255),
    image bytea,
    info varchar(255),
    name varchar(255),
    phone varchar(255),
    user_id varchar(255)
);

ALTER TABLE public.organization OWNER TO postgres;

CREATE TABLE public.participant (
    participant_id uuid NOT NULL,
    is_team_need boolean,
    user_id varchar(255),
    event_id uuid
);

ALTER TABLE public.participant OWNER TO postgres;


ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (event_id);

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (organization_id);

ALTER TABLE ONLY public.participant
    ADD CONSTRAINT participant_pkey PRIMARY KEY (participant_id);

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT uk_8j5y8ipk73yx2joy9yr653c9t UNIQUE (name);

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT uk_t4bamosb7eleheafmlcalbhjf UNIQUE (email);

ALTER TABLE ONLY public.event
    ADD CONSTRAINT fkkarqc3c84scr3r5ncv5stqbk2 FOREIGN KEY (organization_id) REFERENCES public.organization(organization_id);

ALTER TABLE ONLY public.participant
    ADD CONSTRAINT fkojeqkvv72xcx0ytdqkik7objq FOREIGN KEY (event_id) REFERENCES public.event(event_id);