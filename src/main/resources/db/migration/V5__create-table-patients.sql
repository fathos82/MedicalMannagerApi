CREATE TABLE patients
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    cpf      VARCHAR(11)  NOT NULL,
    active TINYINT(1) DEFAULT 1
);
