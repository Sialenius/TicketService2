package Model;

import Model.Enums.ConcertHall;
import Model.Enums.StadiumSector;
import View.Printable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements ID, Printable {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm a");

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final DecimalFormat FORMATTER = new DecimalFormat("$#,##0.00");

    private ConcertHall concertHall;
    private int eventCode;
    private Timestamp eventTime;
    private boolean isPromo;
    private StadiumSector stadiumSector;

    //@Min(value = 0, message = "Invalid backpack weight")
    private double backpackAllowedWeight;

   // @Min(value = 0, message = "Invalid price")
    private BigDecimal price;

    public ConcertHall getConcertHall() {
        return concertHall;
    }

    public int getEventCode() {
        return eventCode;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        if (eventTime != null) {

            if (eventTime.isBefore(LocalDateTime.now())) {
                System.out.println("Invalid event time.");
                System.exit(0);
            }

            this.eventTime = Timestamp.valueOf(eventTime);
        }
    }

    public boolean isPromo() {
        return isPromo;
    }


    public StadiumSector getStadiumSector() {
        return stadiumSector;
    }

    public void setStadiumSector(StadiumSector stadiumSector) {

        this.stadiumSector = stadiumSector;
    }

    public double getbackpackAllowedWeight() {
        return backpackAllowedWeight;
    }


    public String getPrice() {
        return FORMATTER.format(this.price);
    }

    public Ticket() {
        this(ConcertHall.not_specified, 3, null, false, StadiumSector.not_specified, 0, 0); // How to create a Ticket without Event Time, not using "null"??
    }

    public Ticket(ConcertHall concertHall, int eventCode, LocalDateTime eventTime) {
        this(concertHall, eventCode, eventTime, false, StadiumSector.not_specified, 0, 0);
    }

    public Ticket(ConcertHall concertHall, int eventCode, LocalDateTime eventTime, boolean isPromo, StadiumSector stadiumSector, double backpackAllowedWeight, double price ) {

        this.concertHall = concertHall;
        this.eventCode = eventCode;
        setEventTime(eventTime);
        this.isPromo = isPromo;
        setStadiumSector(stadiumSector);
        this.backpackAllowedWeight = backpackAllowedWeight;
        this.price = new BigDecimal(price);
    }

    @Override
    public String toString() {
        return "-----Ticket-----" + '\n' +
                "Ticket ID: " + getId() + '\n' +
                "Concert hall: " + concertHall + '\n' +
                "Event code: " + eventCode + '\n' +
                "Event time: " + eventTime + '\n' +
                "Promo: " + isPromo + '\n' +
                "Stadium sector: " + stadiumSector + '\n' +
                "Backpack allowed weight: " + backpackAllowedWeight + '\n' +
                "Price: " + getPrice() + '\n' +
                "----------------";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Ticket)) {
            return false;
        }

        if (this == object) {
            return true;
        }

        if (concertHall == ((Ticket) object).concertHall &&
                eventCode == ((Ticket) object).eventCode &&
                eventTime.equals(((Ticket) object).eventTime) &&
                isPromo == ((Ticket) object).isPromo &&
                stadiumSector == ((Ticket) object).stadiumSector &&
                backpackAllowedWeight == ((Ticket) object).backpackAllowedWeight &&
               price.equals(((Ticket) object).price)) {
            return true;
        }
        else {
            return false;
        }
    }

  @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public void share(PhoneNumber phoneNumber) {
        System.out.println("Ticket "  + getId() + " was shared by phone number");
    }

    public void share(PhoneNumber phoneNumber, Email email) {
        System.out.println("Ticket " + getId() + " was shared by phone number and email");

    }

}
