package com.ivan.MoviesRDFTransformer.entities;

import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    static final AtomicInteger count = new AtomicInteger(0);
    @JsonProperty("name")
    private String name;
    private String url;

    public Category() {
        this.url = "prod" + count.incrementAndGet();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}