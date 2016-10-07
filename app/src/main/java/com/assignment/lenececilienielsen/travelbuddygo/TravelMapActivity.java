package com.assignment.lenececilienielsen.travelbuddygo;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class TravelMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travelmaplayout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void goToHistory(View view) {
        Intent intent = new Intent(TravelMapActivity.this, CompassActivity.class);
        startActivity(intent);
    }
        public void onSearch(View view)
        {
            EditText location_tf = (EditText)findViewById(R.id.city_searcher);
            String location = location_tf.getText().toString();
            List<Address> addressList = null;
            if(location != null || !location.equals(""))
            {
                Geocoder geocoder = new Geocoder(this);
                try {
                    addressList = geocoder.getFromLocationName(location , 1);


                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude() , address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            }
        }

    public void goToCompass(View view) {
        Intent intent = new Intent(TravelMapActivity.this, CompassActivity.class);
        startActivity(intent);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /**
         * Tutorial used for the info window functionality.
         * URL: https://www.youtube.com/watch?v=g7rvqxn8SLg
         */
        if(mMap != null) {
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                public View getInfoWindow(Marker arg0) {
                    return null;
                }

                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.custom_info_contents, null);

                    TextView tvLocality = (TextView) v.findViewById(R.id.tv_locality);
                    TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
                    TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
                    TextView tvSnippit = (TextView) v.findViewById(R.id.tv_snippet);

                    LatLng ll = marker.getPosition();
                    tvLocality.setText(marker.getTitle());
                    tvLat.setText("Latitude: " + ll.latitude);
                    tvLng.setText("Longitude: " + ll.longitude);
                    tvSnippit.setText(marker.getSnippet());
                    return v;
                }
            });
        }

        /**
         * Tutorial used for the info window functionality.
         * URL: https://developers.google.com/maps/documentation/android-api/marker
         */
        LatLng SYDNEY = new LatLng(-33.865081, 151.203843);
        LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
        LatLng NEWYORK = new LatLng(40.714778, -74.011017);
        LatLng PARIS = new LatLng(48.856353, 2.352705);
        LatLng NTNU = new LatLng(60.788292, 10.680422);

        Marker mSYDNEY;
        Marker mBrisbane;
        Marker mNewYork;
        Marker mParis;
        Marker mNTNU;

        mSYDNEY = mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney")
                .snippet("Facts about Sydney here."));
        mSYDNEY.setTag(0);

        mBrisbane = mMap.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane")
                .snippet("Facts about Brisbane here."));
        mBrisbane.setTag(0);

        mNewYork = mMap.addMarker(new MarkerOptions()
                .position(NEWYORK)
                .title("New York")
                .snippet("Facts about New York here."));
        mNewYork.setTag(0);

        mParis = mMap.addMarker(new MarkerOptions()
                .position(PARIS)
                .title("Paris")
                .snippet("Facts about Paris here."));
        mParis.setTag(0);

        mNTNU = mMap.addMarker(new MarkerOptions()
                .position(NTNU)
                .title("NTNU Gjøvik")
                .snippet("Facts about NTNU here."));
        mNTNU.setTag(0);



        mMap.moveCamera(CameraUpdateFactory.newLatLng(NTNU));
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // Adds a small button that finds your current location.
        mMap.setMyLocationEnabled(true);*/
    }
}
