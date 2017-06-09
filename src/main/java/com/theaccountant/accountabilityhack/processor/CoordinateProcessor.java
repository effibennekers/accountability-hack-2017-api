package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.Address;
import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;
import com.theaccountant.accountabilityhack.service.CoordinateService;

import java.io.IOException;

public final class CoordinateProcessor implements Processor {

    private static final int TICK = 10;
    private static final int MAX = 10000;

    @Override
    public void process(final SchoolRegistry registry) throws IOException {
        final CoordinateService coordinateService = new CoordinateService();
        int count = 0;
        final int totalCount = registry.size();
        for (final SchoolEntry entry : registry) {
            if ((count % TICK) == 0) {
                System.out.println(String.format("Processing coordinates: %s/%s", count, totalCount));
            }
            count++;
            if (count >= MAX) {
                break;
            }
            if (entry.getGeo() != null) {
                continue;
            }
            final Address address = entry.getAddress();
            if (address == null) {
                continue;
            }
            try {
                entry.setGeo(coordinateService.queryCoordinates(address));
            } catch(Exception e) {
                System.err.println("Failed to retrieve coordinates, but continuing. Cause: " + e.getMessage());
            }
        }
        System.out.println(String.format("Processing coordinates: %s/%s Done", count, totalCount));
    }
}
