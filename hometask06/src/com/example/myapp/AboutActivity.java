package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class AboutActivity extends Activity {
    private ArrayList<String> list = new ArrayList<>();
    private final int CAMERA_RESULT = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_about);
        ImageView ivIcon = (ImageView) findViewById(R.id.imageView);
        ivIcon.setImageResource(R.drawable.annonym);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = bundle.getStringArrayList("EXTRA_DATA");
        fillcontacts();
        ImageButton ibCall = (ImageButton) findViewById(R.id.imageButton_call);
        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + list.get(4)));
                startActivity(dialIntent);
            }
        });
        ImageButton ibEmail = (ImageButton) findViewById(R.id.imageButton_email);
        ibEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + list.get(2)));
                startActivity(mailIntent);
            }
        });
        ImageButton ibCamera = (ImageButton) findViewById(R.id.imageButton_camera);
        ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_RESULT);
            }
        });


    }

    private void fillcontacts() {
        ListView listView = (ListView) findViewById(R.id.lv_info);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_RESULT){
            Bitmap thumbnail = (Bitmap)data.getExtras().get("data");
            ImageView ivCamera = (ImageView) findViewById(R.id.imageView);
            ivCamera.setImageBitmap(thumbnail);
        }
    }
}