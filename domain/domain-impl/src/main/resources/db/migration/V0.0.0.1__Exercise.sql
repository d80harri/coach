
CREATE
    TABLE
        Entity(
            id VARCHAR(36) NOT NULL
        );

ALTER TABLE
    Entity ADD PRIMARY KEY(id);

CREATE
    TABLE
        Exercise(
            id VARCHAR(36) NOT NULL,
            name VARCHAR(256),
            description VARCHAR(256)
        );

ALTER TABLE
    Exercise ADD PRIMARY KEY(id);

ALTER TABLE
    Exercise ADD FOREIGN KEY(id) REFERENCES Entity(id);

CREATE
    TABLE
        AtomicExercise(
            id VARCHAR(36) NOT NULL
        );

ALTER TABLE
    AtomicExercise ADD PRIMARY KEY(id);

ALTER TABLE
    AtomicExercise ADD FOREIGN KEY(id) REFERENCES Exercise(id);

CREATE
    TABLE
        Flow(
            id VARCHAR(36) NOT NULL
        );

ALTER TABLE
    Flow ADD PRIMARY KEY(id);

ALTER TABLE Flow
    ADD FOREIGN KEY(id) REFERENCES Exercise(id);
