package main.java.model;

import main.java.view.Printable;

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
