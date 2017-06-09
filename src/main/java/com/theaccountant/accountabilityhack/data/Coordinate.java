package com.theaccountant.accountabilityhack.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public final class Coordinate {
    private double latitude;
    private double longitude;
}
