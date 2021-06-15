CREATE DATABASE testProject;
USE testProject;

CREATE TABLE person (
	id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
	birthdate DATETIME NOT NULL
);

CREATE TABLE car (
	id BIGINT NOT NULL PRIMARY KEY,
	model VARCHAR(255) NOT NULL,
    horsepower INTEGER NOT NULL,
    ownerId BIGINT NOT NULL,
    
    FOREIGN KEY (ownerId) REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE statistic (
    personCount BIGINT,
    carCount BIGINT,
    uniqueVendorCount BIGINT
);
