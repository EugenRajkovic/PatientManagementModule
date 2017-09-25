package Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Eugen
 */
public final class Parser {
    
    private static SimpleDateFormat formatSQLDate = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    
    private static java.util.Date date;
    private static java.sql.Date sqlDate;

    public static java.sql.Date ParseUtilDateToSqlDate(Date date) throws Exception {

        if (date == null) {
            return null;
        } else {
            sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;
        }
    }

    public static java.util.Date ParseStringToDate(String input) throws ParseException {

        date = formatSQLDate.parse(input);
        return date;
    }

    public static java.util.Date ParseSQLDateToUtilDate(java.sql.Date sqlDate) {
       if (sqlDate == null) {
            return null;
        } else {
            date = new java.util.Date(sqlDate.getTime());
            return date;
        }
    }
}
