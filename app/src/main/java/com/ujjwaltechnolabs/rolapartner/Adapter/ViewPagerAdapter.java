package com.ujjwaltechnolabs.rolapartner.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ujjwaltechnolabs.rolapartner.Fragment.DriverFragment;
import com.ujjwaltechnolabs.rolapartner.Fragment.VehicleFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DriverFragment(); // Return DriverFragment
            case 1:
                return new VehicleFragment(); // Return VehicleFragment
            default:
                return new DriverFragment(); // Default to DriverFragment
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
