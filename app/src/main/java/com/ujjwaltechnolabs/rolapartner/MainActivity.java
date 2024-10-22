package com.ujjwaltechnolabs.rolapartner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ujjwaltechnolabs.rolapartner.Fragment.FinanceFragment;
import com.ujjwaltechnolabs.rolapartner.Fragment.HomeFragment;
import com.ujjwaltechnolabs.rolapartner.Fragment.TrackingFragment;
import com.ujjwaltechnolabs.rolapartner.Fragment.TripFragment;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityMainBinding;

import me.ertugrul.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //by default home fragment open
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();
        //BottomNavigation
        bottomNavigation();
    }
    private void bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int i) {
                Log.d(TAG, "onItemSelect: "+i);
                // Update icons based on selected item
                if (i == 0) {

                    Log.d(TAG, "onItemSelect:INSIDE "+i);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new HomeFragment())
                            .commit();
                } else if (i == 1) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new FinanceFragment(), "fragmentTag")
                            .commit();
                } else if (i == 2) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new TrackingFragment(), "fragmentTag")
                            .commit();
                } else if (i == 3) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new TripFragment(), "fragmentTag")
                            .commit();
                }
            }
        });
    }

}