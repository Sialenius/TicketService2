package model.enums;

public enum TicketType {
    EMPTY("Empty"),
    LIMITED("Limited"),
    FULL("Full");

    private String type;

    TicketType(String ticketType) {
        this.type = ticketType;
    }

    public String getType() {
        return type;
    }
}
