package com.study.camel.simplecamelspringboot.components;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
class LegacyFileExternalProcessorRouteTest {

    @Autowired
    CamelContext context;

    @Autowired
    ProducerTemplate producerTemplate;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;


    @Test
    void moveFileMockingFromEndpoint() throws Exception{
        String expectedBody = "OutBoundClient(name=Mike, address= 111  Toronto  ON  M5F3D2)";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);

        AdviceWith.adviceWith(context, "legacyFileTransferWithExternalProcessorRoute", routeBuilder ->{
            routeBuilder.replaceFromWith("direct:mockStart");
            routeBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
        });

        context.start();
        producerTemplate.sendBody("direct:mockStart", "name, house_number, city, province, postal_code\n" +
                "Mike, 111, Toronto, ON, M5F3D2");
        mockEndpoint.assertIsSatisfied();
    }

}