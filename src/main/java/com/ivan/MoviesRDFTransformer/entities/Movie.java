package com.ivan.MoviesRDFTransformer.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    @JsonProperty("id")
    @JsonAlias("movie_id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("budget")
    private Long budget;
    @JsonProperty("revenue")
    private Long revenue;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("tagline")
    private String tagline;
    @JsonProperty("popularity")
    private Float popularity;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("genres")
    private List<Category> genres;
    @JsonProperty("keywords")
    private List<Category> keywords;
    @JsonProperty("production_companies")
    private List<Category> productionCompanies;
    @JsonProperty("production_countries")
    private List<Category> productionCountries;

    @JsonProperty("crew")
    private List<CrewMember> crewMembers;
    @JsonProperty("cast")
    private List<CastMember> castMembers;

    private String url;

    public Movie() {

    }

    public Movie(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.url = "movie" + id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getGenres() {
        return genres;
    }

    public void setGenres(List<Category> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie [genres=" + genres + ", id=" + id + ", title=" + title + "]";
    }

    public List<Category> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Category> keywords) {
        this.keywords = keywords;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Date getReleaseDate() {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return d.parse(this.releaseDate);
        } catch (ParseException e) {

        }
        return null;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<Category> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<Category> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Category> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<Category> productionCountries) {
        this.productionCountries = productionCountries;
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

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Movie) obj).getId());
    }

    @Override
    public int hashCode() {
        if (this.id == null)
            id = -1L;
        return this.id.hashCode();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}