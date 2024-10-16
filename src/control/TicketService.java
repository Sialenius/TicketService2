package control;

import model.*;
import model.enums.ConcertHall;
import model.enums.StadiumSector;
import view.Printable;

import java.sql.SQLOutput;
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

    CustomArrayList<Integer> numbersList = new CustomArrayList<>(4);

    numbersList.put(1);
    numbersList.put(2);
    numbersList.put(3);
    numbersList.put(4);
    numbersList.put(5);
    System.out.println(numbersList);


    CustomHashSet<String> stringsSet = new CustomHashSet<>(2);
    stringsSet.put("AA");
    stringsSet.put("BB");
    stringsSet.put("CC");
    stringsSet.put("DD");
    System.out.println(stringsSet);

    stringsSet.put("EE");
    stringsSet.put("DD");
    stringsSet.put("QQQQQ", 3);
    System.out.println(stringsSet);

    stringsSet.deleteItemFromPosition(2);
    System.out.println(stringsSet);

    for (Iteratorable customIterator = stringsSet.getIterator(); customIterator.hasNext();) {
        System.out.print(customIterator.iterate() + " ");
    }




}


    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
