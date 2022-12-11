package com.example.bt_qt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        LinearLayout backgroundLayout = findViewById(R.id.photo_background);
        TextView zipcodeTV = findViewById(R.id.photo_zipcode);
        TextView positionTV = findViewById(R.id.photo_position);
        TextView nameTV = findViewById(R.id.photo_name);
        ImageView imageView = findViewById(R.id.imageButton1);

        Official official = (Official) getIntent().getSerializableExtra("official");

        if (!official.getPhotoUrl().isEmpty()) {
            Log.d("PhotoUrl", official.getPhotoUrl());
            Picasso.get().load(official.getPhotoUrl()).error(R.drawable.avatar).into(imageView);
        }

        zipcodeTV.setText(official.getAddress().toString());
        positionTV.setText(official.getPosition());
        nameTV.setText(official.getName());
        if (official.getParty().equalsIgnoreCase("(Republican Party)")) {
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.republican));
        } else if(official.getParty().equalsIgnoreCase("(Democratic Party)")){
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.democratic));
        }

    }
}