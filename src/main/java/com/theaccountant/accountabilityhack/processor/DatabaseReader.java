package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;
import com.theaccountant.accountabilityhack.service.DatabaseService;

import java.io.IOException;
import java.util.List;

public final class DatabaseReader implements Processor {

    @Override
    public void process(final SchoolRegistry registry) throws IOException {
        final DatabaseService databaseService = new DatabaseService();
        final List<SchoolEntry> all = databaseService.getAll(SchoolEntry.class, 0, 100);
        registry.addAllSchools(all);
        System.out.println("Loaded all records from database: " + registry.size());
    }
}
