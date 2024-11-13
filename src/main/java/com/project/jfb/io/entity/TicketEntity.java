package com.project.jfb.io.entity;


import jakarta.persistence.*;
import lombok.*;
import com.project.jfb.io.entity.enums.ConcertHall;
import com.project.jfb.io.entity.enums.StadiumSector;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.view.Printable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static com.project.jfb.io.entity.AppConstants.UNSPECIFIED_DATE_TIME;


@Entity
@Data
@Table(name="tickets_info")
@NoArgsConstructor
public class TicketEntity implements Printable {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID ticketId = UUID.randomUUID();

    @Column(name="user_id", nullable = false)
    private UUID userId;

    @Column(name="ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType = TicketType.NOT_SPECIFIED;

    @Column(nullable = false)
    private Timestamp creationDate;


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

    public TicketEntity(UUID userId, TicketType ticketType, Timestamp creationDate) {
        this(ConcertHall.NOT_SPECIFIED, 3, UNSPECIFIED_DATE_TIME, false, StadiumSector.NOT_SPECIFIED, 0, 0);
        this.userId = userId;
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
        System.out.println("Ticket "  + getTicketId() + " was shared by " + phoneNumber);
    }

    public void share(PhoneNumber phoneNumber, Email email) {
        System.out.println("Ticket " + getTicketId() + " was shared by " + phoneNumber + " and " + email);

    }

  @Override
    public int hashCode() {
      int result = 31 * this.getTicketId().hashCode();
      result += 31 * concertHall.hashCode();
      result += 31 * eventCode;
      result += 31 * eventTime.hashCode();
      result += 31 * stadiumSector.hashCode();
      result += (int) (31 * backpackAllowedWeight);
      result += 31 * price.hashCode();
      return result;
    }

}
