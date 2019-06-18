package org.rif.lumino.explorer.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {

    private static final String UTC_ZONE_ID = "UTC";

    public static long getMinutesDifferenceUTC(Date fromDateParam, LocalDateTime toDateTimeParam) {
        Date fromDate = fromDateParam;
        ZonedDateTime zonedDateTime = fromDate.toInstant().atZone(ZoneId.of(UTC_ZONE_ID));
        LocalDateTime fromDateLocal = zonedDateTime.toLocalDateTime();

        long minutes = ChronoUnit.MINUTES.between(fromDateLocal, toDateTimeParam);

        return minutes;
    }

}
