package com.ivan.MoviesRDFTransformer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    @JsonProperty("name")
    private String name;

    public Category() {
    }

    public Category(String name) {

        this.name = name;
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
        return this.name.equals(((Category) obj).getName());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}