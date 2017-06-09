package com.theaccountant.accountabilityhack.controllers;

import com.theaccountant.accountabilityhack.data.Address;
import com.theaccountant.accountabilityhack.data.Coordinate;
import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    List<SchoolEntry> schools() {
        SchoolEntry schoolEntry = SchoolEntry.builder().brin("haha").address(Address.builder().build()).geo(Coordinate.builder().build()).build();
        databaseService.save(schoolEntry);
        return databaseService.getAll(SchoolEntry.class);
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