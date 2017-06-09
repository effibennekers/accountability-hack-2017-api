package com.theaccountant.accountabilityhack.processor;

import org.junit.Test;

import java.io.IOException;

public final class CsvReaderTest {

    @Test
    public void testReadFile() throws IOException {
        final CsvReader reader = new CsvReader("01.-leerlingen-po-soort-po,-cluster,-leeftijd-2015-2016.csv");
        reader.next();
        System.out.println(reader.get("BRIN_NUMMER"));
    }
}
