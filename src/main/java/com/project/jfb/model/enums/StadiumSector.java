<<<<<<<< HEAD:src/main/java/com/project/jfb/model/enums/StadiumSector.java
package com.project.jfb.model.enums;
========
package com.project.jfb.io.entity.enums;
>>>>>>>> JFB-12.Spring_Boot1.2:src/main/java/com/project/jfb/io/entity/enums/StadiumSector.java

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
