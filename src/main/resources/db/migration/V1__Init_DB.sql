--V1__Init_DB.sql
create table event (
    event_id uuid not null,
    city varchar(255) not null,
    type varchar(255),
    image bytea,
    info varchar(255),
    max_mem_quantity int4,
    name varchar(255) not null,
    prize varchar(255),
    start_date date not null,
    primary key (event_id)
);

create table event_organization (
    organization_id uuid not null,
    event_id uuid not null
);

create table organization (
    organization_id uuid not null,
    email varchar(255) not null,
    image bytea,
    info varchar(255),
    name varchar(255) not null,
    phone varchar(255),
    user_id varchar(255) not null,
    primary key (organization_id)
);

create table participant (
    participant_id uuid not null,
    user_id varchar(255) not null,
    is_team_need boolean,
    event_id uuid not null,
    primary key (participant_id)
);

alter table organization
    add constraint UK_t4bamosb7eleheafmlcalbhjf
    unique (email);

alter table organization
    add constraint UK_8j5y8ipk73yx2joy9yr653c9t
    unique (name);

alter table event_organization
    add constraint FKl1ue0kq78t4toj2fdg6tif64f
    foreign key (event_id) references event;

alter table event_organization
    add constraint FKqt2wb623ivhsvsnaljcgj5gyq
     foreign key (organization_id) references organization;

alter table participant
    add constraint FKojeqkvv72xcx0ytdqkik7objq
    foreign key (event_id) references event;