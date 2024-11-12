package com.project.jfb.io.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.project.jfb.io.entity.enums.ConcertHall;
import com.project.jfb.io.entity.enums.StadiumSector;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.view.Printable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static com.project.jfb.io.entity.AppConstants.UNSPECIFIED_DATE_TIME;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TicketEntity implements Printable {

    @Id
    private UUID id = UUID.randomUUID();

    @Column
    private TicketType ticketType = TicketType.NOT_SPECIFIED;

    @Column
    private Timestamp creationDate;

    @Column
    private UUID userID;

    @Transient
    private  ConcertHall concertHall = ConcertHall.NOT_SPECIFIED;

    @Transient
    private  int eventCode = 0;

    @Transient
    private Timestamp eventTime = UNSPECIFIED_DATE_TIME;

    @Transient
    private  boolean isPromo = false;

    @Transient
    private StadiumSector stadiumSector = StadiumSector.NOT_SPECIFIED;

    @Transient
    private  double backpackAllowedWeight = 0;

    @Transient
    private  BigDecimal price = BigDecimal.ZERO;


    public TicketEntity(ConcertHall concertHall, int eventCode, Timestamp eventTime) {
        this(concertHall, eventCode, eventTime, false, StadiumSector.NOT_SPECIFIED, 0, 0);
        ticketType = TicketType.NOT_SPECIFIED;

    }

    public TicketEntity(ConcertHall concertHall, int eventCode, Timestamp eventTime, boolean isPromo, StadiumSector stadiumSector, double backpackAllowedWeight, double price ) {

        this.concertHall = concertHall;
        this.eventCode = eventCode;
        setEventTime(eventTime);
        this.isPromo = isPromo;
        setStadiumSector(stadiumSector);
        this.backpackAllowedWeight = backpackAllowedWeight;
        this.price = new BigDecimal(price);
        ticketType = TicketType.NOT_SPECIFIED;

    }

    public TicketEntity(UUID userID, TicketType ticketType, Timestamp creationDate) {
        this(ConcertHall.NOT_SPECIFIED, 3, UNSPECIFIED_DATE_TIME, false, StadiumSector.NOT_SPECIFIED, 0, 0);
        this.userID = userID;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public TicketEntity(UUID ticketID, UUID userID, TicketType ticketType, Timestamp creationDate) {
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

        if (!(object instanceof TicketEntity)) {
            return false;
        }

        if (this == object) {
            return true;
        }

        if (concertHall == ((TicketEntity) object).concertHall &&
                eventCode == ((TicketEntity) object).eventCode &&
                eventTime.equals(((TicketEntity) object).eventTime) &&
                isPromo == ((TicketEntity) object).isPromo &&
                stadiumSector == ((TicketEntity) object).stadiumSector &&
                backpackAllowedWeight == ((TicketEntity) object).backpackAllowedWeight &&
               price.equals(((TicketEntity) object).price)) {
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

}
