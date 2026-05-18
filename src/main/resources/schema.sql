DROP TABLE IF EXISTS richting_cursus;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS richting;
DROP TABLE IF EXISTS cursus;

CREATE TABLE cursus (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE ,
    coursepoints INT,
    coordinator VARCHAR(255)
);

CREATE TABLE richting (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    date varchar(255),
    duration INT,
    bedrag INT
);

CREATE TABLE richting_cursus (
    richting_id BIGINT,
    cursus_id BIGINT,
    PRIMARY KEY (richting_id, cursus_id),
    FOREIGN KEY (richting_id) REFERENCES richting(id),
    FOREIGN KEY (cursus_id) REFERENCES cursus(id)
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    richting_id BIGINT,
    FOREIGN KEY (richting_id) REFERENCES richting(id)
);