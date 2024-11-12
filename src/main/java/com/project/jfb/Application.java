package com.project.jfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

/*
    private static void show11Task() throws IllegalAccessException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        ApplicationService applicationService = ctx.getBean(ApplicationService.class);

        //CLEAR DATABASE
        applicationService.deleteAllUsers();
        applicationService.printInformation();


        //CREATE AND PUT USERS INTO DATABASE
        User user1 = new Client("Tom", Timestamp.valueOf(LocalDateTime.now()));
        User user2 = new Client("Jery", Timestamp.valueOf(LocalDateTime.now()));
        User user3 = new Client("Mike", Timestamp.valueOf(LocalDateTime.now()));
        User user4 = new Admin("Lee", Timestamp.valueOf(LocalDateTime.now()));

        applicationService.registerUser(user1);
        applicationService.registerUser(user2);
        applicationService.registerUser(user3);
        applicationService.registerUser(user4);


        //CREATE AND PUT TICKETS INTO DATABASE
        Ticket ticket1 = new Ticket(user1.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now()));
        Ticket ticket2 = new Ticket(user2.getId(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now()));
        Ticket ticket3 = new Ticket(user3.getId(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now()));

        applicationService.registerTicket(ticket1);
        applicationService.registerTicket(ticket2);
        applicationService.registerTicket(ticket3);


        //SHOW ALL USERS AND TICKETS
        applicationService.printInformation();



        //FETCH USER BY ID
        System.out.println(applicationService.fetchByUserID(user1.getId())); //THE FIELD 'TICKETS' STILL null?


        //FETCH TICKET BY ID AND USER ID
        System.out.println(applicationService.fetchTicketByUserAndTicketID(user1.getId(), ticket1.getId()));


        //UPDATE TICKET TYPE
        applicationService.updateTicketType(ticket2.getId(), TicketType.WEEK);


        //REMOVE USER AND HIS TICKETS
        applicationService.removeUser(user3.getId());
        applicationService.printTicketInformation();


        //UPDATE USER ID AND CREATE NEW TICKET
        applicationService.updateUserIDAndCreateTicket(user2.getId(), UUID.fromString("bbbbbbbb-bbbbb-bbbb-bbbb-bbbbbbbbb"));

        applicationService.printInformation();


        //READ JSON
        List<BusTicket> busTicketList = TicketDataReader.readTicketsData();
        busTicketList.forEach(System.out::println);

    }

    private static void showSpringTask() {


        //Homework #10: Spring
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);

        UserDAO userDAO = ctx.getBean(UserDAO.class);
        TicketDAO ticketDAO = ctx.getBean(TicketDAO.class);

        // CLEAR THE DATABASE BEFORE THE SHOWCASE
        ticketDAO.getAll();
        userDAO.deleteAll();


        // SAVE USERS
        User user1 = new Client("Henry", Timestamp.valueOf(LocalDateTime.now()));
        User user2 = new Client("Mike", Timestamp.valueOf(LocalDateTime.now()));
        User user3 = new Admin("Lee", Timestamp.valueOf("2024-11-14 18:00:00"));

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);


        // SAVE TICKETS
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(user1.getId(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId(), TicketType.WEEK, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user3.getId(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));


        for (Ticket t: tickets) {
            ticketDAO.save(t);
        }

        // FETCH THE TICKET BY ID AND USER' ID
        Ticket newTicket = ticketDAO.fetchByTicketAndUserID(tickets.get(0).getId(), user1.getId());
        System.out.println(newTicket.toString());


        // FETCH THE USER BY ID
        User newUser = userDAO.fetchByID(user3.getId());
        System.out.println(newUser.toString());


        // UPDATE THE TICKET TYPE
        ticketDAO.update(tickets.get(0).getId(), TicketType.MONTH);


        // DELETE USERS AND THEIR TICKETS
        userDAO.delete(user1.getId());

    }

    private static void showDBsTask () throws Exception {
        //Homework #8: DBs


        /*
        File file = new File("src/main/resources/ConnectionConfiguration.txt");

        UserDAO userDAO = new UserDAO(ConfigurationReader.read(file));
        TicketDAO ticketDAO = new TicketDAO(ConfigurationReader.read(file));

        // CLEAR THE DATABASE BEFORE THE SHOWCASE
        userDAO.deleteAll();


        // SAVE USERS
        User user1 = new Client("Henry", Timestamp.valueOf("2024-10-21"));
        User user2 = new Client("Mike", Timestamp.valueOf("2024-05-15"));
        User user3 = new Admin("Lee", Timestamp.valueOf(LocalDateTime.now()));

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);


        // SAVE TICKETS
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(user1.getId(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId(), TicketType.WEEK, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId(), TicketType.MONTH, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user2.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user1.getId(), TicketType.YEAR, Timestamp.valueOf(LocalDateTime.now())));
        tickets.add(new Ticket(user3.getId(), TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));

        for (Ticket t: tickets) {
            ticketDAO.save(t);
        }


        // FETCH THE TICKET BY ID AND USER' ID
        Ticket newTicket = ticketDAO.fetchByTicketAndUserID(tickets.get(0).getId(), user1.getId());
        System.out.println(newTicket.toString());


        // FETCH THE USER BY ID
        User newUser = userDAO.fetchByID(user3.getId());
        System.out.println(newUser.toString());


        // UPDATE THE TICKET TYPE
        ticketDAO.update(tickets.get(0).getId(), TicketType.MONTH);


        // DELETE USERS AND THEIR TICKETS
        userDAO.delete(user1.getId());






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

 */

    @Override
    public String toString() {
        String string = new String();
        string += "Ticket Service" + "\n";

        return string;
    }
}
