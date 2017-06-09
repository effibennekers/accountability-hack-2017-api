package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;
import java.math.BigDecimal;

public final class CitoProcessor implements Processor {

    public void process(final SchoolRegistry registry) throws IOException {
        final CsvReader reader = new CsvReader("05.-gemiddelde-eindscores-bo-sbo-2015-2016.csv");
        while (reader.next()) {
            final String brin = reader.getString("BRIN_NUMMER");
            final SchoolEntry entry;
            if (registry.isSchoolPresent(brin)) {
                entry = registry.getSchoolByBrin(brin);
            } else {
                entry = SchoolEntry.builder().brin(brin).build();
                registry.addSchool(entry);
            }
            entry.setSoortPo(reader.getString("SOORT_PO"));
            entry.setDenomination(reader.getString("DENOMINATIE_VESTIGING"));
            entry.setCetAverage(reader.getBigDecimal("CET_GEM"));
            entry.setIepAverage(reader.getBigDecimal("IEP_GEM"));
        }
        System.out.println("Done processing CITO constructions: " + registry.size());
    }
}
