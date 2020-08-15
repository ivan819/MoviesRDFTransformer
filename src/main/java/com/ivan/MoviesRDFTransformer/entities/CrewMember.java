package com.ivan.MoviesRDFTransformer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrewMember implements Member {
    private String url;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("department")
    private String department;
    @JsonProperty("job")
    private String job;
    @JsonProperty("credit_id")
    private String credit;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.url = "member" + id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = "creditcrew" + credit;
    }
}
