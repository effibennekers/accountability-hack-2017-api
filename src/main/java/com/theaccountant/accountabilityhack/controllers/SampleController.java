package com.theaccountant.accountabilityhack.controllers;

import com.theaccountant.accountabilityhack.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

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
    String home() {
        return "Hello World!";
    }

}