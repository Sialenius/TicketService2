package model;

import java.lang.reflect.Field;

public interface AnnaotationAnalizator {

    public default void analyze() throws IllegalAccessException {
        System.out.println("Hi");
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotEmpty.class)) {
                //field.setAccessible(true);
                String objectValue = (String) field.get(this);
                System.out.println(objectValue);

                if (objectValue instanceof String stringValue) {
                    System.out.println("Hi");
                    System.out.println(stringValue);
                    //System.exit(1);
                }
            }
        }
    }
}

