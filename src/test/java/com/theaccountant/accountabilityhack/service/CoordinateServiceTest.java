package com.theaccountant.accountabilityhack.service;

import com.theaccountant.accountabilityhack.data.Address;
import org.junit.Test;

public class CoordinateServiceTest {

    @Test
    public void testCoordinates() {
        final CoordinateService service = new CoordinateService();
        final Address address = new Address("Bijlmerdreef", "24", "1102 CT", "Amsterdam-Zuidoost");
        System.out.println(service.queryCoordinates(address));
    }

}
