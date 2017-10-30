package nl.jordysipkema.mad.airports.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import nl.jordysipkema.mad.airports.Contracts.AirportReaderContract;

/**
 * Created by jordysipkema on 30/10/2017.
 */

public class AirportCursorAdapter extends ResourceCursorAdapter {

    public AirportCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String airport_name =
                cursor.getString(cursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_NAME));
        String airport_country =
                cursor.getString(cursor.getColumnIndex(AirportReaderContract.AirportEntry.COLUMN_NAME_COUNTRY));

        TextView title = (TextView) view.findViewById(android.R.id.text1);
        title.setText(airport_name);

        TextView description = (TextView) view.findViewById(android.R.id.text2);
        description.setText(airport_country);
    }
}
