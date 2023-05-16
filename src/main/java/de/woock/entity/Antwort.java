package de.woock.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Antwort (@JsonProperty("bonitaet") Bonitaet bonitaet,
		               @JsonProperty("name")     String name) implements Serializable {}
