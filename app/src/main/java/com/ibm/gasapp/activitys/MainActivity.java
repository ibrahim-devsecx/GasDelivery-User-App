package com.ibm.gasapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ibm.gasapp.R;
import com.ibm.gasapp.fragments.MapsUsersFragment;
import com.ibm.gasapp.fragments.ProfileFragment;
import com.ibm.gasapp.fragments.RequestsFragment;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabMap;
    BottomNavigationView bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fabMap = findViewById(R.id.main_fab_map);
        bottomNavigationBar = findViewById(R.id.main_bottom_navigation_bar);

        openFragment(R.id.main_layout_container, new MapsUsersFragment());

        fabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(R.id.main_layout_container, new MapsUsersFragment());

            }
        });

        bottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_Profile:
                        openFragment(R.id.main_layout_container, ProfileFragment.newInstance());
                        return true;
                    case R.id.menu_request:
                        openFragment(R.id.main_layout_container, RequestsFragment.newInstance());
                        return true;
                }

                return false;
            }
        });
    }

    private void openFragment(int container, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}