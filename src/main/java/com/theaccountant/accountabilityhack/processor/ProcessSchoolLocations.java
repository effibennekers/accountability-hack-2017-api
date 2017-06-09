package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.Address;
import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ProcessSchoolLocations implements Processor {

    private static final java.lang.String COMMA = ";";

    @Override
    public void process(final SchoolRegistry registry) throws IOException {
        for (final SchoolEntry entry : processInputFile()) {
            registry.addSchool(entry);
        }
    }

    public static List<SchoolEntry> processInputFile() throws IOException {
        List<SchoolEntry> inputList = new ArrayList<SchoolEntry>();
        InputStream inputFS = ProcessSchoolLocations.class.getClassLoader().getResourceAsStream("01.-hoofdvestigingen-basisonderwijs-2017.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

        // skip the header of the csv
        inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
        br.close();
        return inputList;
    }

    private static Function<String, SchoolEntry> mapToItem = (line) -> {

        String[] p = line.split(COMMA);// a CSV has comma separated lines

        // SchoolEntry data
        SchoolEntry item = SchoolEntry.builder().build();
        item.setBrin(p[2]);
        item.setBevoegdGezag(Integer.valueOf(p[1]));
        item.setName(p[3]);

        // School adres
        Address adres = new Address(p[7], p[4], p[5],  p[6]);
        item.setAddress(adres);

        return item;
    };
}