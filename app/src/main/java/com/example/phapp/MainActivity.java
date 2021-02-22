package com.example.phapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.phapp.fragments.CallFragment;
import com.example.phapp.fragments.ContactFragment;
import com.example.phapp.fragments.InfoFragment;
import com.example.phapp.fragments.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private GoogleMap mMap;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView= findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;
                if (getIntent().getClass() != null) {
                    Log.i("MainActivity", getIntent().getClass().toString());
                }

                if (savedInstanceState != null) {
                    fragment = fragmentManager.findFragmentById(menuItem.getItemId());
                }
                switch (menuItem.getItemId()) {
                    case R.id.action_location:
                        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                        Intent chooser = Intent.createChooser(intent, "Launch Maps");
                        startActivity(chooser);
                        //SupportMapFragment mapFragment = SupportMapFragment.newInstance();
                        //fragmentManager.beginTransaction().replace(R.id.flContainer, mapFragment).commit();
                        //fragment = SupportMapFragment.newInstance();
                        //fragment = (SupportMapFragment)fragmentManager.findFragmentById(R.id.map);
                        //fragment.getMapAsync(this);
                        //fragment = new MapFragment();
                        //ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
                        break;
                    case R.id.action_contact:
                        fragment = new ContactFragment();
                        break;
                    case R.id.action_call:
                        fragment = new CallFragment();
                        break;
                    case R.id.action_info:
                        fragment = new InfoFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment)
                        .addToBackStack("frag").commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_contact);
    }
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}