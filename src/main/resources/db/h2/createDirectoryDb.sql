DROP TABLE directories IF EXISTS;
DROP SEQUENCE DIRECTORIES_SEQ IF EXISTS;

CREATE SEQUENCE DIRECTORIES_SEQ

  START WITH 10000;

CREATE TABLE directories
(
  id bigint default DIRECTORIES_SEQ.nextval      primary key,
  name             VARCHAR(255)            NOT NULL,
  user_id          bigint                  NOT NULL,
  parent_id        bigint                  NOT NULL,
  is_main          BOOLEAN                 NOT NULL
);