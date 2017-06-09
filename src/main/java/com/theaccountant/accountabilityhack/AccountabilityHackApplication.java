package com.theaccountant.accountabilityhack;

import com.theaccountant.accountabilityhack.data.SchoolEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

import static com.theaccountant.accountabilityhack.processDataSets.ProcessSchoolLocations.processInputFile;

@SpringBootApplication
public class AccountabilityHackApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AccountabilityHackApplication.class, args);

		List<SchoolEntry> schools = processInputFile();
		System.out.print(schools);

		System.out.println("Hello");
	}
}
