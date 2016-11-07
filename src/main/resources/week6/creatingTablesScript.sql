CREATE TABLE notebook_models (
  id      INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
  company VARCHAR(255),
  model   VARCHAR(255)
);

CREATE TABLE processors (
  id        INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
  company   VARCHAR(255),
  frequency INTEGER
);

CREATE TABLE hard_memories (
  id      INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
  company VARCHAR(255),
  size    INTEGER
);

CREATE TABLE operative_memories (
  id      INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
  company VARCHAR(255),
  size    INTEGER
);

CREATE TABLE video_memories (
  id      INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
  company VARCHAR(255),
  size    INTEGER
);

CREATE TABLE displays (
  id     INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
  width  INTEGER,
  heigth INTEGER
);

CREATE TABLE notebook_types (
  id               INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
  descr            VARCHAR(600),
  hard_memory      INTEGER,
  operative_memory INTEGER,
  processor        INTEGER,
  video_memory     INTEGER,
  model            INTEGER,
  display          INTEGER,
  price            DOUBLE,
  FOREIGN KEY (hard_memory) REFERENCES hard_memories (id),
  FOREIGN KEY (operative_memory) REFERENCES operative_memories (id),
  FOREIGN KEY (processor) REFERENCES processors (id),
  FOREIGN KEY (video_memory) REFERENCES video_memories (id),
  FOREIGN KEY (model) REFERENCES notebook_models (id),
  FOREIGN KEY (display) REFERENCES displays (id)
);


CREATE TABLE notebooks_for_sail (
  id               INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  notebook_type    INTEGER     NOT NULL,
  serial_num       VARCHAR(30) NOT NULL,
  partiya          INTEGER     NOT NULL,
  state            VARCHAR(255),
  dateStateChanged DATETIME,
  FOREIGN KEY (notebook_type) REFERENCES notebook_types (id)
);

CREATE TABLE partiyas (
  id                INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  patriya_type      INTEGER NOT NULL,
  price             DOUBLE,
  amountOfNotebooks INTEGER,
  dateOfTake        DATETIME,
  FOREIGN KEY (patriya_type) REFERENCES notebook_types (id)
);

ALTER TABLE notebooks_for_sail
  ADD FOREIGN KEY (partiya) REFERENCES partiyas (id);