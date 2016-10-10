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
        LatLng CAPETOWN = new LatLng(-33.959104, 18.416973);
        LatLng RIODEJANEIRO = new LatLng(-22.907193, -43.171657);
        LatLng BEIJING = new LatLng(39.905530, 116.335169);
        LatLng SAINTPETERSBURG = new LatLng(59.940840, 30.332349);

        Marker mSYDNEY;
        Marker mBrisbane;
        Marker mNewYork;
        Marker mParis;
        Marker mNTNU;
        Marker mBeijing;
        Marker mSaintPetersburg;
        Marker mCapeTown;
        Marker mRioDeJaneiro;

        mSYDNEY = mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney")
                .snippet("Sydney is the most populous city in Australia and Oceania. Located on Australia's east coast, the metropolis surrounds the world's largest natural harbour, and sprawls towards the Blue Mountains to the west. Residents of Sydney are known as \"Sydneysiders\". The Sydney area has been inhabited by indigenous Australians for at least 30,000 years."));
        mSYDNEY.setTag(0);

        mBrisbane = mMap.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane")
                .snippet("Brisbane is the third most populous city in Australia and has a population of 2.3 million. Brisbane is the fastest growing city in Australia. The Brisbane River flows through the city."));
        mBrisbane.setTag(0);

        mNewYork = mMap.addMarker(new MarkerOptions()
                .position(NEWYORK)
                .title("New York")
                .snippet("The City of New York, often called New York City, is the most populous city in the United States. Major tourist destinations include Times Square; Broadway theater productions; the Empire State Building; the Statue of Liberty; Ellis Island; theUnited Nations Headquarters; museums such as the Metropolitan Museum of Art; greenspaces such as Central Park and Washington Square Park; Rockefeller Center.\n"));
        mNewYork.setTag(0);

        mParis = mMap.addMarker(new MarkerOptions()
                .position(PARIS)
                .title("Paris")
                .snippet("Paris is the capital and the most populous city of France. Paris is located in northern central France. The centre of Paris contains the most visited monuments in the city, including the Notre Dame Cathedral and the Louvre as well as theSainte-Chapelle; Les Invalides, where the tomb of Napoleon is located, and the Eiffel Tower are located on the Left Bank south-west of the centre.\n"));
        mParis.setTag(0);

        mCapeTown = mMap.addMarker(new MarkerOptions()
                .position(CAPETOWN)
                .title("CapeTown")
                .snippet("Cape Town is a coastal city in South Africa. It is the second most populous urban area in South Africa, after Johannesburg. The city is famous for its harbour, for its natural setting in the Cape Floristic Region, as well as for such well-known landmarks as Table Mountainand Cape Point."));
        mCapeTown.setTag(0);

        mRioDeJaneiro = mMap.addMarker(new MarkerOptions()
                .position(RIODEJANEIRO)
                .title("RioDeJaneiro")
                .snippet("Rio is the second-most populous city in Brazil. Founded in 1565 by the Portuguese, the city was initially the seat of the Captaincy of Rio de Janeiro, a domain of the Portuguese Empire. Rio de Janeiro is one of the most visited cities in the Southern Hemisphere and is known for its natural settings, Carnival, samba, bossa nova, and balneario beaches such as Barra da Tijuca, Copacabana, Ipanema, and Leblon."));
        mRioDeJaneiro.setTag(0);

        mNTNU = mMap.addMarker(new MarkerOptions()
                .position(NTNU)
                .title("NTNU Gjøvik")
                .snippet("Gjøvik is a town in Oppland County in Norway, and Norway´s biggest lake “Mjøsa” is located here, there is also a department of the NTNU university located in Gjøvik."));
        mNTNU.setTag(0);

        mBeijing = mMap.addMarker(new MarkerOptions()
                .position(BEIJING)
                .title("Bejing")
                .snippet("Beijing (formerly romanized as Peking) is the capital of the People's Republic of China and the world's third most populous city proper. It is also the world's most populous capital city."));
        mBeijing.setTag(0);

        mSaintPetersburg = mMap.addMarker(new MarkerOptions()
                .position(SAINTPETERSBURG)
                .title("Saint Petersburg")
                .snippet("Saint Petersburg is Russia's second-largest city after Moscow, with five million inhabitants in 2012 and an important Russian port on the Baltic Sea."));
        mSaintPetersburg.setTag(0);


        /*
        mMap.moveCamera(CameraUpdateFactory.newLatLng(NTNU));
        mMap.setMyLocationEnabled(true);
        */

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // Adds a small button that finds your current location.
        mMap.setMyLocationEnabled(true);*/
    }
}
