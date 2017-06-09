package com.theaccountant.accountabilityhack.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SchoolEntry {

    private String name;

    private Address address;

    private Coordinate geo;

}
