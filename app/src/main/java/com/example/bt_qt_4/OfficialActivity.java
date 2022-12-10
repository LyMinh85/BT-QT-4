package com.example.bt_qt_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OfficialActivity extends AppCompatActivity {
    private TextView txt2, txt3, txt5, txt6, txt7, txt8, txtBut1, txtBut2, txtBut3, txtBut4;
    ImageButton facebookButton, googleButton, twitterButton, youtubeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);

        Chanels googlePlus = new Chanels("GooglePlus", "+whitehouse");
        Chanels facebook = new Chanels("Facebook", "whitehouse");
        Chanels twitter = new Chanels("Twitter", "+whitehouse");
        Chanels youTube = new Chanels("YouTube", "+whitehouse");
        Address address = new Address("The White House", "1600 Pennsylvania Avenue NW", "Washington", "DC", "20500");
        Officials officials = new Officials("Donald J. Trump", "President of the United States", "Republican", address, "(202) 456-1111", "email@address.com", "http://www.whitehouse.gov/");
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt1);
        txt5 = findViewById(R.id.txt4);
        txt6 = findViewById(R.id.txt6);
        txt7 = findViewById(R.id.txt8);
        txt8 = findViewById(R.id.txt10);
        txt2.setText(officials.getPosition() + System.getProperty("line.separator") + officials.getName() + System.getProperty("line.separator") + officials.getParty());
        txt5.setText(officials.getAddress().getLine1() + System.getProperty("line.separator") + officials.getAddress().getLine2() + System.getProperty("line.separator") + officials.getAddress().getCity() + ", " + officials.getAddress().getState() + " " + officials.getAddress().getZip());
        txt6.setText(officials.getPhones());
        txt7.setText(officials.getEmails());
        txt8.setText(officials.getUrls());

        googleButton = (ImageButton) findViewById(R.id.imageButton3);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + googlePlus.getId()));
                startActivity(browserIntent);
            }
        });
        facebookButton = (ImageButton) findViewById(R.id.imageButton5);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/" + facebook.getId()));
                startActivity(browserIntent);
            }
        });
        twitterButton = (ImageButton) findViewById(R.id.imageButton4);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twitter.getId()));
                startActivity(browserIntent);
            }
        });
        youtubeButton = (ImageButton) findViewById(R.id.imageButton2);
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/" + youTube.getId()));
                startActivity(browserIntent);
            }
        });

        //Open website
        txtBut4 = (TextView) findViewById(R.id.txt10);
        txtBut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(officials.getUrls()));
                startActivity(browserIntent);
            }
        });
        // Open Dial
        txtBut1 = (TextView) findViewById(R.id.txt6);
        txtBut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:" + officials.getPhones());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
        // Show address in map
        txtBut2 = (TextView) findViewById(R.id.txt4);
        txtBut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Map point based on address
                Uri location = Uri.parse("geo:0,0?q=" + officials.getAddress().getLine1() + officials.getAddress().getLine2() + officials.getAddress().getCity() + ", " + officials.getAddress().getState() + " " + officials.getAddress().getZip());
                // Or map point based on latitude/longitude
                // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });

        // Send email
        txtBut3 = (TextView) findViewById(R.id.txt8);
        txtBut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{officials.getEmails()}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                // You can also attach multiple items by passing an ArrayList of Uris
                startActivity(emailIntent);
            }
        });
    }
}