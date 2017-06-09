package com.theaccountant.accountabilityhack.controllers;

import com.theaccountant.accountabilityhack.data.Address;
import com.theaccountant.accountabilityhack.data.Coordinate;
import com.theaccountant.accountabilityhack.data.Ratings;
import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class SchoolController {

    private final DatabaseService databaseService;

    @Autowired
    public SchoolController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @RequestMapping("/schools")
    @ResponseBody
    List<SchoolEntry> schools(@RequestParam("start") Integer start, @RequestParam("size") Integer size) {
        final List<SchoolEntry> schools = databaseService.getAll(SchoolEntry.class, start, size);
        enrichWithRatings(schools);
        return schools;
    }

    private void enrichWithRatings(final List<SchoolEntry> schools) {
        for (final SchoolEntry entry : schools) {
            final BigDecimal leerlingen = new BigDecimal(entry.getTotaalAantalLeerlingen());
            final BigDecimal fteDirectie = entry.getFteDirectie();
            final BigDecimal fteLeerkrachten = entry.getFteLeerkrachten();
            final BigDecimal bekostigingPersoneel = entry.getBekostigingPersoneel();
            final BigDecimal klasgrootte = fteLeerkrachten.intValue() == 0 ? new BigDecimal(0) : leerlingen.divide(fteLeerkrachten);
            final BigDecimal totalIncome = entry.getBekostigingDirectie().add(entry.getBekostigingPersoneel()).add(entry.getBekostigingPersoneelOverig());
            final Ratings ratings = new Ratings();
            ratings.setClassSize(klasgrootte.doubleValue());
            ratings.setIncomePerStudent(totalIncome.doubleValue());
            ratings.setNonPersonelCostsPerStudent(leerlingen.intValue() == 0 ? 0d : entry.getTotalMaterialInstantHolding().divide(leerlingen).doubleValue());
            ratings.setFteBoardPerFteTeacher(fteLeerkrachten.intValue() == 0 ? 0d : fteDirectie.divide(fteLeerkrachten).doubleValue());
            ratings.setCostsBoardPerCostsPersonel(bekostigingPersoneel.intValue() == 0 ? 0d : entry.getBekostigingDirectie().divide(bekostigingPersoneel).doubleValue());
            ratings.setCitoPerClassSize(klasgrootte.intValue() == 0 ? 0d : entry.getCetAverage().divide(klasgrootte).doubleValue());
            entry.setRatings(ratings);
        }
    }

    @RequestMapping("/school")
    @ResponseBody
    SchoolEntry school() {
        return SchoolEntry
                .builder()
                .name("ARHC het Gooi")
                .address(Address.builder()
                        .streetname("Eemnesserweg")
                        .streetNr("292")
                        .zipcode("1223GK")
                        .city("Hilversum").build())
                .geo(Coordinate.builder()
                        .latitude(123.4).longitude(567.8).build())
                .build();
    }

}