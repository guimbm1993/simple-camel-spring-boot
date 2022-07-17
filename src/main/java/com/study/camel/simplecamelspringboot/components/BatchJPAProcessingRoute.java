package com.study.camel.simplecamelspringboot.components;

import com.study.camel.simplecamelspringboot.beans.Client;
import com.study.camel.simplecamelspringboot.processors.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BatchJPAProcessingRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:readDB?period=10000")
                .routeId("readDBId")
                .to("jpa:" + Client.class.getName()+"?namedQuery=fetchAll")
                .split(body())
                .process(new InboundMessageProcessor())
                .convertBodyTo(String.class)
                .to("file:src/data/output?fileName=output.csv&fileExist=append&appendChars=\\n")
                .toD("jpa:"+Client.class.getName()+"?nativeQuery=DELETE from client where id=${header.consumeID}&useExecuteUpdate=true")
                .log(LoggingLevel.INFO, "${body}")
                .end();
    }
}
