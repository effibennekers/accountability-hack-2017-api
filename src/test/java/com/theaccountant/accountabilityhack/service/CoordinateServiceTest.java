package com.theaccountant.accountabilityhack.service;

import com.theaccountant.accountabilityhack.data.Address;
import org.junit.Test;

public class CoordinateServiceTest {

    @Test
    public void testCoordinates() {
        final CoordinateService service = new CoordinateService();
        final Address address = Address.builder().streetname("Bijlmerdreef").streetNr("24").zipcode("1102 CT").city("Amsterdam-Zuidoost").build();
        System.out.println(service.queryCoordinates(address));
    }

}
