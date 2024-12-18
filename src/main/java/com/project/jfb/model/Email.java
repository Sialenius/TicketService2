<<<<<<<< HEAD:src/main/java/com/project/jfb/model/Email.java
package com.project.jfb.model;
========
package com.project.jfb.io.entity;
>>>>>>>> JFB-12.Spring_Boot1.2:src/main/java/com/project/jfb/io/entity/Email.java


import com.project.jfb.view.Printable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements Printable {
    private String email;

    final static Pattern emailPattern = Pattern.compile("@");
    private Matcher matcher;

    public Email(String email) {
       matcher = emailPattern.matcher(email);
        if (!matcher.find()) {
            System.out.println("Enter a valid email");
            System.exit(0);
        }
        else {
            this.email = email;
        }
    }

    public String getEmail() {
    return email;
    }

    @Override
    public String toString() {
        return "email: " + email;
    }
}
