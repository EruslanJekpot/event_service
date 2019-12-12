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