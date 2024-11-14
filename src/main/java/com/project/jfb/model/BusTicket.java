<<<<<<<< HEAD:src/main/java/com/project/jfb/model/BusTicket.java
package com.project.jfb.model;
========
package com.project.jfb.io.entity;
>>>>>>>> JFB-12.Spring_Boot1.2:src/main/java/com/project/jfb/io/entity/BusTicket.java

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusTicket {

    private String ticketClass;

    private String ticketType;

    private String startDate;

    private String price;
}
