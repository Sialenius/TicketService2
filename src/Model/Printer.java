package Model;

public interface Printer {

    public default void printInformation() {
        System.out.println(this.toString());
    }

}
