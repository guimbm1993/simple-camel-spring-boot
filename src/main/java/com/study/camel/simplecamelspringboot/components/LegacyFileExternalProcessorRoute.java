package com.study.camel.simplecamelspringboot.components;

import com.study.camel.simplecamelspringboot.beans.Client;
import com.study.camel.simplecamelspringboot.processors.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LegacyFileExternalProcessorRoute extends RouteBuilder {

    Logger logger = LoggerFactory.getLogger(getClass());
    BeanIODataFormat inboundDataFormat = new BeanIODataFormat("inboundMessageBeanIoMapping.xml","inboundMessageStream");

    @Override
    public void configure() throws Exception {
        from("file:src/data/input?fileName=inputFile.csv")
                .id("legacyFileTransferWithExternalProcessorRoute")
                .split(body().tokenize("\n", 1, true))
                .unmarshal(inboundDataFormat)
                .process(new InboundMessageProcessor())
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "moving the file")
                .to("file:src/datae/output?fileName=output.csv&fileExist=append&appendChars=\\n")
                .end(); //to indicate what block is executed in split
    }
}
