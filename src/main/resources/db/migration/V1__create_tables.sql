CREATE TABLE areas
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(500) NOT NULL,
    CONSTRAINT pk_areas PRIMARY KEY (id)
);

CREATE TABLE areas_vagas
(
    area_id BIGINT NOT NULL,
    vaga_id BIGINT NOT NULL
);

CREATE TABLE ies_vagas
(
    user_id BIGINT NOT NULL,
    vaga_id BIGINT NOT NULL
);

CREATE TABLE levels
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_levels PRIMARY KEY (id)
);

CREATE TABLE levels_vagas
(
    level_id BIGINT NOT NULL,
    vaga_id  BIGINT NOT NULL
);

CREATE TABLE periods
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_periods PRIMARY KEY (id)
);

CREATE TABLE periods_vagas
(
    period_id BIGINT NOT NULL,
    vaga_id   BIGINT NOT NULL
);

CREATE TABLE testes
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    CONSTRAINT pk_testes PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)  NOT NULL,
    cnpj          VARCHAR(255)  NOT NULL,
    ies           BIT(1)        NOT NULL,
    `description` VARCHAR(1000) NOT NULL,
    main_phone    VARCHAR(255)  NOT NULL,
    main_email    VARCHAR(255)  NOT NULL,
    other_phone   VARCHAR(255) NULL,
    other_email   VARCHAR(255) NULL,
    address_line  VARCHAR(255) NULL,
    city          VARCHAR(255)  NOT NULL,
    state         VARCHAR(255)  NOT NULL,
    country       VARCHAR(255)  NOT NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE vagas
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    owner_id          BIGINT NULL,
    title             VARCHAR(255) NOT NULL,
    `description`     VARCHAR(255) NOT NULL,
    requisites        VARCHAR(255) NULL,
    remuneration DOUBLE NOT NULL,
    weekly_workload   INT          NOT NULL,
    format            VARCHAR(255) NOT NULL,
    address_line      VARCHAR(255) NULL,
    city              VARCHAR(255) NULL,
    state             VARCHAR(255) NULL,
    country           VARCHAR(255) NULL,
    phone             VARCHAR(255) NULL,
    email             VARCHAR(255) NULL,
    renew_within_days INT          NOT NULL,
    expires_at        datetime     NOT NULL,
    created_at        datetime NULL,
    updated_at        datetime NULL,
    CONSTRAINT pk_vagas PRIMARY KEY (id)
);

ALTER TABLE vagas
    ADD CONSTRAINT FK_VAGAS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES users (id);

ALTER TABLE areas_vagas
    ADD CONSTRAINT fk_arevag_on_area_d_b_entity FOREIGN KEY (area_id) REFERENCES areas (id);

ALTER TABLE areas_vagas
    ADD CONSTRAINT fk_arevag_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);

ALTER TABLE ies_vagas
    ADD CONSTRAINT fk_ies_vagas_on_user_d_b_entity FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE ies_vagas
    ADD CONSTRAINT fk_ies_vagas_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);

ALTER TABLE levels_vagas
    ADD CONSTRAINT fk_levvag_on_level_d_b_entity FOREIGN KEY (level_id) REFERENCES levels (id);

ALTER TABLE levels_vagas
    ADD CONSTRAINT fk_levvag_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);

ALTER TABLE periods_vagas
    ADD CONSTRAINT fk_pervag_on_period_d_b_entity FOREIGN KEY (period_id) REFERENCES periods (id);

ALTER TABLE periods_vagas
    ADD CONSTRAINT fk_pervag_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);