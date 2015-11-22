package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactView extends BaseAdapter{
    private ArrayList<Contacts> contacts;
    private Context context;

    public ContactView(ArrayList<Contacts> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contacts c = contacts.get(position);
        View view = convertView;

        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_info, null);
        }

        TextView tvName = (TextView) view.findViewById(R.id.textView2);
        tvName.setText(c.getName());

        TextView tvDate = (TextView) view.findViewById(R.id.textView10);
        tvName.setText(c.getDate());

        TextView tvEmail = (TextView) view.findViewById(R.id.textView3);
        tvName.setText(c.getEmail());

        TextView tvHomePhone = (TextView) view.findViewById(R.id.textView6);
        tvHomePhone.setText(c.getHomePhone());

        TextView tvMobile = (TextView) view.findViewById(R.id.textView8);
        tvMobile.setText(c.getMobilePhone());

        return view;
    }
}
