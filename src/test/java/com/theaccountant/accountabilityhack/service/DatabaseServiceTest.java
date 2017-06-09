package com.theaccountant.accountabilityhack.service;

import com.theaccountant.accountabilityhack.data.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * TODO: Useful documentation
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseServiceTest {

    @Autowired
    private DatabaseService databaseService;

    @Test
    public void test() {
        Address address = Address.builder().city("HAHA").build();
        databaseService.save(address);

        List<Address> all = databaseService.getAll(Address.class, 0, 100);
        assertFalse(all.isEmpty());
    }
}