package nl.jordysipkema.mad.airports.Presenters;

import nl.jordysipkema.mad.airports.AirportDetailActivity;
import nl.jordysipkema.mad.airports.Models.Airport;
import nl.jordysipkema.mad.airports.Services.AirportService;

/**
 * Created by jordysipkema on 30/10/2017.
 */

public class AirportDetailPresenter {
    private AirportDetailActivity view;
    private AirportService airportService;

    public AirportDetailPresenter(AirportDetailActivity view) {
        this.view = view;
        this.airportService = new AirportService(view);
    }

    public Airport getAirport(String icao){
        return airportService.getAirport(icao);
    }

    public float getDistanceBetweenAirports(Airport a, Airport b){
        return a.getGeolocation().distanceTo(b.getGeolocation());
    }
}
