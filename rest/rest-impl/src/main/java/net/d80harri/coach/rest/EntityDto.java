package net.d80harri.coach.rest;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "entities")
public class EntityDto {
    private String id;
	
    @JsonApiId
    public String getId() {
		return id;
	}
    public void setId(String id) {
		this.id = id;
	}
}
