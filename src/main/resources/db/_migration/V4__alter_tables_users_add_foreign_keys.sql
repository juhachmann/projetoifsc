ALTER TABLE vagas
    ADD CONSTRAINT FK_VAGAS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES users (id);

ALTER TABLE users_credentials
    ADD CONSTRAINT uc_users_credentials_user UNIQUE (user_id);

ALTER TABLE users_credentials
    ADD CONSTRAINT FK_USERS_CREDENTIALS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE ies_vagas
    ADD CONSTRAINT fk_ies_vagas_on_user_d_b_entity FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE ies_vagas
    ADD CONSTRAINT fk_ies_vagas_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);

ALTER TABLE users_profiles
    ADD CONSTRAINT uc_users_profiles_user UNIQUE (user_id);

ALTER TABLE users_profiles
    ADD CONSTRAINT FK_USERS_PROFILES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users_settings
    ADD CONSTRAINT FK_USERS_SETTINGS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
