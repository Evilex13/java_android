package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllContactsView extends BaseAdapter {
    private ArrayList<Contacts> contacts;
    private Context context;
    static int i = 0;

    public AllContactsView(ArrayList<Contacts> contacts, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lv_contact, null);
        }

        if (!c.isIcon()){
            ImageView imIcon = (ImageView) view.findViewById(R.id.imageView2);
            imIcon.setImageResource(R.drawable.annonym_small);
        }
        TextView tvName = (TextView) view.findViewById(R.id.textView2);
        tvName.setText(c.getName());

        TextView tvHomePhone = (TextView) view.findViewById(R.id.textView6);
        tvHomePhone.setText(c.getHomePhone());

        TextView tvMobile = (TextView) view.findViewById(R.id.textView8);
        tvMobile.setText(c.getMobilePhone());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                ArrayList<String> list = new ArrayList<String>();
                Intent intent = new Intent(context, AboutActivity.class);
                list.add("ФИО: \n" + c.getName());
                list.add("Дата рождения: \n" + c.getDate());
                list.add("E-mail: \n" + c.getEmail());
                list.add("Домашний телефон: \n" + c.getHomePhone());
                list.add("Мобильный телефон: \n" + c.getMobilePhone());

                bundle.putStringArrayList("EXTRA_DATA", list);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        ImageButton ibCall = (ImageButton) view.findViewById(R.id.imageButton_call2);
        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + c.getMobilePhone()));
                context.startActivity(dialIntent);
            }
        });

        ImageButton imEmail = (ImageButton) view.findViewById(R.id.imageButton_email2);
        imEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + c.getEmail()));
                context.startActivity(mailIntent);
            }
        });


        return view;
    }
}
