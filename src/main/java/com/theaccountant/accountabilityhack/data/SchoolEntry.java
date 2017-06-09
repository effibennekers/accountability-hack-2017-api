package com.theaccountant.accountabilityhack.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public final class SchoolEntry {

    public SchoolEntry(final String brin) {
        this.brin = brin;
    }

    private String brin;

    private Integer bevoegdGezag;

    private String name;

    private Address address;

    private Coordinate geo;

}
