CREATE DATABASE ps_landing_project;

USE ps_landing_project;

CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_date TIMESTAMP DEFAULT NULL
)ENGINE = InnoDB;

CREATE TABLE roles (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_date TIMESTAMP DEFAULT NULL
)ENGINE = InnoDB;

CREATE TABLE user_roles (
    id_user INT UNSIGNED NOT NULL,
    id_role INT UNSIGNED NOT NULL,
    CONSTRAINT id_user_FK
        FOREIGN KEY (id_user)
            REFERENCES users (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT id_role_FK
        FOREIGN KEY (id_role)
            REFERENCES roles (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)ENGINE = InnoDB;

CREATE TABLE modules (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(100) NOT NULL,
    url VARCHAR(100) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    status BOOLEAN DEFAULT 0,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_date TIMESTAMP DEFAULT NULL
)ENGINE = InnoDB;

CREATE TABLE role_modules (
    id_role INT UNSIGNED NOT NULL,
    id_module INT UNSIGNED NOT NULL,
    CONSTRAINT roles_to_role_modules_FK
        FOREIGN KEY (id_role)
            REFERENCES roles (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT modules_to_role_modules_FK
        FOREIGN KEY (id_module)
            REFERENCES modules (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE catalogs (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60)  NOT NULL,
    description VARCHAR(100) NOT NULL,
    id_father INT UNSIGNED,
    status BOOLEAN NOT NULL
);

ALTER TABLE catalogs
    CHANGE id_father parent INT UNSIGNED DEFAULT NULL,
    ADD CONSTRAINT catalog_to_parent_FK
        FOREIGN KEY (parent)
            REFERENCES catalogs (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ENGINE = InnoDB;

ALTER TABLE modules
    ADD COLUMN parent INT UNSIGNED DEFAULT NULL AFTER description,
    ADD CONSTRAINT module_to_parent_FK
        FOREIGN KEY (parent)
            REFERENCES modules (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;