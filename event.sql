create table event (
    event_id serial PRIMARY KEY ,
    name varchar(128) NOT NULL ,
    start_date date NOT NULL ,
    city varchar(64) NOT NULL ,
    picture bytea NOT NULL ,
    info text ,
    prize text ,
    max_mem_quantity smallint
);

create table organization (
    org_id serial PRIMARY KEY ,
    name varchar(128) NOT NULL UNIQUE ,
    email varchar(128) NOT NULL UNIQUE ,
    phone varchar(32),
    info text
);

create table event_org (
    event_id integer REFERENCES event,
    org_id integer REFERENCES organization,
    PRIMARY KEY (event_id, org_id)
);

create table participant (
    event_id integer REFERENCES event,
    attendee_id varchar NOT NULL,
    isTeamNeed boolean default false,
    PRIMARY KEY(event_id, attendee_id)
);