--liquibase formatted sql

-- Changeset admin:v.1.0.0-1
-- This changeset alter the 'match' table, which stores information about sports matches.
-- It includes two custom tables for each sport football_notes, basketball_notes.
ALTER TABLE IF EXISTS match
ADD COLUMN IF NOT EXISTS football_notes VARCHAR(1024),
ADD COLUMN IF NOT EXISTS basketball_notes VARCHAR(1024);