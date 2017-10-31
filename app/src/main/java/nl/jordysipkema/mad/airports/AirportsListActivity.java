package nl.jordysipkema.mad.airports;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.jordysipkema.mad.airports.Adapters.SegmentedListAdapter;
import nl.jordysipkema.mad.airports.Contracts.AirportReaderContract;
import nl.jordysipkema.mad.airports.Models.Airport;
import nl.jordysipkema.mad.airports.Presenters.AirportListPresenter;

public class AirportsListActivity extends AppCompatActivity {

    private AirportListPresenter presenter;
    private ExpandableListView listView;

    private List<String> segments;
    private HashMap<String, List<Airport>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airports_list);

        this.presenter = new AirportListPresenter(this);
        setupList();

    }

    private void setupList() {
        this.listView = (ExpandableListView) findViewById(R.id.airportList);

        this.segments = new ArrayList<>();
        this.data = new HashMap<>();

        Cursor segmentCursor = presenter.getAvailableCountries();
        segmentCursor.moveToFirst();
        while (segmentCursor.moveToNext()) {
            String iso_country = segmentCursor.getString(
                    segmentCursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY)
            );
            this.segments.add(iso_country);
            List<Airport> airports = new ArrayList<>();
            Cursor airportCursor = presenter.getAirportsByCountryCode(iso_country);
            while (airportCursor.moveToNext()) {
                Airport airport = new Airport(airportCursor);
                airports.add(airport);
            }
            this.data.put(iso_country, airports);
        }

        SegmentedListAdapter adapter =
                new SegmentedListAdapter(this, this.segments, this.data);
        this.listView.setAdapter(adapter);

        this.listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupIndex, int childIndex, long id) {
                Intent intent = new Intent(getApplicationContext(), AirportDetailActivity.class);
                Airport airport = data.get(segments.get(groupIndex)).get(childIndex);
                intent.putExtra("icao", airport.getIcao());
                startActivity(intent);
                return false;
            }
        });
    }


}
