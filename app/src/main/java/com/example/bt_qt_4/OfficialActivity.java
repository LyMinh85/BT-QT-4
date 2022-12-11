package com.example.bt_qt_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OfficialActivity extends AppCompatActivity {
    private TextView nameTV, locationTV, addressTV, phoneTV, emailTV, urlTV, txtBut1, txtBut2, txtBut3, txtBut4;
    ImageButton facebookButton, googleButton, twitterButton, youtubeButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);

        Official official = (Official) getIntent().getSerializableExtra("official");

        locationTV = findViewById(R.id.txt_location);
        nameTV = findViewById(R.id.txt_name);
        addressTV = findViewById(R.id.txt_address);
        phoneTV = findViewById(R.id.txt_phone);
        emailTV = findViewById(R.id.txt_email);
        urlTV = findViewById(R.id.txt_url);
        imageView = findViewById(R.id.imageButton1);
        LinearLayout backgroundLayout = findViewById(R.id.official_background);

        locationTV.setText(official.getAddress().toString());
        nameTV.setText(official.getName() + official.getParty());
        addressTV.setText(official.getAddress().toString());
        phoneTV.setText(official.getPhone());
        emailTV.setText(official.getEmail());
        urlTV.setText(official.getUrl());

        if (!official.getPhotoUrl().isEmpty()) {
            Log.d("PhotoUrl", official.getPhotoUrl());
            Picasso.get().load(official.getPhotoUrl()).error(R.drawable.avatar).into(imageView);
        }

        Log.d("Party", official.getParty());
        if (official.getParty().equalsIgnoreCase("(Republican Party)")) {
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.republican));
        } else if (official.getParty().equalsIgnoreCase("(Democratic Party)")) {
            backgroundLayout.setBackgroundColor(getResources().getColor(R.color.democratic));
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(OfficialActivity.this, PhotoActivity.class);
                photoIntent.putExtra("official", official);
                startActivity(photoIntent);
            }
        });

    googleButton =(ImageButton)

    findViewById(R.id.imageButton3);
        googleButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + official.getChannel("Google")));
        startActivity(browserIntent);
    }
    });
    facebookButton =(ImageButton)

    findViewById(R.id.imageButton5);
        facebookButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/" + official.getChannel("Facebook")));
        startActivity(browserIntent);
    }
    });
    twitterButton =(ImageButton)

    findViewById(R.id.imageButton4);
        twitterButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + official.getChannel("Twitter")));
        startActivity(browserIntent);
    }
    });
    youtubeButton =(ImageButton)

    findViewById(R.id.imageButton2);
        youtubeButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/" + official.getChannel("Youtube")));
        startActivity(browserIntent);
    }
    });

    //Open website
    txtBut4 =(TextView)

    findViewById(R.id.txt_url);
        txtBut4.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(official.getUrl()));
        startActivity(browserIntent);
    }
    });
    // Open Dial
    txtBut1 =(TextView)

    findViewById(R.id.txt_phone);
        txtBut1.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Uri number = Uri.parse("tel:" + official.getPhone());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }
    });
    // Show address in map
//        txtBut2 = (TextView) findViewById(R.id.txt_address);
//        txtBut2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Map point based on address
//                Uri location = Uri.parse("geo:0,0?q=" + officials.getAddress().getLine1() + officials.getAddress().getLine2() + officials.getAddress().getCity() + ", " + officials.getAddress().getState() + " " + officials.getAddress().getZip());
//                // Or map point based on latitude/longitude
//                // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
//                startActivity(mapIntent);
//            }
//        });

    // Send email
    txtBut3 =(TextView)

    findViewById(R.id.txt_email);
        txtBut3.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{official.getEmail()}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
        // You can also attach multiple items by passing an ArrayList of Uris
        startActivity(emailIntent);
    }
    });
}
}