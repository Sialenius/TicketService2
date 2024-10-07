package View;

public interface Printable {

    public default void printInformation() {
        System.out.println(this.toString());
    }

}
