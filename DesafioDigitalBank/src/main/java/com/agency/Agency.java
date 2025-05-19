package com.agency;

import lombok.*;

@Getter
@Setter
@ToString
public class Agency {
    private static int numberSequency = 0;
    protected String name;
    protected int number;
    protected String address;

    public Agency(String name, String address) {
        this.name = name;
        this.number = numberSequency++;
        this.address = address;
    }
}
