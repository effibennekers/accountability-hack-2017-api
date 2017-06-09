package com.theaccountant.accountabilityhack.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class CsvReader {

    private String filename;

    private final BufferedReader reader;

    private final Map<String, Integer> fieldIndices = new HashMap<>();

    private String[] currentRecord;

    public CsvReader(final String filename) throws IOException {
        this.filename = filename;
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
    public final String getString(final String fieldname) {
        if (!fieldIndices.containsKey(fieldname)) {
            throw new IllegalArgumentException(filename + " does not contain field " + fieldname);
        }
        return currentRecord[fieldIndices.get(fieldname)];
    }

    public final BigDecimal getBigDecimal(final String fieldname) {
        final String string = getString(fieldname);
        return new BigDecimal(string.replaceAll("\\.", "").replaceAll(",", "."));
    }

    public final int getInt(final String fieldname) {
        return Integer.parseInt(getString(fieldname));
    }
}
