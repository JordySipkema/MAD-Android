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

public class AirportService extends SQLiteAssetHelper {

    private static final String DB_NAME = "airports.sqlite";
    private static final int DB_VERSION = 2;

    public AirportService(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public Cursor getAirports(){
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT rowid _id, * FROM airports";
        return db.rawQuery(query, null);
    }

    public Cursor getCountries(){
        SQLiteDatabase db = getReadableDatabase();

        String select = "rowid _id " + AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY;
        String groupBy = AirportReaderContract.AirportEntry.COLUMN_NAME_NAME

        String query = "SELECT %s FROM airports WHERe";
        return db.rawQuery(query, null);
    }
}
