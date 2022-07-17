package com.study.camel.simplecamelspringboot.processors;

import com.study.camel.simplecamelspringboot.beans.Client;
import com.study.camel.simplecamelspringboot.beans.OutBoundClient;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundMessageProcessor implements Processor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Exchange exchange) throws Exception {
        Client client = exchange.getIn().getBody(Client.class);
        String outboundClient = transformToOutbound(client);
        exchange.getIn().setBody(outboundClient);
        exchange.getIn().setHeader("consumeID",client.getId());
    }

    private String transformToOutbound(Client client) {
        return new OutBoundClient(client.getName(), client.getHouseNumber()
                + " " + client.getCity()
                + " " + client.getProvince()
                + " " + client.getPostalCode()).toString();
    }
}
