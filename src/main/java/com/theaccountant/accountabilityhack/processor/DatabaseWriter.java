package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.data.SchoolRegistry;
import com.theaccountant.accountabilityhack.service.DatabaseService;

import java.io.IOException;

public final class DatabaseWriter implements Processor {

    @Override
    public final void process(final SchoolRegistry registry) throws IOException {
        final DatabaseService databaseService = new DatabaseService();
        for (final SchoolEntry school : registry) {
            databaseService.save(school);
        }
    }
}
