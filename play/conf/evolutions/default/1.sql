# --- !Ups

CREATE TABLE USERS (
  ID integer NOT NULL,
  NAME varchar(255) NOT NULL,
  EMAIL varchar(255) NOT NULL,
  PASSWORD varchar(255) NOT NULL
);

INSERT INTO USERS (ID, NAME, EMAIL, PASSWORD) VALUES (1, 'marcy', 'marcy@sample.com', 'password');