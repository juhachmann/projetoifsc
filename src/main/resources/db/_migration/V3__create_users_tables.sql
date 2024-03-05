CREATE TABLE users_credentials
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    user_id  BIGINT                NOT NULL,
    username VARCHAR(255)          NOT NULL,
    password VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_users_credentials PRIMARY KEY (id)
);

CREATE TABLE users
(
    id  BIGINT AUTO_INCREMENT NOT NULL,
    ies BIT(1)                NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);


CREATE TABLE users_profiles
(
    id            BIGINT AUTO_INCREMENT   NOT NULL,
    user_id       BIGINT                  NOT NULL,
    name          VARCHAR(255)            NOT NULL,
    cnpj          VARCHAR(255)            NOT NULL,
    `description` VARCHAR(1000)           NOT NULL,
    main_phone    VARCHAR(255)            NOT NULL,
    main_email    VARCHAR(255)            NOT NULL,
    other_phone   VARCHAR(255)            NULL,
    other_email   VARCHAR(255)            NULL,
    address_line  VARCHAR(255)            NULL,
    city          VARCHAR(255)            NOT NULL,
    state         VARCHAR(255)            NOT NULL,
    country       VARCHAR(255)            NOT NULL,
    created_at    timestamp DEFAULT NOW() NULL,
    updated_at    timestamp DEFAULT NOW() NULL,
    CONSTRAINT pk_users_profiles PRIMARY KEY (id)
);

CREATE TABLE users_settings
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT                NOT NULL,
    CONSTRAINT pk_users_settings PRIMARY KEY (id)
);


CREATE TABLE ies_vagas
(
    user_id BIGINT NOT NULL,
    vaga_id BIGINT NOT NULL
);
