package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;

public final class LeerlingenAantalProcessor implements Processor {

    public void process(final SchoolRegistry registry) throws IOException {
        final CsvReader reader = new CsvReader("01.-leerlingen-bo-gewicht,-leeftijd-2016-2017.csv");
        while (reader.next()) {
            final String brin = reader.getString("BRIN_NUMMER");
            final SchoolEntry entry;
            if (registry.isSchoolPresent(brin)) {
                entry = registry.getSchoolByBrin(brin);
            } else {
                entry = SchoolEntry.builder().brin(brin).build();
                registry.addSchool(entry);
            }

            final int oldValue = entry.getTotaalAantalLeerlingen() == null ? 0 : entry.getTotaalAantalLeerlingen();
            entry.setTotaalAantalLeerlingen(oldValue + reader.getInt("TOTAAL"));
        }
        System.out.println("Done processing aantal leerlingen: " + registry.size());
    }
}
