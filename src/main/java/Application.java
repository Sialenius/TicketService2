import config.MyApplicationContextConfiguration;
import service.UserService;
import service.TicketService;
import model.*;
import model.enums.ConcertHall;
import model.enums.StadiumSector;
import model.enums.TicketType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.TicketDataReader;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Application {

    public static void main(String[] args) throws Exception {

        show11Task();

    }

    private static void show11Task() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        UserService userService = ctx.getBean(UserService.class);

        //CLEAR DATABASE
        userService.deleteAllUsers();


        //CREATE AND PUT USERS INTO DATABASE
        User user1 = new Client("Tom", Timestamp.valueOf(LocalDateTime.now()));
        User user2 = new Client("Jery", Timestamp.valueOf(LocalDateTime.now()));
        User user3 = new Client("Mike", Timestamp.valueOf(LocalDateTime.now()));
        User user4 = new Admin("Lee", Timestamp.valueOf(LocalDateTime.now()));

        userService.register(user1);
        userService.register(user2);
        userService.register(user3);
        userService.register(user4);

        userService.printInformation();


        //FETCH USER BY ID
        System.out.println(userService.fetchByUserID(user1.getId())); //THE FIELD 'TICKETS' STILL null?


        //CREATE AND PUT TICKETS INTO DATABASE
        TicketService ticketService = ctx.getBean(TicketService.class);

        Ticket ticket1 = new Ticket(user1.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now()));
        Ticket ticket2 = new Ticket(user2.getId(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now()));
        Ticket ticket3 = new Ticket(user3.getId(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now()));

        ticketService.register(ticket1);
        ticketService.register(ticket2);
        ticketService.register(ticket3);

        ticketService.printInformation();


        //FETCH TICKET BY ID AND USER ID
        System.out.println(ticketService.fetchByUserAndTicketID(user1.getId(), ticket1.getId()));


        //UPDATE TICKET TYPE
        ticketService.updateTicketType(ticket2.getId(), TicketType.WEEK);

        //REMOVE USER AND HIS TICKETS
        userService.removeUser(user3.getId());
        ticketService.printInformation();


        //UPDATE USER ID
        userService.updateUserID(user2.getId(), UUID.fromString("bbbbbbbb-bbbbb-bbbb-bbbb-bbbbbbbbb"));

        userService.printInformation();
        ticketService.printInformation();




        //READ JSON
        List<BusTicket> busTicketList = TicketDataReader.readTicketsData();
        busTicketList.forEach(System.out::println);




    }

    private static void showSpringTask() {

        /*
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

        userDAO.printInformation();

        // SAVE TICKETS
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(user1.getId().toString(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId().toString(), TicketType.WEEK, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId().toString(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId().toString(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId().toString(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user3.getId().toString(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));


        for (Ticket t: tickets) {
            ticketDAO.save(t);
        }

        userDAO.printInformation();
        ticketDAO.printInformation();


        // FETCH THE TICKET BY ID AND USER' ID
        Optional<Ticket> newTicket = ticketDAO.fetchByTicketAndUserID(tickets.get(0).getId(), user1.getId());
        if (newTicket.isEmpty()) {
            System.out.println("Invalid ticket or user UUID");
        } else {
            System.out.println(newTicket.get().toString());

        }


        // FETCH THE USER BY ID
        Optional<User> newUser = userDAO.fetchByID(user3.getId());
        if (newUser.isEmpty()) {
            System.out.println("Invalid user UUID");
        } else {
            System.out.println(newUser.get().toString());
        }


        // UPDATE THE TICKET TYPE
        ticketDAO.printInformation();
        ticketDAO.update(tickets.get(0).getId(), TicketType.MONTH);
        ticketDAO.printInformation(); //WHY DID THE RECORDS IN THE TABLE ROTATE UP BY 1 LINE?


        // DELETE USERS AND THEIR TICKETS
        userDAO.delete(user1.getId());

        userDAO.printInformation();
        ticketDAO.printInformation();



    }

    private static void showDBsTask () throws Exception {
        //Homework #8: DBs

        /*
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
        tickets.add(new Ticket(user1.getId().toString(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId().toString(), TicketType.WEEK, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId().toString(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId().toString(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId().toString(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user3.getId().toString(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));

        for (Ticket t: tickets) {
            ticketDAO.save(t);
        }

        userDAO.printInformation();
        ticketDAO.printInformation();


        // FETCH THE TICKET BY ID AND USER' ID
        Optional<Ticket> newTicket = ticketDAO.fetchByTicketAndUserID(tickets.get(0).getId(), user1.getId());
        if (newTicket.isEmpty()) {
            System.out.println("Invalid ticket or user UUID");
        } else {
            System.out.println(newTicket.get().toString());

        }


        // FETCH THE USER BY ID
        Optional<User> newUser = userDAO.fetchByID(user3.getId());
        if (newUser.isEmpty()) {
            System.out.println("Invalid user UUID");
        } else {
            System.out.println(newUser.get().toString());
        }


        // UPDATE THE TICKET TYPE
        ticketDAO.printInformation();
        ticketDAO.update(tickets.get(0).getId(), TicketType.MONTH);
        ticketDAO.printInformation(); //WHY DID THE RECORDS IN THE TABLE ROTATE UP BY 1 LINE?


        // DELETE USERS AND THEIR TICKETS
        userDAO.delete(user1.getId());

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

         */

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
