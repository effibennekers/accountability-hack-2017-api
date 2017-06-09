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
//        new CoordinateProcessor().process(registry);

        // TODO: save to database
    }
}
