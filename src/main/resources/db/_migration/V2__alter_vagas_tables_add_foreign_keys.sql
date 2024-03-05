ALTER TABLE vagas
    ADD CONSTRAINT FK_VAGAS_ON_FORMAT FOREIGN KEY (format_id) REFERENCES formats (id);

ALTER TABLE areas_vagas
    ADD CONSTRAINT fk_arevag_on_area_d_b_entity FOREIGN KEY (area_id) REFERENCES areas (id);

ALTER TABLE areas_vagas
    ADD CONSTRAINT fk_arevag_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);

ALTER TABLE levels_vagas
    ADD CONSTRAINT fk_levvag_on_level_d_b_entity FOREIGN KEY (level_id) REFERENCES levels (id);

ALTER TABLE levels_vagas
    ADD CONSTRAINT fk_levvag_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);

ALTER TABLE periods_vagas
    ADD CONSTRAINT fk_pervag_on_period_d_b_entity FOREIGN KEY (period_id) REFERENCES periods (id);

ALTER TABLE periods_vagas
    ADD CONSTRAINT fk_pervag_on_vaga_d_b_entity FOREIGN KEY (vaga_id) REFERENCES vagas (id);
