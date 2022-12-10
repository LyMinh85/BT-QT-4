package com.example.bt_qt_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvOfficials;
    private List<Officials> officialsList;
    private OfficialsAdapter officialsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvOfficials = findViewById(R.id.rcv_officials);
        officialsList = new ArrayList<>();

        Officials officials1 = new Officials("President of the United States", "Joseph Robinette Biden Jr.","Democratic");
        Officials officials2 = new Officials("Vice President of the United States", "Kamala Harris","Democratic");
        Officials officials3 = new Officials("Secretary Of the United States", "Antony Blinken","Democratic");
        Officials officials4 = new Officials("Secretary Of The Treasury of the United States", "Janet Yellen","Democratic");
        Officials officials5 = new Officials("Secretary Of The Interior of the United States", "Deb Haaland","Democratic");
        Officials officials6 = new Officials("Secretary Of Agriculture of the United States", "Tom Vilsack","Democratic");

        officialsList.add(officials1);
        officialsList.add(officials2);
        officialsList.add(officials3);
        officialsList.add(officials4);
        officialsList.add(officials5);
        officialsList.add(officials6);

        officialsAdapter = new OfficialsAdapter(this, officialsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvOfficials.setLayoutManager(linearLayoutManager);
        rcvOfficials.setAdapter(officialsAdapter);

        rcvOfficials.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Intent intent = new Intent(MainActivity.this, OfficialActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}