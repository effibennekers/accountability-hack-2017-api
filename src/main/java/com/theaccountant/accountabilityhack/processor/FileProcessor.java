package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;
import com.theaccountant.accountabilityhack.service.CoordinateService;

import java.io.IOException;

public final class FileProcessor {

    public static void main(final String...args) throws IOException {
        final SchoolRegistry registry = new SchoolRegistry();
        final CoordinateService coordinateService = new CoordinateService();
        for (final SchoolEntry entry : ProcessSchoolLocations.processInputFile()) {
            registry.addSchool(entry);
            entry.setGeo(coordinateService.queryCoordinates(entry.getAddress()));
        }

        // TODO: save to database
    }
}
