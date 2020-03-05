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
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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

public class Map  extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    LocationManager locationManager;
    DatabaseReference databaseReference;
    LatLng cycle1,cycle2,cycle3,cycle4;




    List<Cycle> cycleList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        databaseReference =FirebaseDatabase.getInstance().getReference("Cycle");
        cycleList=new ArrayList<>();
        cycle1=new LatLng(19.136113,72.914583);
        cycle2=new LatLng(19.13221,72.917777);
        cycle3=new LatLng(19.133642,72.917365);
        cycle4=new LatLng(19.136132,72.911536);



    }
    public  void onStart(){
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cycleList.clear();

                for (DataSnapshot cycleSnapshot : dataSnapshot.getChildren()) {
                    try {
                        Cycle cycle = cycleSnapshot.getValue(Cycle.class);
                        double m = cycle.getLatitude();
                        double n = cycle.getLongitude();
                        double k = cycle.getCycleid();
                        int l = cycle.getCstatus();

                        if (k == 1 && l == 1) cycle1 = new LatLng(m, n);

                        if (k == 2 && l == 0) cycle1 = new LatLng(0, 0);

                        if (k == 2 && l == 1) cycle2 = new LatLng(m, n);

                        if (k == 2 && l == 0) cycle2 = new LatLng(0, 0);

                        if (k == 3 && l == 1) cycle3 = new LatLng(m, n);

                        if (k == 3 && l == 0) cycle3 = new LatLng(0, 0);

                        if (k == 4 && l == 1) cycle4 = new LatLng(m, n);

                        if (k == 4 && l == 0) cycle4 = new LatLng(0, 0);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Map.this,"DATABASE ERROR",Toast.LENGTH_SHORT).show();
                    }

                }
            }


            public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(Map.this,"DATABASE ERROR",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }

        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    mMap.clear();
                    double latitude =location.getLatitude();
                    double longitude = location.getLongitude();

                    LatLng latLng=new LatLng(latitude,longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str="Your Location";
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17.2f));



                        mMap.addMarker(new MarkerOptions().position(cycle1).title("Cycle 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));


                        mMap.addMarker(new MarkerOptions().position(cycle2).title("Cycle 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));


                        mMap.addMarker(new MarkerOptions().position(cycle3).title("Cycle 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));



                        mMap.addMarker(new MarkerOptions().position(cycle4).title("Cycle 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }


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
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    mMap.clear();
                    double latitude =location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng=new LatLng(latitude,longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str=addressList.get(0).getLocality()+",";
                        str+= addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str).icon(BitmapDescriptorFactory.fromResource(R.drawable.you)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17.2f));

                        mMap.addMarker(new MarkerOptions().position(cycle1).title("Cycle 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));

                        mMap.addMarker(new MarkerOptions().position(cycle2).title("Cycle 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));

                        mMap.addMarker(new MarkerOptions().position(cycle3).title("Cycle 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));

                        mMap.addMarker(new MarkerOptions().position(cycle4).title("Cycle 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.cycleicon)));

                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }


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

    public void OnbuttonClick(View v)

    {
        Intent i = new Intent(this,Qrscanner.class);
        startActivity(i);
        finish();
    }

}

