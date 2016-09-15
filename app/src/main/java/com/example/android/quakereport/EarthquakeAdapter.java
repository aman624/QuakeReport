package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by abakshi on 9/14/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

    int mLayoutResId;
    Context mContext;

    public EarthquakeAdapter(Context context, int resource, List<Earthquake> earthquakes) {
        super(context, resource, earthquakes);
        mContext = context;
        mLayoutResId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Earthquake earthquake = getItem(position);
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(mContext).inflate(mLayoutResId, parent, false);
        }

        TextView magnitudeView = (TextView) itemView.findViewById(R.id.magnitude);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(ContextCompat.getColor(getContext(), magnitudeColor));
        magnitudeView.setText(formatMagnitude(earthquake.getMagnitude()));

        String location = earthquake.getLocation();
        String locationOffset;
        String primaryLocation;
        if (location.contains(LOCATION_SEPARATOR)) {
            int index = location.indexOf(LOCATION_SEPARATOR) + 4;
            primaryLocation = location.substring(index);
            locationOffset = location.substring(0, index);
        } else {
            primaryLocation = location;
            locationOffset = mContext.getResources().getString(R.string.near_the);
        }

        TextView locationView = (TextView) itemView.findViewById(R.id.primary_location);
        locationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) itemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        Date date = new Date(earthquake.getTime());
        TextView dateView = (TextView) itemView.findViewById(R.id.date);
        dateView.setText(formatDate(date));

        TextView timeView = (TextView) itemView.findViewById(R.id.time);
        timeView.setText(formatTime(date));
        return itemView;
    }

    private int getMagnitudeColor(double mag) {
        int magnitude = (int)Math.floor(mag);
        switch (magnitude) {
            case 0:
            case 1:
                return R.color.magnitude1;
            case 2:
                return R.color.magnitude2;
            case 3:
                return R.color.magnitude3;
            case 4:
                return R.color.magnitude4;
            case 5:
                return R.color.magnitude5;
            case 6:
                return R.color.magnitude6;
            case 7:
                return R.color.magnitude7;
            case 8:
                return R.color.magnitude8;
            case 9:
                return R.color.magnitude9;
            default:
                return R.color.magnitude10plus;
        }
    }

    private String formatMagnitude(double mag) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(mag);
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(date);
    }
}
