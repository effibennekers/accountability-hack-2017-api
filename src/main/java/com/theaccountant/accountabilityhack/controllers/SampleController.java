package com.theaccountant.accountabilityhack.controllers;

import com.theaccountant.accountabilityhack.data.Address;
import com.theaccountant.accountabilityhack.data.Coordinate;
import com.theaccountant.accountabilityhack.data.SchoolEntry;
import com.theaccountant.accountabilityhack.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class SampleController {

    private final DatabaseService databaseService;

    @Autowired
    public SampleController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @RequestMapping("/")
    @ResponseBody
    List<SchoolEntry> home() {
        SchoolEntry schoolEntry = SchoolEntry.builder().brin("haha").address(Address.builder().build()).geo(Coordinate.builder().build()).build();
        databaseService.save(schoolEntry);
        return databaseService.getAll(SchoolEntry.class);
    }

}