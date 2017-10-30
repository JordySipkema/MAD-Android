package nl.jordysipkema.mad.airports.Contracts;

import android.provider.BaseColumns;

/**
 * Created by jordysipkema on 30/10/2017.
 */

public final class AirportReaderContract {
    private AirportReaderContract() { }

    /* Inner class that defines the table contents */
    public static class AirportEntry implements BaseColumns {
        public static final String TABLE_NAME = "airports";
        public static final String COLUMN_NAME_ICAO = "icao";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LOCATION = "municipality";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_COUNTRY = "iso_country";
    }
}
