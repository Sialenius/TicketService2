package Control;

import Model.*;
import Model.Enums.ConcertHall;
import Model.Enums.StadiumSector;
import View.Printable;

import java.time.LocalDateTime;
import java.time.Month;


public class TicketService implements ID, Printable {

    public static void main(String[] args) {

    Ticket ticket1 = new Ticket();

    LocalDateTime eventDateTime = LocalDateTime.of(2024, Month.NOVEMBER, 14, 18, 00);

    Ticket ticket2 = new Ticket(ConcertHall.Theatre, 123, eventDateTime);
    Ticket ticket3 = new Ticket(ConcertHall.Circus, 56, eventDateTime, true, StadiumSector.B, 2.5, 10);
    Ticket ticket4 = new Ticket(ConcertHall.Circus, 56, eventDateTime, true, StadiumSector.B, 2.5, 10);


    Admin admin = new Admin();
    Client client = new Client();

    client.buyTicket(ticket4);
    admin.checkTicket(client.getTicket());

    client.getTicket().printInformation();


    Email email = new Email("Aliaksei.sialenia@mail.com");
    PhoneNumber phoneNumber = new PhoneNumber("375337191105");

    ticket3.share(phoneNumber);
    ticket4.share(phoneNumber, email);

    }

    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
