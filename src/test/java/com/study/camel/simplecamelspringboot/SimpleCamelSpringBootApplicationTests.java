package com.study.camel.simplecamelspringboot;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
class SimpleCamelSpringBootApplicationTests {

    @Autowired
    CamelContext context;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;

    @Test
    void simpleTimerTest() throws Exception {
		String expectedBody = "Hello World!";

		mockEndpoint.expectedBodiesReceived(expectedBody);
		mockEndpoint.expectedMinimumMessageCount(1);

		AdviceWith.adviceWith(context, "simpleTimerID", routeBuilder ->{
			routeBuilder.weaveAddLast().to(mockEndpoint);
		});

        context.start();
        mockEndpoint.assertIsSatisfied();
    }

}
