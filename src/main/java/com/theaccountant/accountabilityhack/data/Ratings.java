package com.theaccountant.accountabilityhack.data;

import lombok.Data;

@Data
public final class Ratings {

    private double klasgrootte;
    private double incomePerLeerling;
    private double nonPersonelCostsPerLeerling;
    private double fteDirectiePerFteLeerkracht;
    private double kostenDirectiePerKostenPersoneel;
    private double citoPerKlasgrootte;
}
