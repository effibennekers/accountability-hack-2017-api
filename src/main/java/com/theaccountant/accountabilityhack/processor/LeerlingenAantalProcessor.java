package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;

public final class LeerlingenAantalProcessor implements Processor {

    public void process(final SchoolRegistry registry) throws IOException {
        final CsvReader reader = new CsvReader("01.-leerlingen-bo-gewicht,-leeftijd-2016-2017.csv");
        while (reader.next()) {
            final String brin = reader.get("BRIN_NUMMER");
            final SchoolEntry entry;
            if (registry.isSchoolPresent(brin)) {
                entry = registry.getSchoolByBrin(brin);
            } else {
                entry = new SchoolEntry(brin);
                registry.addSchool(entry);
            }

            entry.setTotaalAantalLeerlingen(entry.getTotaalAantalLeerlingen() + reader.getInt("TOTAAL"));
        }
    }
}