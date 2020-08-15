package com.ivan.MoviesRDFTransformer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CastMember implements Member {

    private String url;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("order")
    private Integer order;
    @JsonProperty("character")
    private String character;
    @JsonProperty("credit_id")
    private String credit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.url = "member" + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCharacter() {
        return character;
    }

    public void setcCharacter(String character) {
        this.character = character;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = "creditcast" + credit;
    }
}