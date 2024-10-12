package control;

import model.Ticket;
import model.Entity;
import model.Admin;
import model.Client;
import model.Email;
import model.PhoneNumber;
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

    }

    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
