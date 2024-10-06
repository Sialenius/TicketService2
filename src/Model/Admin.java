package Model;

import java.time.LocalDateTime;

public class Admin extends User {

    public void checkTicket(Ticket ticket) {
       // if (LocalDateTime.now() > ticket.getEventTime())
    }

    @Override
    public String toString() {
        return "Admin: " + "\n" +
                "ID: " + this.getId();
    }
}
