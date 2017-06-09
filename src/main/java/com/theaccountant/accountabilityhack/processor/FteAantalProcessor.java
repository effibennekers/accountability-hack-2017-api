package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;

public final class FteAantalProcessor implements Processor {

    public void process(final SchoolRegistry registry) throws IOException {
        final CsvReader reader = new CsvReader("02.-po-fte-owtype-bestuur-brin-functie.csv");
        while (reader.next()) {
            final String brin = reader.get("BRIN_NUMMER");
            final SchoolEntry entry;
            if (registry.isSchoolPresent(brin)) {
                entry = registry.getSchoolByBrin(brin);
            } else {
                entry = new SchoolEntry(brin);
                registry.addSchool(entry);
            }
            final String functie = reader.get("FUNCTIEGROEP");
            final String fte = reader.get("FTE'S 2016");
            switch (functie) {
            case "Directie":
                entry.setFteDirectie(fte);
                break;
            case "Onderwijsgevend personeel":
                entry.setFteLeerkrachten(fte);
                break;
            case "Onderwijsondersteunend personeel (OOP/OBP)":
                entry.setFteOndersteunend(fte);
                break;
            default:
                System.err.println("Unknown functie: " + functie);
                break;
            }
        }
    }
}
