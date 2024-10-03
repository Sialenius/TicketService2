package Service;

import Model.ConcertHall;
import Model.StadiumSector;
import Model.Ticket;

import java.time.LocalDateTime;
import java.time.Month;


public class TicketService {

    public static void main(String[] args) {

    Ticket ticket1 = new Ticket();

    LocalDateTime eventDateTime = LocalDateTime.of(2024, Month.NOVEMBER, 14, 18, 00);

    Ticket ticket2 = new Ticket(ConcertHall.Theatre, 123, eventDateTime);
    Ticket ticket3 = new Ticket(ConcertHall.Circus, 56,eventDateTime, true, StadiumSector.B, 2.5);

    ticket3.setPrice(50);

    ticket1.printTicket();
    ticket2.printTicket();
    ticket3.printTicket();

    }
}
