package com.ivan.MoviesRDFTransformer.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class RDFTransformer {

    private final String rdfPath = "C:\\Users\\Duck\\Desktop\\movies.ttl";

    private final String wbs = "http://prefix.com/#";
    private final String dbpedia = "http://dbpedia.org/ontology/";
    private final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";

    private final Resource genreResource;
    private final Resource productionCompanyResource;
    private final Resource productionCountryResource;
    private final Resource filmResource;
    private final Resource personResource;
    private final Resource departmentResource;
    private final Resource crewResource;
    private final Resource castResource;

    private final Property isA;
    private final Property genreProp;
    private final Property keywordProp;
    private final Property labelProp;
    private final Property producedByProp;
    private final Property producedInProp;
    private final Property budgetProp;
    private final Property homepageProp;
    private final Property languageProp;
    private final Property overviewProp;
    private final Property taglineProp;
    private final Property releaseProp;
    private final Property popularityProp;
    private final Property revenueProp;
    private final Property runtimeProp;
    private final Property idProp;
    private final Property subclassProp;

    private final Property hasCrewProp;
    private final Property hasCastProp;

    private final Property hasOrderProp;
    private final Property hasCharacterProp;

    private final Property inDepartmentProp;
    private final Property inJobProp;

    private final Property hasPersonProp;

    private Model model;

    public RDFTransformer() throws JsonParseException, JsonMappingException, IOException {

        model = ModelFactory.createDefaultModel();
        model.setNsPrefix("dbpedia", dbpedia);
        model.setNsPrefix("wbs", wbs);
        model.setNsPrefix("rdfs", rdfs);

        subclassProp = model.createProperty(rdfs, "subClassOf");

        genreResource = model.createResource(dbpedia + "Genre");
        filmResource = model.createResource(dbpedia + "Film");
        personResource = model.createResource(dbpedia + "Person");
        departmentResource = model.createResource(dbpedia + "Department");
        productionCompanyResource = model.createResource(dbpedia + "Company");
        productionCountryResource = model.createResource(dbpedia + "Country");
        crewResource = model.createResource(wbs + "CrewMember");
        castResource = model.createResource(wbs + "CastMember");

        isA = model.createProperty(rdf + "type");
        genreProp = model.createProperty(dbpedia + "genre");
        keywordProp = model.createProperty(wbs + "keyword");
        labelProp = model.createProperty(rdfs + "label");
        producedByProp = model.createProperty(dbpedia + "producedBy");
        producedInProp = model.createProperty(wbs + "producedIn");
        budgetProp = model.createProperty(dbpedia + "budget");
        homepageProp = model.createProperty(dbpedia + "homepage");
        languageProp = model.createProperty(dbpedia, "originalLanguage");
        overviewProp = model.createProperty(rdfs, "comment");
        taglineProp = model.createProperty(dbpedia, "tagline");
        releaseProp = model.createProperty(dbpedia, "releaseDate");
        popularityProp = model.createProperty(dbpedia, "popularity");
        runtimeProp = model.createProperty(dbpedia, "runtime");
        revenueProp = model.createProperty(dbpedia, "revenue");
        idProp = model.createProperty(wbs, "id");

        hasCrewProp = model.createProperty(wbs, "hasCrew");
        hasCastProp = model.createProperty(wbs, "hasCast");

        inDepartmentProp = model.createProperty(wbs, "inDepartment");
        inJobProp = model.createProperty(wbs, "inJob");

        hasOrderProp = model.createProperty(wbs, "hasOrder");
        hasCharacterProp = model.createProperty(wbs, "hasCharacter");
        hasPersonProp = model.createProperty(wbs, "hasPerson");
    }

    public void writeGenresToModel() {

        JSONService.getAllGenres().stream().forEach(e -> model.createResource(wbs + cleanName(e.getName()))
                .addProperty(isA, genreResource).addLiteral(labelProp, e.getName()));

    }

    public void writeProductionCountriesToModel() {

        JSONService.getAllProductionContries().stream().forEach(e -> model.createResource(wbs + cleanName(e.getName()))
                .addProperty(isA, productionCountryResource).addLiteral(labelProp, e.getName()));

    }

    public void writeProductionCompaniesToModel() {

        JSONService.getAllProductionCompanies().stream().forEach(e -> model.createResource(wbs + cleanName(e.getName()))
                .addProperty(isA, productionCompanyResource).addLiteral(labelProp, e.getName()));

    }

    public void writeMembersToModel() {

        JSONService.getAllMembers().stream().distinct().sorted(Comparator.comparing(e -> e.getId()))
                .forEach(e -> model.createResource(wbs + cleanName(e.getId().toString()))
                        .addProperty(isA, personResource).addLiteral(labelProp, e.getName())
                        .addLiteral(idProp, e.getId()));

    }

    public void writeCastMembersToModel() {

        JSONService.getAllCastMembers().stream().distinct()
                .forEach(e -> model.createResource(wbs + cleanName(e.getCharacter() + "_" + e.getName()))
                        .addProperty(isA, castResource)
                        .addProperty(hasPersonProp, model.createResource(wbs + cleanName(e.getId().toString())))
                        .addLiteral(hasOrderProp, e.getOrder()).addLiteral(hasCharacterProp, e.getCharacter()));

    }

    public void writeCrewMembersToModel() {

        JSONService.getAllCrewMembers().stream().distinct()
                .forEach(e -> model.createResource(wbs + cleanName(e.getJob() + "_" + e.getName()))
                        .addProperty(isA, crewResource)
                        .addProperty(hasPersonProp, model.createResource(wbs + cleanName(e.getId().toString())))
                        .addLiteral(inJobProp, e.getJob()).addLiteral(inDepartmentProp, e.getDepartment()));

    }

    public void writeDepartmentsToModel() {

        JSONService.getAllCrewMembers().stream().forEach(e -> model.createResource(wbs + cleanName(e.getDepartment()))
                .addProperty(isA, departmentResource).addLiteral(labelProp, e.getDepartment()));

    }

    public void writeMoviesToModel() {
        JSONService.getAllMovies().stream().forEach(e -> {
            Resource temp;

            temp = model.createResource(wbs + e.getId().toString()).addProperty(isA, filmResource)
                    .addLiteral(idProp, e.getId()).addLiteral(labelProp, e.getTitle())
                    .addLiteral(budgetProp, e.getBudget()).addLiteral(homepageProp, e.getHomepage())
                    .addLiteral(languageProp, e.getOriginalLanguage()).addLiteral(overviewProp, e.getOverview())
                    .addLiteral(taglineProp, e.getTagline()).addLiteral(popularityProp, e.getPopularity())
                    .addLiteral(revenueProp, e.getRevenue());

            if (e.getReleaseDate() != null)
                temp.addLiteral(releaseProp, e.getReleaseDate());
            if (e.getRuntime() != null)
                temp.addLiteral(runtimeProp, e.getRuntime());

            e.getGenres().stream()
                    .forEach(ee -> temp.addProperty(genreProp, model.createResource(wbs + cleanName(ee.getName()))));

            e.getKeywords().stream().forEach(ee -> temp.addProperty(keywordProp, ee.getName()));

            e.getProductionCompanies().stream().forEach(
                    ee -> temp.addProperty(producedByProp, model.createResource(wbs + cleanName(ee.getName()))));

            e.getProductionCountries().stream().forEach(
                    ee -> temp.addProperty(producedInProp, model.createResource(wbs + cleanName(ee.getName()))));
        });
    }

    private String cleanName(String name) {

        try {
            return URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return name;
        // return name.replace(" ", "_").replace("/", "").replace("-", "").replace(",",
        // "_").replace(".", "_")
        // .replace("'", "").replace("\"", "").replace("&", "").replace("*",
        // "").replace("$", "").replace("#", "");
    }

    public void print() {
        model.write(System.out, "TURTLE");

    }

    public void save() {
        FileOutputStream f;
        try {
            f = new FileOutputStream(new File(rdfPath));
            model.write(f, "TURTLE");
            f.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}