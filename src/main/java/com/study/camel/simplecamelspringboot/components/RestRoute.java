package com.study.camel.simplecamelspringboot.components;

import com.study.camel.simplecamelspringboot.beans.Client;
import com.study.camel.simplecamelspringboot.processors.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("jetty")
                .host("0.0.0.0")
                .port(8080)
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        rest("camel/study")
                .produces("application/json")
                .post("nameAddress").type(Client.class)
                .route()
                .routeId("newRestRouteID")
                .log(LoggingLevel.INFO, "${body}")
                .process(new InboundMessageProcessor())
                .convertBodyTo(String.class)
                .to("file:src/data/output?fileName=output.csv&fileExist=append&appendChars=\\n")
                .end();
    }
}
