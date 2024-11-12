package com.project.jfb.io.entity.enums;

public enum StadiumSector {
    A("A"),
    B("B"),
    C("B"),
    NOT_SPECIFIED("not specified");

    private String name;

    StadiumSector(String stadiumSector) {
        this.name = stadiumSector;
    }

    public String getName() {
        return name;
    }
}
