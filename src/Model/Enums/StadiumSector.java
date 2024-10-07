package Model.Enums;

public enum StadiumSector {
    A,
    B,
    C,
    NOT_SPECIFIED("not specified");

    private String name;

    StadiumSector(String stadiumSector) {
        this.name = stadiumSector;
    }

    public String getStadiumSector() {
        return name;
    }
}
