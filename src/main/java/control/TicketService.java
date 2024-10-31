package control;

import config.ConfigurationReader;
import config.MyApplicationContextConfiguration;
import model.Entity;
import model.User;
import model.Client;
import model.Admin;
import model.Ticket;
import model.CustomArrayList;
import model.CustomHashSet;
import model.Iteratorable;

import DAO.UserDAO;
import DAO.TicketDAO;

import model.enums.ConcertHall;
import model.enums.StadiumSector;
import model.enums.TicketType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.Printable;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;


public class TicketService extends Entity implements Printable {

    public static void main(String[] args) throws Exception {

        showSpringTask();

    }

    private static void showSpringTask() {

        //Homework #10: Spring
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);

        UserDAO userDAO = ctx.getBean(UserDAO.class);
        TicketDAO ticketDAO = ctx.getBean(TicketDAO.class);

        // CLEAR THE DATABASE BEFORE THE SHOWCASE
        ticketDAO.getAll();
        userDAO.deleteAll();

        userDAO.printInformation();
        ticketDAO.printInformation();


        // SAVE USERS
        User user1 = new Client("Henry", Timestamp.valueOf(LocalDateTime.now()));
        User user2 = new Client("Mike", Timestamp.valueOf(LocalDateTime.now()));
        User user3 = new Admin("Lee", Timestamp.valueOf("2024-11-14 18:00:00"));

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);


        // SAVE TICKETS
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(user1.getID(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getID(), TicketType.WEEK, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getID(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getID(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getID(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user3.getID(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));

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



    }

    private static void showDBsTask () throws Exception {
        //Homework #8: DBs

        File file = new File("src/main/resources/ConnectionConfiguration.txt");

        UserDAO userDAO = new UserDAO(ConfigurationReader.read(file));
        TicketDAO ticketDAO = new TicketDAO(ConfigurationReader.read(file));

        // CLEAR THE DATABASE BEFORE THE SHOWCASE
        userDAO.deleteAll();

        userDAO.printInformation();
        ticketDAO.printInformation();


        // SAVE USERS
        User user1 = new Client("Henry", Timestamp.valueOf("2024-10-21"));
        User user2 = new Client("Mike", Timestamp.valueOf("2024-05-15"));
        User user3 = new Admin("Lee", Timestamp.valueOf(LocalDateTime.now()));

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);


        // SAVE TICKETS
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(user1.getID(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getID(), TicketType.WEEK, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getID(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getID(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getID(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user3.getID(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));

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



    }

    private static void showCollectionsTask () {

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


        // Iterator
        for (Iteratorable customIterator = stringsSet.getIterator(); customIterator.hasNext();) {
            System.out.print(customIterator.iterate() + " ");
        }

    }

    private static void showOOPTask() {
        Ticket ticket1 = new Ticket();

        Timestamp eventDateTime = Timestamp.valueOf("2024-11-14 18:00");

        Ticket ticket2 = new Ticket(ConcertHall.THEATRE, 123, eventDateTime);
        Ticket ticket3 = new Ticket(ConcertHall.CIRCUS, 56, eventDateTime, true, StadiumSector.B, 2.5, 50);

        ticket1.printInformation();
        ticket2.printInformation();
        ticket3.printInformation();
    }

    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
