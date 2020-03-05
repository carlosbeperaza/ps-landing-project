USE ps_landing_project;

ALTER TABLE catalogs
    DROP CONSTRAINT catalog_to_parent_FK,
    DROP COLUMN parent;

ALTER TABLE modules
    DROP CONSTRAINT module_to_parent_FK,
    DROP COLUMN parent;

CREATE TABLE sub_catalogs (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(100) NOT NULL,
    parent INT UNSIGNED NOT NULL,
    status BOOLEAN DEFAULT 0,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_date TIMESTAMP,
    CONSTRAINT sub_catalogs_parent_FK
        FOREIGN KEY (parent)
            REFERENCES catalogs (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE sub_modules (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(100) NOT NULL,
    parent INT UNSIGNED NOT NULL,
    url VARCHAR(100) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    status BOOLEAN DEFAULT 0,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_date TIMESTAMP,
    CONSTRAINT sub_modules_parent_FK
        FOREIGN KEY (parent)
            REFERENCES modules (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB;