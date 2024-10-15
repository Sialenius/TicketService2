package control;

import model.*;
import model.enums.ConcertHall;
import model.enums.StadiumSector;
import view.Printable;

import java.time.LocalDateTime;
import java.time.Month;


public class TicketService extends Entity implements Printable {

    public static void main(String[] args)  {

    Ticket ticket1 = new Ticket();
        LocalDateTime eventDateTime = LocalDateTime.of(2024, Month.NOVEMBER, 14, 18, 00);

    Ticket ticket2 = new Ticket(ConcertHall.THEATRE, 123, eventDateTime);

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

    System.out.println(ticket3.getId());
    System.out.println(ticket4.hashCode());

    // Homework #5: Custom ArrayList & Custom HashSet

    CustomArrayList<Integer> numbers = new CustomArrayList<>(4);

    numbers.put(1);
    numbers.put(2);
    numbers.put(3);
    numbers.put(4);
    numbers.put(5);
    System.out.println(numbers);


    CustomHashSet<String> strings = new CustomHashSet<>(2);
    strings.put("AA");
    strings.put("BB");
    strings.put("CC");
    strings.put("DD");
    System.out.println(strings);

    strings.put("EE");
    strings.put("DD");
    strings.put("QQQQQ", 3);
    System.out.println(strings);

    strings.deleteItemFromPosition(2);
    System.out.println(strings);




}


    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
