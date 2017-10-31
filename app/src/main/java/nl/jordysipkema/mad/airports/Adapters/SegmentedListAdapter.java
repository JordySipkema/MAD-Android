package nl.jordysipkema.mad.airports.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import nl.jordysipkema.mad.airports.Models.Airport;

/**
 * Created by jordysipkema on 30/10/2017.
 */

public class SegmentedListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> segments;
    private HashMap<String, List<Airport>> data;

    public SegmentedListAdapter(Context context, List<String> listSegments, HashMap<String, List<Airport>> listData){
        this.context = context;
        this.segments = listSegments;
        this.data = listData;
    }


    @Override
    public int getGroupCount() {
        return this.segments.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.data.get(this.segments.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.segments.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.data.get(this.segments.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        String iso_country = (String) getGroup(i);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }

        TextView lblListGroup = convertView.findViewById(android.R.id.text1);
        lblListGroup.setTypeface(null, Typeface.BOLD);
        lblListGroup.setText(iso_country);

        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        final Airport airport = (Airport) getChild(i, i1);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);
        }

        TextView airport_name = convertView.findViewById(android.R.id.text1);
        TextView airport_desc = convertView.findViewById(android.R.id.text2);
        airport_name.setText(airport.getName());
        airport_desc.setText(airport.getLocation());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
