CREATE TABLE areas
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(500)          NOT NULL,
    CONSTRAINT pk_areas PRIMARY KEY (id)
);

CREATE TABLE formats
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_formats PRIMARY KEY (id)
);

CREATE TABLE levels
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    `order` INT                   NOT NULL,
    name    VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_levels PRIMARY KEY (id)
);

CREATE TABLE periods
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_periods PRIMARY KEY (id)
);

CREATE TABLE levels_vagas
(
    level_id BIGINT NOT NULL,
    vaga_id  BIGINT NOT NULL
);

CREATE TABLE periods_vagas
(
    period_id BIGINT NOT NULL,
    vaga_id   BIGINT NOT NULL
);

CREATE TABLE areas_vagas
(
    area_id BIGINT NOT NULL,
    vaga_id BIGINT NOT NULL
);

CREATE TABLE vagas
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    owner_id          BIGINT                NOT NULL,
    exclusive         BIT(1)                NOT NULL,
    title             VARCHAR(255)          NOT NULL,
    `description`     VARCHAR(255)          NOT NULL,
    requisites        VARCHAR(255)          NULL,
    remuneration      DOUBLE                NOT NULL,
    weekly_workload   INT                   NOT NULL,
    format_id         BIGINT                NOT NULL,
    address_line      VARCHAR(255)          NULL,
    city              VARCHAR(255)          NULL,
    state             VARCHAR(255)          NULL,
    country           VARCHAR(255)          NULL,
    phone             VARCHAR(255)          NULL,
    email             VARCHAR(255)          NULL,
    renew_within_days INT                   NOT NULL,
    expires_at        datetime              NOT NULL,
    created_at        datetime              NULL,
    updated_at        datetime              NULL,
    CONSTRAINT pk_vagas PRIMARY KEY (id)
);
