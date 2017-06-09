package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;
import java.math.BigDecimal;

public final class PersoneelsBekostigingProcessor implements Processor {

    public void process(final SchoolRegistry registry) throws IOException {

        final CsvReader reader = new CsvReader("03.-personele-bekostiging-bo-2015-2016.csv");
        while (reader.next()) {
            final String brin = reader.get("BRIN_NUMMER");
            final SchoolEntry entry;
            if (registry.isSchoolPresent(brin)) {
                entry = registry.getSchoolByBrin(brin);
            } else {
                entry = SchoolEntry.builder().brin(brin).build();
                registry.addSchool(entry);
            }

            final BigDecimal onderbouw = reader.getMoney("ONDERBOUW");
            final BigDecimal bovenbouw = reader.getMoney("BOVENBOUW");
            final BigDecimal directie = reader.getMoney("DIRECTIETOESLAG");
            final BigDecimal totaal = reader.getMoney("TOTAAL");

            entry.setBekostigingPersoneel(onderbouw.add(bovenbouw));
            entry.setBekostigingDirectie(directie);
            entry.setBekostigingOverig(totaal.subtract(directie).subtract(onderbouw).subtract(bovenbouw));
        }
    }
}
