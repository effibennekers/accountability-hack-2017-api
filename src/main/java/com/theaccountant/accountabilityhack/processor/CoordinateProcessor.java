package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;
import com.theaccountant.accountabilityhack.service.CoordinateService;

import java.io.IOException;

public final class CoordinateProcessor implements Processor {

    @Override
    public void process(final SchoolRegistry registry) throws IOException {
        final CoordinateService coordinateService = new CoordinateService();
        for (final SchoolEntry entry : registry) {
            entry.setGeo(coordinateService.queryCoordinates(entry.getAddress()));
        }
    }
}
