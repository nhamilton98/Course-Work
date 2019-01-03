package com.hamilton.nathan.nhamiltonlab8;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int mMarkCount;
    private ArrayList<MarkerOptions> mMarkerOptions;
    private ArrayList<Marker> mMarkers;
    private int mCameraZoom = 10;

    private Button mMarkButton;
    private Button mChangeTypeButton;

    private static final int PERMISSION_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMarkerOptions = new ArrayList<>();
        mMarkers = new ArrayList<>();
        if (savedInstanceState != null)
        {
            mMarkerOptions = (ArrayList<MarkerOptions>) savedInstanceState.getSerializable("marker_options");
            mCameraZoom = savedInstanceState.getInt("zoom");
        }

        mChangeTypeButton = findViewById(R.id.change_map_type_button);
        mChangeTypeButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View view)
           {
               int mapType = mMap.getMapType();

               if (mapType == GoogleMap.MAP_TYPE_NORMAL)
                   mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
               else if (mapType == GoogleMap.MAP_TYPE_SATELLITE)
                   mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
               else
                   mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
           }
        });

        mMarkButton = findViewById(R.id.mark_button);
        mMarkButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View view)
           {
               mMarkCount++;

               Location location = getLocation();

               MarkerOptions options = new MarkerOptions();
               options.title("Mark " + mMarkCount);
               options.position(new LatLng(location.getLatitude(), location.getLongitude()));
               mMarkers.add(mMap.addMarker(options));
               mMarkerOptions.add(options);
           }
        });
        mMarkButton.setOnLongClickListener(new View.OnLongClickListener()
        {
           @Override
           public boolean onLongClick(View view)
           {
               if (mMarkers.isEmpty())
                   return false;

               Marker mark = mMarkers.get(mMarkers.size() - 1);
               mMarkers.remove(mark);
               mark.remove();

               return true;
           }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_about)
        {
            Toast.makeText(this, "Lab 8, Winter 2018, Nathan E Hamilton", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        bundle.putSerializable("marker_options", mMarkerOptions);
        bundle.putInt("zoom", Math.round(mMap.getCameraPosition().zoom));

        super.onSaveInstanceState(bundle);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        initializeMap();
    }

    public void initializeMap()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);
            return;
        }

        mMap.setMyLocationEnabled(true);

        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                Location location = getLocation();

                if (location != null)
                    moveToLocation(location);
                else
                    Toast.makeText(getApplicationContext(), "No location. Try the zoom to button.", Toast.LENGTH_SHORT).show();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 200);

        if (mMarkerOptions.isEmpty() != true)
        {
            for (MarkerOptions options: mMarkerOptions)
                mMarkers.add(mMap.addMarker(options));
        }
    }

    public Location getLocation()
    {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = mMap.getMyLocation();
        String provider;

        if (location == null)
        {
            provider = getProvider(manager, Criteria.ACCURACY_FINE, manager.GPS_PROVIDER);

            try
            {
                location = manager.getLastKnownLocation(provider);
            }
            catch (SecurityException e)
            {
                Log.e("ERROR", "Security Exception: " + e.getMessage());
            }
        }

        if (location == null)
        {
            provider = getProvider(manager, Criteria.ACCURACY_COARSE, manager.NETWORK_PROVIDER);

            try
            {
                location = manager.getLastKnownLocation(provider);
            }
            catch (SecurityException e)
            {
                Log.e("ERROR", "Security Exception: " + e.getMessage());
            }
        }

        return location;
    }

    public void moveToLocation(Location location)
    {
        LatLng coordinates = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, mCameraZoom));
    }

    private String getProvider(LocationManager manager, int accuracy, String provider)
    {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(accuracy);

        String retProvider = manager.getBestProvider(criteria, false);
        if (retProvider == null)
            retProvider = provider;

        if (!manager.isProviderEnabled(retProvider))
        {
            Snackbar prompt = Snackbar.make(findViewById(R.id.map), "Location Provider Not Enabled.", Snackbar.LENGTH_LONG);
            prompt.setAction("Settings", new View.OnClickListener()
            {
               @Override
               public void onClick(View view)
               {
                   startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
               }
            });
            prompt.show();
        }

        return retProvider;
    }

    @Override
    public void onRequestPermissionsResult(int request, String[] permissions, int[] result)
    {
        if (request == PERMISSION_REQUEST)
        {
            if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED)
                initializeMap();
            else
                Toast.makeText(this, "Requires location services to work properly.", Toast.LENGTH_SHORT).show();
        }
    }
}
