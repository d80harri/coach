
CREATE
    TABLE
        FlowItem(
            id VARCHAR(36) NOT NULL,
	    flow_id VARCHAR(36) NOT NULL 
        );

ALTER TABLE
    FlowItem ADD PRIMARY KEY(id);

ALTER TABLE
    FlowItem ADD FOREIGN KEY(id) REFERENCES Entity(id);

ALTER TABLE
    FlowItem ADD FOREIGN KEY(flow_id) REFERENCES Flow(id);
