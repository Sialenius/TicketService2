package com.project.jfb.view;

public interface Printable {

    public default void printInformation() {
        System.out.println(this.toString());
    }

}
