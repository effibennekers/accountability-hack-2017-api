package com.theaccountant.accountabilityhack.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class SchoolEntry {

    private String brin;

    private Integer bevoegdGezag;

    private String name;

    private Address address;

    private Coordinate geo;

}
