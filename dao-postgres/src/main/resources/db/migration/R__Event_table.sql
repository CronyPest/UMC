CREATE TABLE event (
                       id VARCHAR(255) PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       start_time TIMESTAMP NOT NULL,
                       end_time TIMESTAMP NOT NULL
);

CREATE TABLE venue_event (
                             venue_reference_id VARCHAR(255) NOT NULL,
                             event_id VARCHAR(255) NOT NULL,
                             PRIMARY KEY (venue_reference_id, event_id),
                             FOREIGN KEY (venue_reference_id) REFERENCES venue(reference_id),
                             FOREIGN KEY (event_id) REFERENCES event(id)
);

CREATE INDEX idx_venue_event_venue ON venue_event(venue_reference_id);
CREATE INDEX idx_venue_event_event ON venue_event(event_id);