package com.example.adityachondke.lastmile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class Booked extends Activity {
    private GoogleMap mMap;
    LocationManager locationManager;
    DatabaseReference databaseReference;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cycle");



    }


    public void OnbuttonClick(View v)

    {





        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }



            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {


                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    Toast.makeText(Booked.this,"Latitude" + latitude, Toast.LENGTH_SHORT).show();

                    Toast.makeText(Booked.this,"Longitude" + longitude, Toast.LENGTH_SHORT).show();

                    databaseReference.child("10007").child("cstatus").setValue(1);
                    databaseReference.child("10007").child("latitude").setValue(latitude);

                    databaseReference.child("10007").child("longitude").setValue(longitude);

                    Intent i = new Intent(Booked.this,Map.class);
                    startActivity(i);
                    finish();



                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });




        }
}



