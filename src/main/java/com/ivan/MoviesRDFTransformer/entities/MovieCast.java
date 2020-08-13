package com.ivan.MoviesRDFTransformer.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieCast {
    @JsonProperty("movie_id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("crew")
    private List<CrewMember> crewMembers;
    @JsonProperty("cast")
    private List<CastMember> castMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public List<CastMember> getCastMembers() {
        return castMembers;
    }

    public void setCastMembers(List<CastMember> castMembers) {
        this.castMembers = castMembers;
    }
}
