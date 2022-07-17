package com.study.camel.simplecamelspringboot.beans;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "client")
@NamedQuery(name = "fetchAll", query = "Select x from Client x")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "house_number")
    private String houseNumber;
    private String city;
    private String province;

    @Column(name = "postal_code")
    private String postalCode;

}
