package com.theaccountant.accountabilityhack.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public final class Address {
    private String streetname;
    private int streetNr;
    private String streetNrExt;
    private String zipcode;
}
