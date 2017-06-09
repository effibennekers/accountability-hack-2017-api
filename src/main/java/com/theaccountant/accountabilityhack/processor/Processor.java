package com.theaccountant.accountabilityhack.processor;

import com.theaccountant.accountabilityhack.data.SchoolRegistry;

import java.io.IOException;

public interface Processor {

    void process(final SchoolRegistry registry) throws IOException;
}
