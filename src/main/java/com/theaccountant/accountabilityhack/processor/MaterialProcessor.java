package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;
import java.math.BigDecimal;

public final class MaterialProcessor implements Processor {

    public void process(final SchoolRegistry registry) throws IOException {
        final CsvReader reader = new CsvReader("01.-materiele-instandhouding-bo-2015.csv");
        while (reader.next()) {
            final String brin = reader.getString("BRIN_NUMMER");
            final SchoolEntry entry;
            if (registry.isSchoolPresent(brin)) {
                entry = registry.getSchoolByBrin(brin);
            } else {
                entry = SchoolEntry.builder().brin(brin).build();
                registry.addSchool(entry);
            }
            entry.setTotalMaterialInstantHolding(reader.getBigDecimal("TOTAAL_MI"));
        }
        System.out.println("Done processing materiele instanthouding: " + registry.size());
    }
}
