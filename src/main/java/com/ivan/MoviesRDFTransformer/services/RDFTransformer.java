package com.ivan.MoviesRDFTransformer.services;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.MoviesRDFTransformer.entities.Category;
import com.ivan.MoviesRDFTransformer.entities.Movie;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class RDFTransformer {

    private final String filePath = "C:\\Users\\Duck\\Desktop\\movies.json";
    private final String wbs = "http://prefix.com/#";
    private final String dbpedia = "http://dbpedia.org/ontology/";
    private final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";

    Property isA;
    Property genreProp;

    Resource genreResource;

    Resource productionCompanyResource;
    Resource productionCountryResource;
    Resource filmResource;
    Property keywordProp;
    Property labelProp;

    private Model model;
    private ObjectMapper mapper;
    private List<Movie> movies;

    public RDFTransformer() throws JsonParseException, JsonMappingException, IOException {

        model = ModelFactory.createDefaultModel();
        model.setNsPrefix("dbpedia", dbpedia);
        model.setNsPrefix("wbs", wbs);
        model.setNsPrefix("rdfs", rdfs);

        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        movies = mapper.readValue(Paths.get(filePath).toFile(), new TypeReference<List<Movie>>() {
        });

        isA = model.createProperty(rdf + "type");
        genreProp = model.createProperty(dbpedia + "genre");
        keywordProp = model.createProperty(wbs + "keyword");
        genreResource = model.createResource(dbpedia + "Genre");
        filmResource = model.createResource(dbpedia + "Film");
        productionCompanyResource = model.createResource(dbpedia + "Company");
        productionCountryResource = model.createResource(dbpedia + "Country");
        labelProp = model.createProperty(rdfs + "label");
    }

    private List<Category> getAllGenres() {
        return movies.stream().flatMap(e -> e.getGenres().stream()).distinct().collect(Collectors.toList());
    }

    private List<Category> getAllProductionCompanies() {
        return movies.stream().flatMap(e -> e.getProductionCompanies().stream()).distinct()
                .collect(Collectors.toList());
    }

    private List<Category> getAllProductionContries() {
        return movies.stream().flatMap(e -> e.getProductionCountries().stream()).distinct()
                .collect(Collectors.toList());
    }

    public void writeGenresToModel() {

        getAllGenres().stream().forEach(e -> model.createResource(wbs + e.getName().replace(" ", "_"))
                .addProperty(isA, genreResource).addLiteral(labelProp, e.getName()));

    }

    public void writeProductionCountriesToModel() {

        getAllProductionContries().stream().forEach(e -> model.createResource(wbs + e.getName().replace(" ", "_"))
                .addProperty(isA, productionCountryResource).addLiteral(labelProp, e.getName()));

    }

    public void writeProductionCompaniesToModel() {

        getAllProductionCompanies().stream().forEach(e -> model.createResource(wbs + e.getName().replace(" ", "_"))
                .addProperty(isA, productionCompanyResource).addLiteral(labelProp, e.getName()));

    }

    public void writeMoviesToModel() {

        movies.stream().forEach(e -> {
            Resource temp = model.createResource(wbs + e.getTitle().replace(" ", "_")).addProperty(isA, filmResource);
            e.getGenres().stream().forEach(
                    ee -> temp.addProperty(genreProp, model.createResource(wbs + ee.getName().replace(" ", "_"))));
            e.getKeywords().stream().forEach(ee -> temp.addProperty(keywordProp, ee.getName()));
        });

        // Resource mmmm = model.createResource(wbs +
        // movies.get(0).getTitle()).addProperty(p, genreRes1).addProperty(p1,
        // mmm);

    }

    public void print() {
        model.write(System.out, "TURTLE");
    }
}