package Model;

import View.Printer;

public abstract class User implements ID, Printer {


    public abstract String toString();

    public abstract void printRole();

}
