package com.ivan.MoviesRDFTransformer.services;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.MoviesRDFTransformer.entities.CastMember;
import com.ivan.MoviesRDFTransformer.entities.Category;
import com.ivan.MoviesRDFTransformer.entities.CrewMember;
import com.ivan.MoviesRDFTransformer.entities.Member;
import com.ivan.MoviesRDFTransformer.entities.Movie;
import com.ivan.MoviesRDFTransformer.entities.MovieCast;

public class JSONService {
    private static String jsonPathMovies = "C:\\Users\\Duck\\Desktop\\movies.json";
    private static String jsonPathCredits = "C:\\Users\\Duck\\Desktop\\credits.json";

    private static ObjectMapper mapper;
    private static List<Movie> movies;
    private static List<MovieCast> movieCasts;

    static {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            movies = mapper.readValue(Paths.get(jsonPathMovies).toFile(), new TypeReference<List<Movie>>() {
            });
            movieCasts = mapper.readValue(Paths.get(jsonPathCredits).toFile(), new TypeReference<List<MovieCast>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> getAllMovies() {
        movies.stream().forEach(e -> {
            MovieCast mc = movieCasts.stream().filter(ee -> ee.getId().equals(e.getId())).findAny().orElse(null);
            e.setCastMembers(mc.getCastMembers());
            e.setCrewMembers(mc.getCrewMembers());
        });

        return movies.stream().distinct().collect(Collectors.toList());
    }

    public static List<Category> getAllGenres() {
        return movies.stream().flatMap(e -> e.getGenres().stream()).distinct().collect(Collectors.toList());
    }

    public static List<Category> getAllProductionCompanies() {
        return movies.stream().flatMap(e -> e.getProductionCompanies().stream()).distinct()
                .collect(Collectors.toList());
    }

    public static List<Category> getAllProductionContries() {
        return movies.stream().flatMap(e -> e.getProductionCountries().stream()).distinct()
                .collect(Collectors.toList());
    }

    public static List<CastMember> getAllCastMembers() {
        return movieCasts.stream().flatMap(e -> e.getCastMembers().stream()).distinct().collect(Collectors.toList());
    }

    public static List<CrewMember> getAllCrewMembers() {
        return movieCasts.stream().flatMap(e -> e.getCrewMembers().stream()).distinct().collect(Collectors.toList());
    }

    public static List<Member> getAllMembers() {

        List<Member> members = new ArrayList<Member>();
        members.addAll(getAllCastMembers());
        members.addAll(getAllCrewMembers());
        return members;
    }

}