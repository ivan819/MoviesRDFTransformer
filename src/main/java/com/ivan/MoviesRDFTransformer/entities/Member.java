package com.ivan.MoviesRDFTransformer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Member) obj).getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}