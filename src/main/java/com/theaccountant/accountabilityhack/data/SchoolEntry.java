package com.theaccountant.accountabilityhack.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public final class SchoolEntry {

    private String brin;

    private Integer bevoegdGezag;

    private String name;

    private Address address;

    private Coordinate geo;

}
