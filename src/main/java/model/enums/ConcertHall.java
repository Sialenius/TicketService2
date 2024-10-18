package main.java.model.enums;

public enum ConcertHall {
    CIRCUS("Circus"),
    THEATRE("Theatre"),
    SPORT_PALACE("Sport_Palace"),
    NOT_SPECIFIED("not specified");

    private String name;

    ConcertHall(String concertHall) {
        this.name = concertHall;
    }

    public String getName() {
        return name;
    }
}
