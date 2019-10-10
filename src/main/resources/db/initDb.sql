DROP TABLE IF EXISTS content;
DROP TABLE IF EXISTS directories;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS users_seq;
DROP SEQUENCE IF EXISTS directories_seq;
DROP SEQUENCE IF EXISTS content_seq;

CREATE SEQUENCE users_seq START WITH 1000;
CREATE SEQUENCE directories_seq START WITH 10000;
CREATE SEQUENCE content_seq START WITH 100000;

CREATE TABLE users(
  id               INTEGER PRIMARY KEY DEFAULT nextval('users_seq'),
  name             VARCHAR                 NOT NULL,
  emailMessage            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (emailMessage);

CREATE TABLE directories(
  id                  INTEGER PRIMARY KEY DEFAULT nextval('directories_seq'),
  user_id             INTEGER               NOT NULL,
  description         VARCHAR               NOT NULL,
  parent_directory_id INTEGER                       ,
  row_position        INTEGER               NOT NUll,
  column_position     INTEGER               NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE INDEX directories_user_id_idx ON directories (user_id);

CREATE TABLE content(
  id                  INTEGER PRIMARY KEY DEFAULT nextval('content_seq'),
  directory_id        INTEGER           NOT NULL,
  comment             VARCHAR                   ,
  header              VARCHAR           NOT NULL,
  source              VARCHAR            NOT NULL,
  FOREIGN KEY (directory_id) REFERENCES directories (id) ON DELETE CASCADE
);
