package Control;

import Model.Ticket;
import Model.Entity;
import Model.Admin;
import Model.Client;
import Model.Email;
import Model.PhoneNumber;
import Model.Enums_and_constants.ConcertHall;
import Model.Enums_and_constants.StadiumSector;
import View.Printable;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.Month;


public class TicketService extends Entity implements Printable {

    public static void main(String[] args) throws IllegalAccessException {

    //AnnaotationAnalizator analizator = new AnnaotationAnalizator();
    //analizator.analize(Ticket.class);

    Ticket ticket1 = new Ticket();
        LocalDateTime eventDateTime = LocalDateTime.of(2024, Month.NOVEMBER, 14, 18, 00);

    Ticket ticket2 = new Ticket(ConcertHall.THEATRE, 123, eventDateTime);
/*

    for (Field field: ticket2.getClass().getDeclaredFields()) {
        if (field.isAnnotationPresent(NotEmpty.class)) {
            Object objectValue = field;
            NotEmpty value = field.getAnnotation(NotEmpty.class);
            if (field.get(ticket2) == null) {
                System.out.println(value.message());

            }

            if (objectValue instanceof String stringValue) {
                System.out.println(stringValue.toUpperCase());
            }
        }
    }

 */



    Ticket ticket3 = new Ticket(ConcertHall.SPORT_PALACE, 56, eventDateTime, true, StadiumSector.B, 2.5, 10);
    Ticket ticket4 = new Ticket(ConcertHall.CIRCUS, 56, eventDateTime, true, StadiumSector.B, 2.5, 10);
    ticket1.printInformation();
    ticket3.printInformation();
    ticket4.printInformation();

    System.out.println(ticket3.equals(ticket4));

    Admin admin = new Admin();
    Client client = new Client();

    client.buyTicket(ticket4);
    admin.checkTicket(client.getTicket());

    client.getTicket().printInformation();



    Email email = new Email("Aliaksei.sialenia@mail.com");
    PhoneNumber phoneNumber = new PhoneNumber("375337191105");

    ticket3.share(phoneNumber);
    ticket4.share(phoneNumber, email);

    System.out.println(ticket3.hashCode());
        System.out.println(ticket4.hashCode());

    }

    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
