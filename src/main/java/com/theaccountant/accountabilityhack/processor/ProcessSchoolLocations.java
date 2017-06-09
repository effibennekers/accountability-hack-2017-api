package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.Address;
import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ProcessSchoolLocations implements Processor {

    private static final java.lang.String COMMA = ";";

    @Override
    public void process(final SchoolRegistry registry) throws IOException {
        final CsvReader reader = new CsvReader("01.-hoofdvestigingen-basisonderwijs-2017.csv");

        while (reader.next()) {
            final String brin = reader.getString("BRIN NUMMER");
            final SchoolEntry entry;
            if (registry.isSchoolPresent(brin)) {
                entry = registry.getSchoolByBrin(brin);
            } else {
                entry = SchoolEntry.builder().brin(brin).build();
                registry.addSchool(entry);
            }

            entry.setAddress(Address.builder()
                    .streetname(reader.getString("STRAATNAAM"))
                    .streetNr(reader.getString("HUISNUMMER-TOEVOEGING"))
                    .zipcode(reader.getString("POSTCODE"))
                    .city(reader.getString("PLAATSNAAM"))
                    .build());

            entry.setBevoegdGezag(reader.getInt("BEVOEGD GEZAG NUMMER"));
            entry.setName(reader.getString("INSTELLINGSNAAM"));
        }
        System.out.println("Done processing addresses: " + registry.size());
    }
}
