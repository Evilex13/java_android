package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StartActivity extends Activity {
    private ArrayList<Contacts> contacts = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        createContacts();
        fillcontacts();
    }

    private void fillcontacts() {
        ListView listView = (ListView) findViewById(R.id.lv_contact);
        AllContactsView adapter = new AllContactsView(contacts, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createContacts() {
        for (int i = 0; i < 10; i++) {
            Contacts c = new Contacts();
            c.setIcon(false);
            c.setName("Иванов Иван Иванович " + i);
            c.setDate(i + "/5/" + (1979 + i));
            c.setEmail("ivan_ivanov" + i + "@ya.ru");
            c.setHomePhone("+7(343)1232312");
            c.setMobilePhone("+7(900)9092012");
            contacts.add(c);
        }
    }
}