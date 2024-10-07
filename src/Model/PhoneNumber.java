package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    private String phoneNumber;

    final static Pattern phoneNumberPattern = Pattern.compile("^((\\+?375)(44)([0-9]{7}))");
    private Matcher matcher;


    public PhoneNumber (String phoneNumber) {
        matcher = phoneNumberPattern.matcher(phoneNumber);
        if (!matcher.find()) {
            System.out.println("Enter a valid phone number, that starts with +375");
            System.exit(0);
        }
        else {
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public String toString() {
        return "Phone number: " + phoneNumber;
    }
}
