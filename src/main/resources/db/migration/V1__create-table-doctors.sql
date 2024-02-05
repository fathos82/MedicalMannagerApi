CREATE TABLE doctors
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    phone      VARCHAR(20)  NOT NULL,
    crm        VARCHAR(6)   NOT NULL,
    specialty  VARCHAR(100) NOT NULL,
    street     VARCHAR(100) NOT NULL,
    number     VARCHAR(100) NOT NULL,
    complement VARCHAR(255) NOT NULL
);