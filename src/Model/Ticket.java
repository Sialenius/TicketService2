package Model;

import Model.Enums.ConcertHall;
import Model.Enums.StadiumSector;
import View.Printer;
import org.graalvm.nativeimage.c.function.CMacroInfo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements ID, Printer {
    private final LocalDateTime ticketCreationTime = LocalDateTime.now();
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm a");

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final DecimalFormat FORMATTER = new DecimalFormat("$#,##0.00");

    private ConcertHall concertHall;
    private int eventCode;
    private Timestamp eventTime;
    private boolean isPromo;
    private StadiumSector stadiumSector;

    @Min(value = 0, message = "Invalid backpack weight")
    private double backpackAllowedWeight;

    @Min(value = 0, message = "Invalid price")
    private BigDecimal price;


    public LocalDateTime getTicketCreationTime() {
        return ticketCreationTime;
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
        if (eventTime != null) {
            if (eventTime.isBefore(ticketCreationTime)) {
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
                "Creation time: " + ticketCreationTime + '\n' +
                "Concert hall: " + concertHall + '\n' +
                "Event code: " + eventCode + '\n' +
                "Event time: " + eventTime + '\n' +
                "Promo: " + isPromo + '\n' +
                "Stadium sector: " + stadiumSector + '\n' +
                "Backpack allowed weight: " + backpackAllowedWeight + '\n' +
                "Price: " + getPrice() + '\n' +
                "----------------";
    }

/*
    public void printTicket() {
        System.out.println("----------------");
        System.out.println("Model.Ticket ID: " + getTicketId());
        System.out.println("Model.Ticket created: " + DATE_TIME_FORMATTER.format(ticketCreationTime));

        System.out.println("Concert Hall: " + getConcertHall());
        System.out.println("Event Code: " + getEventCode());

        if (eventTime != null) {
            System.out.println("Event Time: " + TIME_FORMAT.format(getEventTime()));
        }
        else {
            System.out.println("Event Time: -");
        }

        if (isPromo()) {
            System.out.println("Promo: Yes");
        }
        else {
            System.out.println("Promo: No");
        }
        System.out.println("Stadium Sector: " + getStadiumSector());
        System.out.println("Allowed Backpack Weight: " + getbackpackAllowedWeight() + "kg");

        if (price != null) {
            System.out.println("Price: " + getPrice());
        }
        else {
            System.out.println("Price: -");
        }
        System.out.println("----------------");

    }

 */
}
