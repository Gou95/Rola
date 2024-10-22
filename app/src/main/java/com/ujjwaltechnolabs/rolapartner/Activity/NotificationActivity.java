package com.ujjwaltechnolabs.rolapartner.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ujjwaltechnolabs.rolapartner.Adapter.NotificationAdapter;
import com.ujjwaltechnolabs.rolapartner.Model.NotificationResponse;
import com.ujjwaltechnolabs.rolapartner.R;
import com.ujjwaltechnolabs.rolapartner.databinding.ActivityNotificationBinding;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    ActivityNotificationBinding binding;

    ArrayList<NotificationResponse> list = new ArrayList<>();
    NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list.add(new NotificationResponse("khksdhkjhksdf","jhjkgjsgjksgdjkgfjkhsdgfjkgsdgfjkh"));
        list.add(new NotificationResponse("kjhdkhgkj","jhjkgjsgjksgdjkgfjkdfgfdghsdgfjkgsdgfjkh"));
        list.add(new NotificationResponse("retreretre","fdgggggggggggggggggggggggggggggggggggg"));
        list.add(new NotificationResponse("rrerefgdgfdg","fdddddddddgfdddddddddddddddddddddddd"));
        list.add(new NotificationResponse("kyifhgkhkdfghk","fdgdfgfdgggggggggggggggggggggggggggggggg"));
        list.add(new NotificationResponse("yiyugkrhkhkjhgk","fgdfgdfgfdgfgdfgfd"));

        adapter  =new NotificationAdapter(this,list);
        binding.recyclerViewNotification.setAdapter(adapter);
        binding.recyclerViewNotification.setLayoutManager(new LinearLayoutManager(this));

        binding.imgBtnArrowBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}