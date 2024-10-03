package Model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

public class Ticket {
    private static int ticketsCounter = 1;
    private final LocalDateTime ticketCreationTime = LocalDateTime.now();
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm a");

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final DecimalFormat FORMATTER = new DecimalFormat("$#,##0.00");

    private int ticketId = 0;
    private ConcertHall concertHall;
    private int eventCode;
    private Timestamp eventTime;
    private boolean isPromo;
    private StadiumSector stadiumSector;
    private double backpackAllowedWeight;
    private BigDecimal price;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getTicketCreationTime() {
        return ticketCreationTime;
    }

    public ConcertHall getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(ConcertHall concertHall) {
        this.concertHall = concertHall;
    }



    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        if (Integer.toString(eventCode).length() > 3) {
            System.out.println("Invalid event code");
            System.exit(0);
        }
        this.eventCode = eventCode;
    }

    public void setUnnamedEventCode() {

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

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public StadiumSector getStadiumSector() {
        return stadiumSector;
    }

    public void setStadiumSector(StadiumSector stadiumSector) {

        this.stadiumSector = stadiumSector;
    }

 /*   public void setUnnamedStadiumSector() {
        this.stadiumSector = '-';
    }
*/
    public double getbackpackAllowedWeight() {
        return backpackAllowedWeight;
    }

    public void setbackpackAllowedWeight(double backpackAllowedWeight) {
        if (backpackAllowedWeight < 0) {
            System.out.println("Invalid backpack weight.");
            System.exit(0);
        }
        this.backpackAllowedWeight = backpackAllowedWeight;
    }

    public String getPrice() {
        return FORMATTER.format(this.price);
    }

    public void setPrice(Integer price) {
        if (price < 0 )
        {
            System.out.println("Invalid price.");
            System.exit(0);
        }
        else {
            this.price = new BigDecimal(price);
        }
    }

    public Ticket() {
        this(ConcertHall.not_specified, 3, null, false, StadiumSector.not_specified, 0); // How to create a Ticket without Event Time, not using "null"??
    }

    public Ticket(ConcertHall concertHall, int eventCode, LocalDateTime eventTime) {
        this(concertHall, eventCode, eventTime, false, StadiumSector.not_specified, 0);
    }

    public Ticket(ConcertHall concertHall, int eventCode, LocalDateTime eventTime, boolean isPromo, StadiumSector stadiumSector, double backpackAllowedWeight) {
        ticketId = ticketsCounter;
        ticketsCounter++;

        setConcertHall(concertHall);
        setEventCode(eventCode);
        setEventTime(eventTime);
        setPromo(isPromo);
        setStadiumSector(stadiumSector);
        setbackpackAllowedWeight(backpackAllowedWeight);
    }
    
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
}
