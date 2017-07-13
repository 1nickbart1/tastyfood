package by.tastyfood.utils;

import org.apache.log4j.Logger;

/**
 * Created by Nikolay on 05.06.2017.
 */
public class LongUtils {
    private static final Logger log = Logger.getLogger(LongUtils.class);

    public static Long getLongFromString(String sourceVal) {
        Long destVal = null;

        try {
            destVal = Long.parseLong(sourceVal);

        } catch (Exception e) {

            log.error(e);

        }

        return destVal;
    }
}
