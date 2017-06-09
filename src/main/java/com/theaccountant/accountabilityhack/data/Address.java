package com.theaccountant.accountabilityhack.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public final class Address {
    private String streetname;
    private String streetNr;
    private String zipcode;
    private String city;
}
