package control;

import model.*;
import model.enums.ConcertHall;
import model.enums.StadiumSector;
import model.enums.TicketType;
import org.postgresql.shaded.com.ongres.scram.client.ScramClient;
import view.Printable;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


public class TicketService extends Entity implements Printable {

    public static void main(String[] args) throws ClassNotFoundException {

        //Homework #8: DBs
        String URL = "jdbc:postgresql://localhost:5432/TicketServiceDB";
        String USER_NAME = "postgres";
        String PASSWORD = "123";

        Class.forName("org.postgresql.Driver"); // WHAT IS THIS LINE FOR?

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

            UserDAO userDAO = new UserDAO(connection);
            TicketDAO ticketDAO = new TicketDAO(connection);

            // CLEAR THE DATABASE BEFORE THE SHOWCASE
            userDAO.deleteAll();

            userDAO.printInformation();
            ticketDAO.printInformation();


            // SAVE USERS
            User user1 = new Client("Henry", Date.valueOf("2024-08-10").toLocalDate());
            User user2 = new Client("Mike", Date.valueOf("2024-05-15").toLocalDate());
            User user3 = new Admin("Lee", LocalDate.now());

            userDAO.save(user1);
            userDAO.save(user2);
            userDAO.save(user3);


            // SAVE TICKETS
            ArrayList<Ticket> tickets = new ArrayList<>();
            tickets.add(new Ticket(user1.getID(), TicketType.DAY, LocalDate.now()));
            tickets.add(new Ticket(user1.getID(), TicketType.WEEK, LocalDate.now()));
            tickets.add(new Ticket(user2.getID(), TicketType.MONTH, LocalDate.now()));
            tickets.add(new Ticket(user2.getID(), TicketType.YEAR, LocalDate.now()));
            tickets.add(new Ticket(user1.getID(), TicketType.YEAR, LocalDate.now()));
            tickets.add(new Ticket(user3.getID(), TicketType.DAY, LocalDate.now()));

            for (Ticket t: tickets) {
                ticketDAO.save(t);
            }

            userDAO.printInformation();
            ticketDAO.printInformation();


            // FETCH THE TICKET BY ID AND USER' ID
            Optional<Ticket> newTicket = ticketDAO.fetchByTicketAndUserID(tickets.get(0).getID(), user1.getID());
            if (newTicket.isEmpty()) {
                System.out.println("Invalid ticket or user UUID");
            } else {
                System.out.println(newTicket.get().toString());

            }


            // FETCH THE USER BY ID
           Optional<User> newUser = userDAO.fetchByID(user3.getID());
            if (newUser.isEmpty()) {
                System.out.println("Invalid user UUID");
            } else {
                System.out.println(newUser.get().toString());
            }


            // UPDATE THE TICKET TYPE
            ticketDAO.printInformation();
            ticketDAO.update(tickets.get(0).getID(), TicketType.MONTH);
            ticketDAO.printInformation(); //WHY DID THE RECORDS IN THE TABLE ROTATE UP BY 1 LINE?


            // DELETE USERS AND THEIR TICKETS
            userDAO.delete(user1.getID());

            userDAO.printInformation();
            ticketDAO.printInformation();


            // CLOSE CONNECTIONS
            userDAO.close();
            ticketDAO.close();


        }
        catch (SQLException e) {

        }


    }

    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
