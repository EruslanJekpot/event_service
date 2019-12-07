--V1__Init_DB.sql
CREATE TABLE IF NOT EXISTS event (
    event_id uuid NOT NULL,
    city varchar(255),
    type varchar(255),
    image bytea,
    info varchar(255),
    max_mem_quantity integer,
    name  varchar(255),
    prize varchar(255),
    start_date date,
    organization_id uuid,
    primary key (event_id)
);

CREATE TABLE IF NOT EXISTS organization (
    organization_id uuid NOT NULL,
    email varchar(255),
    image bytea,
    info varchar(255),
    name varchar(255),
    phone varchar(255),
    user_id varchar(255),
    primary key (organization_id)
);

CREATE TABLE IF NOT EXISTS participant (
    participant_id uuid NOT NULL,
    is_team_need boolean,
    user_id varchar(255),
    event_id uuid,
    primary key (participant_id)
);

-- ALTER TABLE participant OWNER TO postgres;
--
--
-- ALTER TABLE ONLY event
--     ADD CONSTRAINT event_pkey PRIMARY KEY (event_id);
--
-- ALTER TABLE ONLY organization
--     ADD CONSTRAINT organization_pkey PRIMARY KEY (organization_id);
--
-- ALTER TABLE ONLY participant
--     ADD CONSTRAINT participant_pkey PRIMARY KEY (participant_id);
--
-- ALTER TABLE ONLY organization
--     ADD CONSTRAINT uk_8j5y8ipk73yx2joy9yr653c9t UNIQUE (name);
--
-- ALTER TABLE ONLY organization
--     ADD CONSTRAINT uk_t4bamosb7eleheafmlcalbhjf UNIQUE (email);
--
-- ALTER TABLE ONLY event
--     ADD CONSTRAINT fkkarqc3c84scr3r5ncv5stqbk2 FOREIGN KEY (organization_id) REFERENCES organization(organization_id);
--
-- ALTER TABLE ONLY participant
--     ADD CONSTRAINT fkojeqkvv72xcx0ytdqkik7objq FOREIGN KEY (event_id) REFERENCES event(event_id);