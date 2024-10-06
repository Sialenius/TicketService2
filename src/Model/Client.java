package Model;

public class Client extends User {
    private Ticket ticket;

    public void buyTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    @Override
    public String toString() {
        return "Client: " + "\n" +
                "ID: " + this.getId();
    }

    @Override
    public void printRole() {
        System.out.println("You are a Client");
    }

}
