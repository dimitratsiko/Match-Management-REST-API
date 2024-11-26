--liquibase formatted sql

-- Changeset admin:v.1.0.0-1
-- This changeset creates the 'match' table, which stores information about sports matches.
-- The table includes the match's unique identifier, description, date, time, teams, and the sport type.
-- Primary Key: id
-- Foreign Key: none
-- Constraints: 'id' is a primary key and cannot be null. 'description', 'match_date', 'match_time', 'team_a', 'team_b', and 'sport' are all required fields.
CREATE TABLE IF NOT EXISTS match (
    id BIGSERIAL NOT NULL,
    description VARCHAR(1024) NOT NULL,
    match_date DATE NOT NULL,
    match_time TIME NOT NULL,
    team_a VARCHAR(256) NOT NULL,
    team_b VARCHAR(256) NOT NULL,
    sport VARCHAR(256) NOT NULL,
    CONSTRAINT match_id_pkey PRIMARY KEY (id)
);

-- Changeset admin:v.1.0.0-2
-- This changeset creates the 'match_odd' table, which stores the odds associated with each match.
-- It includes the unique identifier, the match_id (foreign key to the 'match' table), specifier (type of bet), and the actual odd value.
-- Primary Key: id
-- Foreign Key: match_id references the 'match' table's id
-- Constraints: 'odd' must be greater than or equal to 0.0 (non-negative), 'match_id' is a required foreign key.
CREATE TABLE IF NOT EXISTS match_odd (
    id BIGSERIAL NOT NULL,
    match_id BIGINT NOT NULL,
    specifier VARCHAR(50),
    odd DECIMAL(10, 2) NOT NULL CHECK ( odd >= 0.0 ),
    CONSTRAINT match_odd_id_pkey PRIMARY KEY (id),
    CONSTRAINT match_odd_match_id_fkey FOREIGN KEY (match_id) REFERENCES match(id)
);