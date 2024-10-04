package Service;

import Model.Enums.ConcertHall;
import Model.Enums.StadiumSector;
import Model.ID;
import Model.Ticket;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;


public class TicketService implements ID {

    public static void main(String[] args) {

    Ticket ticket1 = new Ticket();

    LocalDateTime eventDateTime = LocalDateTime.of(2024, Month.NOVEMBER, 14, 18, 00);

    Ticket ticket2 = new Ticket(ConcertHall.Theatre, 123, eventDateTime);
    Ticket ticket3 = new Ticket(ConcertHall.Circus, 56,eventDateTime, true, StadiumSector.B, 2.5);

    ticket3.setPrice(50);



    //System.out.println(ticket3.toString());
    ticket3.printInformation();



    //TicketsStorage newStorage = new TicketsStorage(10);
    //newStorage.

    }
}
