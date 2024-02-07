--DROP TABLE IF EXISTS doctors CASCADE;
--DROP TABLE IF EXISTS patients CASCADE;
--DROP TABLE IF EXISTS time_slots CASCADE;

CREATE TABLE IF NOT EXISTS doctors
(
    id bigint generated always as identity primary key unique,
    uuid uuid not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    specification varchar(255) not null,
    employment_date date not null
);

CREATE TABLE IF NOT EXISTS patients
(
    id bigint generated always as identity primary key unique,
    uuid uuid not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    birthday date not null,
    number_phone varchar(10) not null,
    email varchar(255)
);

CREATE TABLE IF NOT EXISTS time_slots
(
    id bigint generated always as identity primary key unique,
    doctor_id bigint references doctors(id) not null,
    patient_id bigint references patients(id),
    date date not null,
    start_time time not null,
    end_time time not null
);