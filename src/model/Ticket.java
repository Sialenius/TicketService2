package model;

import model.enums.ConcertHall;
import model.enums.StadiumSector;
import view.Printable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static model.AppConstants.UNSPECIFIED_DATE_TIME;


public class Ticket extends Entity implements Printable, AnnaotationAnalizator {


    private final ConcertHall concertHall;
    private final int eventCode;

    @NotEmpty(message = "Event time must be defined.")
    private Timestamp eventTime;
    private final boolean isPromo;

    @NotEmpty(message = "Stadium sector must be defined.")
    private StadiumSector stadiumSector;

    @Min(value = 0, message = "Invalid backpack weight")
    private final double backpackAllowedWeight;

    @Min(value = 0, message = "Invalid price")
    private final BigDecimal price;

    public Ticket() {
        this(ConcertHall.NOT_SPECIFIED, 3, UNSPECIFIED_DATE_TIME, false, null, 0, 0); // How to create a Ticket without Event Time, not using "null"??
    }

    public Ticket(ConcertHall concertHall, int eventCode, LocalDateTime eventTime) {
        this(concertHall, eventCode, eventTime, false, StadiumSector.NOT_SPECIFIED, 0, 0);
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
        if (eventTime != null & eventTime != UNSPECIFIED_DATE_TIME) {

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
        return AppConstants.FORMATTER.format(this.price);
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

    public void share(PhoneNumber phoneNumber) {
        System.out.println("Ticket "  + getId() + " was shared by " + phoneNumber);
    }

    public void share(PhoneNumber phoneNumber, Email email) {
        System.out.println("Ticket " + getId() + " was shared by " + phoneNumber + " and " + email);

    }

  @Override
    public int hashCode() {
      int result = 31 * this.getId().hashCode();
      result += 31 * concertHall.hashCode();
      result += 31 * eventCode;
      result += 31 * eventTime.hashCode();
      result += 31 * stadiumSector.hashCode();
      result += (int) (31 * backpackAllowedWeight);
      result += 31 * price.hashCode();
      return result;
    }



    @Override
    public String toString() {
        return "-----Ticket-----" + '\n' +
                "Ticket ID: " + getId() + '\n' +
                "Concert hall: " + concertHall.getName() + '\n' +
                "Event code: " + eventCode + '\n' +
                "Event time: " + eventTime + '\n' +
                "Promo: " + isPromo + '\n' +
               // "Stadium sector: " + stadiumSector.getName() + '\n' +
                "Backpack allowed weight: " + backpackAllowedWeight + '\n' +
                "Price: " + getPrice() + '\n' +
                "----------------";
    }

}
