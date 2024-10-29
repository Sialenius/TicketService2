package control;

import config.ConfigurationReader;
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
import view.Printable;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class TicketService implements Printable {

    public static void main(String[] args) throws Exception {

      showHibernateTask();

    }

    private static void showHibernateTask() {


        // CREATE AND SAVE USERS
        UserDAO userDAO = new UserDAO();

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("First"));
        users.add(new User("Second"));
        users.add(new User("Third"));

        for (User u: users) {
            userDAO.save(u);
        }


        //CREATE AND SAVE TICKETS
        TicketDAO ticketDAO = new TicketDAO();

        ArrayList<Ticket> tickets = new ArrayList<>();

        tickets.add(new Ticket(users.get(0)));
        tickets.add(new Ticket(users.get(0)));
        tickets.add(new Ticket(users.get(1)));
        tickets.add(new Ticket(users.get(1)));
        tickets.add(new Ticket(users.get(2)));
        tickets.add(new Ticket(users.get(2)));

        for (Ticket t: tickets) {
            ticketDAO.save(t);
        }


        //SHOW TICKETS
        ticketDAO.printInformation();


        //FETCH THE USER BY ID
        User newUser = userDAO.fetchUser(users.get(0).getId());
        System.out.println(newUser);


        //FETCH THE TICKET BY TICKET' AND USER' ID
        List<Ticket> ticketsFromDB = ticketDAO.fetchByTicketAndUserID(tickets.get(0).getId(), users.get(0));
        ticketsFromDB.forEach(System.out::println);


        //UPDATE THE TICKET TYPE
        ticketDAO.updateType(tickets.get(0).getId(), TicketType.YEAR);

        //DELETE TICKET
        ticketDAO.delete(tickets.get(1).getId());


        //DELETE USER AND SHOW ThE RESULT
        userDAO.delete(users.get(0));
        userDAO.printInformation();
        ticketDAO.printInformation();


        //UPDATE THE USER
        userDAO.updateUserID(users.get(1).getId(), "QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
        userDAO.printInformation();
        ticketDAO.printInformation();
    }


    private static void showDBsTask () throws Exception {
        //Homework #8: DBs

        File file = new File("src/main/resources/ConnectionConfiguration.txt");

        Class.forName("org.postgresql.Driver");

        UserDAO userDAO = new UserDAO();
        TicketDAO ticketDAO = new TicketDAO();

        // CLEAR THE DATABASE BEFORE THE SHOWCASE
       // userDAO.deleteAll();

        userDAO.printInformation();
        ticketDAO.printInformation();


        // SAVE USERS
        User user1 = new Client("Henry", Timestamp.valueOf("2024-08-10"));
        User user2 = new Client("Mike", Timestamp.valueOf("2024-05-15"));
        User user3 = new Admin("Lee", Timestamp.valueOf(LocalDateTime.now()));

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);


        // SAVE TICKETS
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(UUID.fromString(user1.getId()), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(UUID.fromString(user1.getId()), TicketType.WEEK, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(UUID.fromString(user2.getId()), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(UUID.fromString(user2.getId()), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(UUID.fromString(user3.getId()), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(UUID.fromString(user3.getId()), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));


        for (Ticket t: tickets) {
            ticketDAO.save(t);
        }

        userDAO.printInformation();
        ticketDAO.printInformation();


        // FETCH THE TICKET BY ID AND USER' ID
       /*
        Optional<Ticket> newTicket = ticketDAO.fetchByTicketAndUserID(UUID.fromString(tickets.get(0).getId()), UUID.fromString(user1.getId()));
        if (newTicket.isEmpty()) {
            System.out.println("Invalid ticket or user UUID");
        } else {
            System.out.println(newTicket.get().toString());

        }



        // FETCH THE USER BY ID
        Optional<User> newUser = userDAO.fetchByID(UUID.fromString(user3.getId()));
        if (newUser.isEmpty()) {
            System.out.println("Invalid user UUID");
        } else {
            System.out.println(newUser.get().toString());
        }


        // UPDATE THE TICKET TYPE
        ticketDAO.printInformation();
       // ticketDAO.update(UUID.fromString(tickets.get(0).getId()), TicketType.MONTH);
        ticketDAO.printInformation(); //WHY DID THE RECORDS IN THE TABLE ROTATE UP BY 1 LINE?


        // DELETE USERS AND THEIR TICKETS
        userDAO.delete(UUID.fromString(user1.getId()));

        userDAO.printInformation();
        ticketDAO.printInformation();

        */

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

        LocalDateTime eventDateTime = LocalDateTime.of(2024, Month.NOVEMBER, 14, 18, 00);

       /*
        Ticket ticket2 = new Ticket(ConcertHall.THEATRE, 123, eventDateTime);
        Ticket ticket3 = new Ticket(ConcertHall.CIRCUS, 56, eventDateTime, true, StadiumSector.B, 2.5, 50);

        ticket1.printInformation();
        ticket2.printInformation();
        ticket3.printInformation();

        */
    }

    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
