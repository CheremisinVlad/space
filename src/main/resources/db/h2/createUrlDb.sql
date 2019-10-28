DROP TABLE urls IF EXISTS;
DROP SEQUENCE urls_SEQ IF EXISTS;

CREATE SEQUENCE urls_SEQ

  START WITH 100000;

CREATE TABLE urls
(
  id              bigint default urls_SEQ.nextval      primary key,
  url             VARCHAR(255)            NOT NULL,
  description     VARCHAR(255)            NOT NULL,
  user_id         bigint                  NOT NULL,
  parent_id       bigint                  NOT NULL
);