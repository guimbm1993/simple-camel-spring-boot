package com.study.camel.simplecamelspringboot.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileCSVSplitterRoute extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure() throws Exception {
        from("file:src/datac/input?fileName=inputFile.csv")
                .id("legacyFileTransferWithSplitterRoute")
                .split(body().tokenize("\n", 1, true))
                .process(exchange -> {
                    String fileData = exchange.getIn().getBody(String.class);
                    logger.info(fileData);
                    //removing some characters
//                    fileData = fileData.replaceAll("i","1");
//                    exchange.getIn().setBody(fileData);
                })
                .log(LoggingLevel.INFO, "moving the file")
                .to("file:src/datac/output?fileName=output.csv&fileExist=append&appendChars=\\n")
                .end(); //to indicate what block is executed in split
    }
}
