package com.ivan.MoviesRDFTransformer;

import java.io.IOException;

import com.ivan.MoviesRDFTransformer.services.RDFTransformer;

public class App {
    public static void main(String[] args) throws IOException {

        RDFTransformer rdf = new RDFTransformer();
        // rdf.writeGenresToModel();
        // rdf.writeProductionCountriesToModel();
        // rdf.writeProductionCompaniesToModel();
        rdf.writeDepartmentsToModel();
        rdf.writeMembersToModel();
        rdf.writeCastMembersToModel();
        rdf.writeCrewMembersToModel();
        rdf.save();
    }

}
