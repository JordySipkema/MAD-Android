package nl.jordysipkema.mad.airports;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

import nl.jordysipkema.mad.airports.Adapters.AirportCursorAdapter;
import nl.jordysipkema.mad.airports.Presenters.AirportListPresenter;

public class AirportsListActivity extends AppCompatActivity {

    private AirportListPresenter presenter;
    private ListView listView;
    private CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airports_list);

        this.presenter = new AirportListPresenter(this);
        setupList();

    }

    private void setupList() {
        this.listView = (ListView) findViewById(R.id.airportList);
        Cursor cursor = this.presenter.getAirports();
        AirportCursorAdapter adapter =
                new AirportCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, 0);
        this.listView.setAdapter(adapter);
    }


}
