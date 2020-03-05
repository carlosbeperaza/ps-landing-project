USE ps_landing_project;

ALTER TABLE catalogs
    ADD COLUMN create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN last_update_date TIMESTAMP,
    MODIFY COLUMN status BOOLEAN DEFAULT 0;

CREATE TABLE system_parameters (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    value VARCHAR(60) NOT NULL,
    description VARCHAR(100) NOT NULL,
    status BOOLEAN DEFAULT 0,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update_date TIMESTAMP
) ENGINE = InnoDB;