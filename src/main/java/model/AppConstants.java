package main.java.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppConstants {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm a");

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    public static final DecimalFormat FORMATTER = new DecimalFormat("$#,##0.00");
    public static final LocalDateTime UNSPECIFIED_DATE_TIME = new Timestamp(0).toLocalDateTime();
}
