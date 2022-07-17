package com.study.camel.simplecamelspringboot.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutBoundClient {

    private String name;
    private String address;

}
