package com.ivan.MoviesRDFTransformer;

import java.io.IOException;

import com.ivan.MoviesRDFTransformer.services.RDFTransformer;

public class App {
    static long begin = System.currentTimeMillis();

    public static void main(String[] args) throws IOException {

        RDFTransformer rdf = new RDFTransformer();
        rdf.writeGenresToModel();
        printTime();

        rdf.writeProductionCountriesToModel();
        printTime();

        rdf.writeProductionCompaniesToModel();
        printTime();

        rdf.writeDepartmentsToModel();
        printTime();

        rdf.writeMembersToModel();
        printTime();

        rdf.writeCastMembersToModel();
        printTime();

        rdf.writeCrewMembersToModel();
        printTime();

        rdf.writeMoviesToModel();
        printTime();

        rdf.save();
        printTime();
    }

    public static void printTime() {
        System.out.println("step finished in " + (System.currentTimeMillis() - begin));
    }
}
