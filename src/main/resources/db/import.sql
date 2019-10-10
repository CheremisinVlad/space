DROP TABLE users IF EXISTS;
DROP SEQUENCE USERS_SEQ IF EXISTS;

CREATE SEQUENCE USERS_SEQ

  START WITH 1000;

CREATE TABLE users
(
  id bigint default USERS_SEQ.nextval primary key,
  name             VARCHAR(255)            NOT NULL,
  emailMessage            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON USERS (emailMessage);