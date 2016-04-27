-- EXPAND
----------------------------------------------------------------------------
INSERT
	INTO
		ENTITY
	VALUES('51fe7b57-719f-4ab8-8586-19620e9913af');

INSERT
	INTO
		EXERCISE
	VALUES(
		'51fe7b57-719f-4ab8-8586-19620e9913af',
		'PLACEHOLDER',
		'Placeholder for FlowItems w/o exercise'
	);

INSERT
	INTO
		ATOMICEXERCISE
	VALUES('51fe7b57-719f-4ab8-8586-19620e9913af');

ALTER TABLE
	FLOWITEM ADD exercise_id VARCHAR(36);

ALTER TABLE
	FLOWITEM ADD FOREIGN KEY(exercise_id) REFERENCES AtomicExercise(id);

-- UPDATE
---------------------------------------------------------------------------
UPDATE
	FLOWITEM
SET
	exercise_id = '51fe7b57-719f-4ab8-8586-19620e9913af'
WHERE
	exercise_id IS NULL;

-- COLLAPSE
ALTER TABLE FLOWITEM ALTER COLUMN exercise_id SET NOT NULL;

DELETE
FROM
    atomicexercise
WHERE
    ID = '51fe7b57-719f-4ab8-8586-19620e9913af'
    AND NOT EXISTS(
        SELECT
            *
        FROM
            flowitem
        WHERE
            exercise_id = '51fe7b57-719f-4ab8-8586-19620e9913af'
    );

DELETE
FROM
    exercise
WHERE
    ID = '51fe7b57-719f-4ab8-8586-19620e9913af'
    AND NOT EXISTS(
        SELECT
            *
        FROM
            flowitem
        WHERE
            exercise_id = '51fe7b57-719f-4ab8-8586-19620e9913af'
    );

DELETE
FROM
    entity
WHERE
    ID = '51fe7b57-719f-4ab8-8586-19620e9913af'
    AND NOT EXISTS(
        SELECT
            *
        FROM
            flowitem
        WHERE
            exercise_id = '51fe7b57-719f-4ab8-8586-19620e9913af'
    );