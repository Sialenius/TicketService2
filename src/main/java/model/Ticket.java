package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.ConcertHall;
import model.enums.StadiumSector;
import model.enums.TicketType;
import view.Printable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static model.AppConstants.UNSPECIFIED_DATE_TIME;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket implements Printable {

    @Id
    private UUID id = UUID.randomUUID();

    @Column (name = "ticket_type")
    private TicketType ticketType = TicketType.NOT_SPECIFIED;

    @Column (name = "creation_date")
    private Timestamp creationDate;

    @Column (name = "userID_id")
    private String userID;

    private  ConcertHall concertHall = ConcertHall.NOT_SPECIFIED;
    private  int eventCode = 0;

    private Timestamp eventTime = UNSPECIFIED_DATE_TIME;
    private  boolean isPromo = false;

    private StadiumSector stadiumSector = StadiumSector.NOT_SPECIFIED;

    private  double backpackAllowedWeight = 0;

    private  BigDecimal price = BigDecimal.ZERO;


    public Ticket(ConcertHall concertHall, int eventCode, Timestamp eventTime) {
        this(concertHall, eventCode, eventTime, false, StadiumSector.NOT_SPECIFIED, 0, 0);
        ticketType = TicketType.NOT_SPECIFIED;

    }

    public Ticket(ConcertHall concertHall, int eventCode, Timestamp eventTime, boolean isPromo, StadiumSector stadiumSector, double backpackAllowedWeight, double price ) {

        this.concertHall = concertHall;
        this.eventCode = eventCode;
        setEventTime(eventTime);
        this.isPromo = isPromo;
        setStadiumSector(stadiumSector);
        this.backpackAllowedWeight = backpackAllowedWeight;
        this.price = new BigDecimal(price);
        ticketType = TicketType.NOT_SPECIFIED;

    }

    public Ticket (String userID, TicketType ticketType, Timestamp creationDate) {
        this(ConcertHall.NOT_SPECIFIED, 3, UNSPECIFIED_DATE_TIME, false, StadiumSector.NOT_SPECIFIED, 0, 0);
        this.userID = userID;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public Ticket (UUID ticketID, String userID, TicketType ticketType, Timestamp creationDate) {
        this(ConcertHall.NOT_SPECIFIED, 3, UNSPECIFIED_DATE_TIME, false, StadiumSector.NOT_SPECIFIED, 0, 0);
        this.setId(ticketID);
        this.userID = userID;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
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
        return "============== TICKET ============" + '\n' +
                "Ticket ID: " + getId() + '\n' +
                "userID ID: " + userID + '\n' +
                "Ticket type: " + getTicketType() + '\n' +
                "Creation date: " + getCreationDate() + '\n' +
                "Concert hall: " + concertHall.getName() + '\n' +
                "Event code: " + eventCode + '\n' +
                "Event time: " + eventTime + '\n' +
                "Promo: " + isPromo + '\n' +
                "Stadium sector: " + stadiumSector.getName() + '\n' +
                "Backpack allowed weight: " + backpackAllowedWeight + '\n' +
                "Price: " + getPrice() + '\n';
    }
}
