package main.java.model;

public class Client extends User {
    private Ticket ticket;

    public void buyTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Client: " + "\n" +
                "ID: " + this.getId() + "\n" +
                ticket.toString();
    }

    @Override
    public void printRole() {
        System.out.println("You are a Client");
    }

}
