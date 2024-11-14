<<<<<<<< HEAD:src/main/java/com/project/jfb/model/enums/UserRole.java
package com.project.jfb.model.enums;
========
package com.project.jfb.io.entity.enums;
>>>>>>>> JFB-12.Spring_Boot1.2:src/main/java/com/project/jfb/io/entity/enums/UserRole.java

public enum UserRole {
    CLIENT("Client"),
    ADMIN("Admin");

    public String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
 }
