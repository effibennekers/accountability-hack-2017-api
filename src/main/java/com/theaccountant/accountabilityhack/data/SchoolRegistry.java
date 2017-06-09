package com.theaccountant.accountabilityhack.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Registry of all school entries.
 */
public final class SchoolRegistry implements Iterable<SchoolEntry> {

    private final Map<String, SchoolEntry> map = new HashMap<>();

    public final SchoolEntry getSchoolByBrin(final String brin) {
        return map.get(brin);
    }

    public final boolean isSchoolPresent(final String brin) {
        return map.containsKey(brin);
    }

    public final void addAllSchools(final List<SchoolEntry> entries) {
        for (final SchoolEntry entry : entries) {
            addSchool(entry);
        }
    }

    public final void addSchool(final SchoolEntry school) {
        final String brin = school.getBrin();
        if (isSchoolPresent(brin)) {
            throw new IllegalStateException("School with brin " + brin + " already present!");
        }
        map.put(brin, school);
    }

    @Override
    public Iterator<SchoolEntry> iterator() {
        return new ArrayList<>(map.values()).iterator();
    }

    public final int size() {
        return map.size();
    }
}
