package com.study.camel.simplecamelspringboot.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiverRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:clientqueue")
                .routeId("queueReceiverId")
                .log(LoggingLevel.INFO, "${body}");
    }
}
