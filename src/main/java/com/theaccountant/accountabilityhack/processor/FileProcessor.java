package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;

public final class FileProcessor {

    public static void main(final String...args) throws IOException {
        final SchoolRegistry registry = new SchoolRegistry();

        // Deze moet eerst
        new ProcessSchoolLocations().process(registry);

        new LeerlingenAantalProcessor().process(registry);
        new FteAantalProcessor().process(registry);
        new PersoneelsBekostigingProcessor().process(registry);
//        new CoordinateProcessor().process(registry);

        System.out.println("Parsed " + registry.size() + " entries");

//        for (final SchoolEntry entry : registry) {
//            System.out.println(entry);
//        }
        // TODO: save to database
    }
}
