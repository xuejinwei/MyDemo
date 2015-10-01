package com.example.designsupportlibraryuseing.otto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.designsupportlibraryuseing.R;
import com.squareup.otto.Produce;

import java.util.Random;

public class OttoActivity extends AppCompatActivity {

    public static final int DEFAULT_LAT = 40;
    public static final int DEFAULT_LON = -79;
    private static final int OFFSET = 2;
    private static final Random RANDOM = new Random();

    private static int lastLatitude = DEFAULT_LAT;
    private static int lastLongitude = DEFAULT_LON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otto);

        findViewById(R.id.clear_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tell everyone to clear their location history.
                BusProvider.getInstance().post(new LocationClearEvent());

                // Post new location event for the default location.
                lastLatitude = DEFAULT_LAT;
                lastLongitude = DEFAULT_LON;
                BusProvider.getInstance().post(new LocationChangedEvent(lastLatitude,lastLongitude));
            }
        });

        findViewById(R.id.move_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastLatitude += (RANDOM.nextInt() * OFFSET * 2) - OFFSET;
                lastLongitude += (RANDOM.nextInt() * OFFSET * 2) - OFFSET;
                BusProvider.getInstance().post(new LocationChangedEvent(lastLatitude,lastLongitude));

            }
        });
    }

    @Produce
    public LocationChangedEvent produceLocationEvent() {
        // Provide an initial value for location based on the last known position.
        return new LocationChangedEvent(lastLatitude, lastLongitude);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register ourselves so that we can provide the initial value.
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Always unregister when an object no longer should be on the bus.
        BusProvider.getInstance().unregister(this);
    }
}
