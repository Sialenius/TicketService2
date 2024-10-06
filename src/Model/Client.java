package Model;

public class Client extends User {

    @Override
    public String toString() {
        return "Client: " + "\n" +
                "ID: " + this.getId();
    }

}
