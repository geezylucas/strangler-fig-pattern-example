package com.geezylucas.stranglerfigpatternexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    private String id;
    private String name;
}