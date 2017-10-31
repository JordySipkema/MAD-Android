package nl.jordysipkema.mad.airports;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;

import nl.jordysipkema.mad.airports.Models.Airport;
import nl.jordysipkema.mad.airports.Presenters.AirportDetailPresenter;

public class AirportDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String BASE_AIRPORT_ICAO = "EHAM";
    private AirportDetailPresenter presenter;
    private GoogleMap googleMap;
    private Airport base_airport;
    private Airport airport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_details);

        this.presenter = new AirportDetailPresenter(this);
        Intent intent = getIntent();

        this.base_airport = presenter.getAirport(BASE_AIRPORT_ICAO);
        this.airport = presenter.getAirport(intent.getStringExtra("icao"));
        float distance = presenter.getDistanceBetweenAirports(this.base_airport, this.airport);

        TextView airport_title = (TextView) findViewById(R.id.airport_name_label);
        TextView airport_location = (TextView) findViewById(R.id.airport_location_label);
        TextView distance_label = (TextView) findViewById(R.id.distance_from_label);

        airport_title.setText(this.airport.getName());
        airport_location.setText(
                String.format(Locale.ENGLISH, "%s, (%s)", airport.getLocation(), airport.getCountry()));
        distance_label.setText(String.format(Locale.ENGLISH, "Distance from %s: %.0fkm",
                base_airport.getIcao(), distance/1000));
        final MapView mapview = (MapView) findViewById(R.id.airport_detail_map);
        mapview.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final MapView mapview = (MapView) findViewById(R.id.airport_detail_map);
        mapview.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng base_airport_location = new LatLng(
                this.base_airport.getGeolocation().getLatitude(),
                this.base_airport.getGeolocation().getLongitude());
        LatLng detail_airport_location = new LatLng(
                this.airport.getGeolocation().getLatitude(),
                this.airport.getGeolocation().getLongitude());

        Marker base_marker = this.googleMap.addMarker(new MarkerOptions()
                .position(base_airport_location)
                .title(base_airport.getName())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        Marker detail_marker = this.googleMap.addMarker(new MarkerOptions()
                .position(detail_airport_location)
                .title(airport.getName())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        this.googleMap.addPolyline(new PolylineOptions()
                .add(base_airport_location, detail_airport_location)
                .width(5)
                .color(Color.YELLOW)).setGeodesic(true);

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(detail_airport_location));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(base_marker.getPosition());
        builder.include(detail_marker.getPosition());
        final LatLngBounds bounds = builder.build();

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
            }
        });
    }
}
