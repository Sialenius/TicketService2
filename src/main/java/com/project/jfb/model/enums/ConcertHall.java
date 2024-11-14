<<<<<<<< HEAD:src/main/java/com/project/jfb/model/enums/ConcertHall.java
package com.project.jfb.model.enums;
========
package com.project.jfb.io.entity.enums;
>>>>>>>> JFB-12.Spring_Boot1.2:src/main/java/com/project/jfb/io/entity/enums/ConcertHall.java

public enum ConcertHall {
    CIRCUS("Circus"),
    THEATRE("Theatre"),
    SPORT_PALACE("Sport_Palace"),
    NOT_SPECIFIED("not specified");

    private String name;

    ConcertHall(String concertHall) {
        this.name = concertHall;
    }

    public String getName() {
        return name;
    }
}
