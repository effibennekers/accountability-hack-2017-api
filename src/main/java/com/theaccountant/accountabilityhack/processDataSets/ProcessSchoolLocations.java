package com.theaccountant.accountabilityhack.processDataSets;

import com.theaccountant.accountabilityhack.data.SchoolEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hanmarkslag on 09/06/2017.
 */
public class ProcessSchoolLocations {


    private static final java.lang.String COMMA = ",";

    public static List<SchoolEntry> processInputFile() throws IOException {
        List<SchoolEntry> inputList = new ArrayList<SchoolEntry>();
        File inputF = new File("../../../../resources/hoofdvestigingen-basisonderwijs.csv");
        InputStream inputFS = new FileInputStream(inputF);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

        // skip the header of the csv
        inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
        br.close();
        return inputList;
    }

    private static Function<String, SchoolEntry> mapToItem = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        SchoolEntry item = SchoolEntry.builder().build();
        item.setBrin(p[2]);
        return item;
    };
}