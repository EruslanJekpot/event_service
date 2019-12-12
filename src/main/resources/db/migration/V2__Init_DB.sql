ALTER TABLE participant OWNER TO postgres;

ALTER TABLE ONLY event
    ADD CONSTRAINT fkkarqc3c84scr3r5ncv5stqbk2 FOREIGN KEY (organization_id) REFERENCES organization(organization_id);

ALTER TABLE ONLY participant
    ADD CONSTRAINT fkojeqkvv72xcx0ytdqkik7objq FOREIGN KEY (event_id) REFERENCES event(event_id);