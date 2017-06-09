package com.theaccountant.accountabilityhack.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public final class Coordinate {
    private double longitude;
    private double latitude;
}
