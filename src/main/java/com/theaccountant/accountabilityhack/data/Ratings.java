package com.theaccountant.accountabilityhack.data;

import lombok.Data;

@Data
public final class Ratings {

    private double classSize;
    private double incomePerStudent;
    private double nonPersonelCostsPerStudent;
    private double fteBoardPerFteTeacher;
    private double costsBoardPerCostsPersonel;
    private double citoPerClassSize;
}
