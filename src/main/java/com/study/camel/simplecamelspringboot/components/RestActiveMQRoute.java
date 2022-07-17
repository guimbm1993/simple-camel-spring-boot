package com.study.camel.simplecamelspringboot.components;

import com.study.camel.simplecamelspringboot.beans.Client;
import com.study.camel.simplecamelspringboot.processors.InboundMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestActiveMQRoute extends RouteBuilder {


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
                .post("clientmq").type(Client.class)
                .route()
                .routeId("activeMQRouteID")
                .log(LoggingLevel.INFO, "${body}")
                .to("activemq:queue:clientqueue?exchangePattern=InOnly")
                .to("jpa:"+ Client.class.getName());
    }
}
