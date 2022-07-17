package com.study.camel.simplecamelspringboot.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleTimer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:simpletimer?period=2000")
                .id("simpleTimerID")
                .setBody(constant("Hello World!"))
                .log(LoggingLevel.INFO, "${body}");
    }
}
