package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;
import com.theaccountant.accountabilityhack.service.DatabaseService;

import java.io.IOException;

public final class FileProcessor {

    public static void main(final String...args) throws IOException {
        final SchoolRegistry registry = new SchoolRegistry();

        new DatabaseReader().process(registry);
        new ProcessSchoolLocations().process(registry);
        new LeerlingenAantalProcessor().process(registry);
        new FteAantalProcessor().process(registry);
        new PersoneelsBekostigingProcessor().process(registry);
        new CitoProcessor().process(registry);
        new MaterialProcessor().process(registry);
//        new CoordinateProcessor().process(registry);
//        new DatabaseWriter().process(registry);

        System.out.println("Parsed " + registry.size() + " entries");
    }
}
