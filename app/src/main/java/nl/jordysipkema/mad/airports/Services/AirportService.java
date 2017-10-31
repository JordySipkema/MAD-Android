package nl.jordysipkema.mad.airports.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import nl.jordysipkema.mad.airports.Contracts.AirportReaderContract;
import nl.jordysipkema.mad.airports.Models.Airport;

public class AirportService extends SQLiteAssetHelper {

    private static final String DB_NAME = "airports.sqlite";
    private static final int DB_VERSION = 2;

    public AirportService(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public Airport getAirport(String icao){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(String.format("Select * from airports WHERE %s = '%s'",
                AirportReaderContract.AirportEntry.COLUMN_NAME_ICAO, icao), null);
        c.moveToFirst();
        return new Airport(c);
    }

    public Cursor getAirports(){
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT rowid _id, * FROM airports";
        return db.rawQuery(query, null);
    }

    public Cursor getCountries(){
        SQLiteDatabase db = getReadableDatabase();

        String select = "rowid _id, " + AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY;
        String groupBy = AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY;
        String orderBy = AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY + " ASC";

        String query = String.format("SELECT %s FROM airports GROUP BY %s ORDER BY %s",
                select, groupBy, orderBy);
        return db.rawQuery(query, null);
    }

    public Cursor getAirportsWhereCountryCode(String country_code) {
        SQLiteDatabase db = getReadableDatabase();

        String where = String.format("%s = '%s'",
                AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY, country_code);

        String query = String.format("SELECT rowid _id, * FROM airports WHERE %s ORDER BY %s",
                where, AirportReaderContract.AirportEntry.COLUMN_NAME_NAME);
        return db.rawQuery(query, null);
    }
}
