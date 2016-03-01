package com.fr.marcoucou.slidinguptaxifare;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private TextView textviewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TypefaceUtils.overrideFont(getApplicationContext(), Constants.fontName, Constants.fontFile);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.location_map);
        mapFragment.getMapAsync(this);
        setAutocompletionTextField();
        textviewTime = (TextView) findViewById(R.id.textViewTime);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/bakery.ttf");
        textviewTime.setTypeface(typeface);

    }


    public void setAutocompletionTextField(){
        //Autocompletion departure textfield
        PlaceAutocompleteFragment departureAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.departureTextField);
        departureAutocompleteFragment.setHint("Departure");
        departureAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Autocomplete", "Place: " + place.getLatLng());
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Autocomplete", "An error occurred: " + status);
            }
        });

        //Autocompletion arrival textfield
        PlaceAutocompleteFragment arrivalAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.arrivaltextField);

        arrivalAutocompleteFragment.setHint("Arrival");
        arrivalAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Autocomplete", "Place: " + place.getLatLng());
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Autocomplete", "An error occurred: " + status);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.location_map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng lognesPosition = new LatLng(48.8333,2.6167);
        googleMap.addMarker(new MarkerOptions().position(lognesPosition).title("Lognes city gang"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lognesPosition, 30));
    }

    public void calculateFareClick(View view){
        ApiCallAsyncTask apiCallAsyncTask = (ApiCallAsyncTask) new ApiCallAsyncTask(new ApiCallResponse() {
            @Override
            public void onApiCallCompleted(String response) {
                Log.d("response", response);
            }
        }, this);
        apiCallAsyncTask.execute("hello");
    }
}
