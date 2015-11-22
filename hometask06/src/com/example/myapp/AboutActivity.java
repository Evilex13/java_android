package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class AboutActivity extends Activity {
    private ArrayList<Contacts> contacts = new ArrayList<>();
    private ContactView adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_about);
        ImageView ivIcon = (ImageView) findViewById(R.id.imageView);
        ivIcon.setImageResource(R.drawable.annonym);

    }

    private void fillcontacts() {
        ListView listView = (ListView) findViewById(R.id.lv_contact);
        adapter = new ContactView(contacts, this);
        listView.setAdapter(adapter);
    }
}