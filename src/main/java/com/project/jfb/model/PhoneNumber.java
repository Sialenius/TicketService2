<<<<<<<< HEAD:src/main/java/com/project/jfb/model/PhoneNumber.java
package com.project.jfb.model;
========
package com.project.jfb.io.entity;
>>>>>>>> JFB-12.Spring_Boot1.2:src/main/java/com/project/jfb/io/entity/PhoneNumber.java


import com.project.jfb.view.Printable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber implements Printable {
    private String phoneNumber;

    final static Pattern phoneNumberPattern = Pattern.compile("^((\\+?375)(25|29|33|44)([0-9]{7}))");
    private Matcher matcher;


    public PhoneNumber (String phoneNumber) {
        matcher = phoneNumberPattern.matcher(phoneNumber);
        if (!matcher.find()) {
            System.out.println("Enter a valid phone number in format '+375XXXXXXXXX'");
            System.exit(0);
        }
        else {
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public String toString() {
        return "phone number: " + phoneNumber;
    }
}
