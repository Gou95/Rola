package com.ujjwaltechnolabs.rolapartner.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ujjwaltechnolabs.rolapartner.Adapter.ViewPagerAdapter;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.FragmentTrackingBinding;

public class TrackingFragment extends Fragment {


    FragmentTrackingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentTrackingBinding.inflate(inflater, container, false);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        binding.viewPager2.setAdapter(adapter);

        new TabLayoutMediator(binding.tab, binding.viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                           tab.setText("Driver");
                            break;
                        case 1:
                            tab.setText("Vehicle");
                            break;
                    }
                }).attach();


        binding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabTextColor(tab, R.color.green);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabTextColor(tab, R.color.black);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Optional: Handle reselection if needed
            }
        });
        if (binding.tab.getTabAt(0) != null) {
            binding.tab.getTabAt(0).select();
        }
        return binding.getRoot();
    }
    private void changeTabTextColor(TabLayout.Tab tab, int color) {
        if (tab.getCustomView() == null) {
            TextView textView = new TextView(getContext());
            textView.setText(tab.getText());
            textView.setTextColor(getResources().getColor(color));
            tab.setCustomView(textView);
        } else {
            ((TextView) tab.getCustomView()).setTextColor(getResources().getColor(color));
        }
    }

//    public void replacementFragment(Fragment fragment){
//        FragmentManager fragmentManager = getChildFragmentManager();
//      fragmentManager.beginTransaction()
//              .replace(R.id.tracking_framlayout,fragment)
//              .commit();
//
//
//    }
}