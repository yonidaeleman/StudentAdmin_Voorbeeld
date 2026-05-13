DROP TABLE IF EXISTS inschrijving_cursus;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS inschrijving;
DROP TABLE IF EXISTS cursus;

CREATE TABLE cursus (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    coursepoints INT,
    coordinator VARCHAR(255)
);

CREATE TABLE inschrijving (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course VARCHAR(255),
    date varchar(255),
    duration INT,
    bedrag INT
);

CREATE TABLE inschrijving_cursus (
    inschrijving_id BIGINT,
    cursus_id BIGINT,
    PRIMARY KEY (inschrijving_id, cursus_id),
    FOREIGN KEY (inschrijving_id) REFERENCES inschrijving(id),
    FOREIGN KEY (cursus_id) REFERENCES cursus(id)
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    inschrijving_id BIGINT,
    FOREIGN KEY (inschrijving_id) REFERENCES inschrijving(id)
);