package com.theaccountant.accountabilityhack.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class CsvReader {

    private final BufferedReader reader;

    private final Map<String, Integer> fieldIndices = new HashMap<>();

    private String[] currentRecord;

    public CsvReader(final String filename) throws IOException {
        final InputStream inputFS = CsvReader.class.getClassLoader().getResourceAsStream(filename);
        reader = new BufferedReader(new InputStreamReader(inputFS));
        final String[] fieldnames = reader.readLine().split(";");
        for (int i = 0; i < fieldnames.length; i++) {
            fieldIndices.put(fieldnames[i], i);
        }
    }

    /**
     * If returns false, no more records found.
     */
    public boolean next() throws IOException {
        final String line = reader.readLine();
        if (line == null) {
            currentRecord = null;
            return false;
        }
        currentRecord = line.split(";");
        return true;
    }

    /**
     * Get field from current record.
     */
    public String get(final String fieldname) {
        return currentRecord[fieldIndices.get(fieldname)];
    }
}
