package main.java.model;

import main.java.view.Printable;

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
