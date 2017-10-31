package nl.jordysipkema.mad.airports.Models;

import android.database.Cursor;
import android.location.Location;

import nl.jordysipkema.mad.airports.Contracts.AirportReaderContract;

/**
 * Created by jordysipkema on 30/10/2017.
 */

public class Airport {
    private String icao;
    private String name;
    private Location geolocation;
    private String country;
    private String location;

    public Airport(Cursor airportCursor) {
        this.icao = airportCursor.getString(
                airportCursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_ICAO));
        this.name = airportCursor.getString(
                airportCursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_NAME));
        double lat = airportCursor.getDouble(
                airportCursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_LATITUDE));
        double lon = airportCursor.getDouble(
                airportCursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_LONGITUDE));
        this.country = airportCursor.getString(
                airportCursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY));
        this.location = airportCursor.getString(
                airportCursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_LOCATION));

        this.setGeolocation(lat, lon);
    }


    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(double latitude, double longitude) {
        final Location location = new Location("Airport Location");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        this.geolocation = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
