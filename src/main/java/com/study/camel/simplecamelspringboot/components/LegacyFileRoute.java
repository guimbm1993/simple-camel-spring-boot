package com.study.camel.simplecamelspringboot.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("file:src/data/input?fileName=inputFile.txt")
        from("file:src/dataa/input?fileName=inputFile.txt")
                .id("legacyFileTransferRoute")
                .log(LoggingLevel.INFO,"moving the file")
                .to("file:src/data/output?fileName=output.txt");
    }
}
