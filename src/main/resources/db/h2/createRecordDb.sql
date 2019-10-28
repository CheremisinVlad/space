DROP TABLE records IF EXISTS;
DROP SEQUENCE RECORDS_SEQ IF EXISTS;

CREATE SEQUENCE RECORDS_SEQ

  START WITH 100;

CREATE TABLE records
(
  id bigint default RECORDS_SEQ.nextval      primary key,
  name             VARCHAR(255)            NOT NULL,
  description      VARCHAR(255)            NOT NULL,
  content          VARCHAR(255)            NOT NULL,
  user_id          bigint                  NOT NULL,
  parent_id        bigint                  NOT NULL
);