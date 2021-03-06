DROP TABLE IF EXISTS urls;
DROP TABLE IF EXISTS directories;
DROP TABLE IF EXISTS records;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS users_seq;
DROP SEQUENCE IF EXISTS directories_seq;
DROP SEQUENCE IF EXISTS urls_seq;
DROP SEQUENCE IF EXISTS records_seq;

CREATE SEQUENCE users_seq START WITH 1000;
CREATE SEQUENCE directories_seq START WITH 10000;
CREATE SEQUENCE urls_seq START WITH 100000;
CREATE SEQUENCE records_seq START WITH 100;

CREATE TABLE users(
  id               INTEGER PRIMARY KEY DEFAULT nextval('users_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE directories(
  id                  INTEGER PRIMARY KEY DEFAULT nextval('directories_seq'),
  user_id             INTEGER               NOT NULL,
  name                VARCHAR               NOT NULL,
  parent_id           INTEGER                       ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE INDEX directories_user_id_idx ON directories (user_id);

CREATE TABLE urls(
  id                  INTEGER PRIMARY KEY DEFAULT nextval('urls_seq'),
  user_id             INTEGER           NOT NULL,
  url                 VARCHAR                   ,
  description         VARCHAR           NOT NULL,
  parent_id           INTEGER           NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE records(
  id                  INTEGER PRIMARY KEY DEFAULT nextval('records_seq'),
  user_id             INTEGER               NOT NULL,
  name                VARCHAR               NOT NULL,
  description         VARCHAR               NOT NULL,
  content             VARCHAR               NOT NULL,
  parent_id           INTEGER                       ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE INDEX records_user_id_idx ON records (user_id);
