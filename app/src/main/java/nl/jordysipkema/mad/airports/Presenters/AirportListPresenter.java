package nl.jordysipkema.mad.airports.Presenters;

import android.database.Cursor;

import nl.jordysipkema.mad.airports.AirportsListActivity;
import nl.jordysipkema.mad.airports.Services.AirportService;

/**
 * Created by jordysipkema on 30/10/2017.
 */

public class AirportListPresenter {
    private AirportsListActivity view;
    private AirportService airportService;

    public AirportListPresenter(AirportsListActivity view){
        this.view = view;
        this.airportService = new AirportService(view);
    }

    public Cursor getAirports(){
        return this.airportService.getAirports();
    }

    public Cursor getAirportsByCountryCode(String countrycode){
        return this.airportService.getAirportsWhereCountryCode(countrycode);
    }

    public Cursor getAvailableCountries() {
        return this.airportService.getCountries();
    }
}
