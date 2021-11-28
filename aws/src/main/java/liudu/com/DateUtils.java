package liudu.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
//import lombok.extern.log4j.Log4j;

/**
 * @author Liu Kedong
 */
//@Log4j
public class DateUtils {

  public static final String DATA_FORMAT = "yyyy-MM-dd";
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATA_FORMAT);
  public static final DateTimeFormatter DATETIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static String getOffsetDate(String endDate, int offset) {
    LocalDate d = LocalDate.parse(endDate, DATE_FORMATTER);
    d = d.plusDays(offset);

    return d.format(DATE_FORMATTER);
  }

  public static String getCurrDate() {
    ZonedDateTime current = ZonedDateTime.now(ZoneId.of("UTC"));
    return current.format(DATE_FORMATTER);
  }

  public static String getDateTimeString(Date d) {
    ZonedDateTime zdt = d.toInstant().atZone(ZoneId.of("UTC"));
    return zdt.format(DATETIME_FORMATTER);
  }

  public static String getDateFromTimestamp(Long timestamp) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT);
    dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));

    Date date = null;
    try {
      date = dateFormat.parse(dateFormat.format(timestamp));
    } catch (ParseException e) {
//      log.error("parse time error", e);
    }
    return dateFormat.format(date);
  }

  public static List<String> getDatesByOffset(String endDate, int offset) {
    List<String> dates = new ArrayList<>();
    for (int i = 0; i < offset; i++) {
      dates.add(getOffsetDate(endDate, -i));
    }
    if (offset == -1) {
      dates.add(endDate);
    }

    return dates;
  }


}
