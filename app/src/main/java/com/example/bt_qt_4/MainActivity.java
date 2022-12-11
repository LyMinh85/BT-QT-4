package com.example.bt_qt_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvOfficials;
    private final List<Official> officials = new ArrayList<>();
    private final ArrayList<Office> offices = new ArrayList<>();
    private final Address searchAddress = new Address();
    private OfficialsAdapter officialsAdapter;
    private String zipcode = "72959";
    TextView searchAddressTV;


//    State	    State   Capital	    ZIP Range
//    Arkansas (AR)	    Little Rock	71601 to 72959
//    California (CA)	Sacramento	90001 to 96162
//    Colorado (CO)	    Denver	    80001 to 81658
//    Connecticut (CT)	Hartford	06001 to 06928
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getStringExtra("zipcode") != null) {
            zipcode = getIntent().getStringExtra("zipcode");
        }

        //Request data
        GetOfficialsTask getOfficialsTask = new GetOfficialsTask(this::getData);
        getOfficialsTask.execute(zipcode);

        rcvOfficials = findViewById(R.id.rcv_officials);
        searchAddressTV = findViewById(R.id.search_address);

        searchAddressTV.setText(zipcode);

        officialsAdapter = new OfficialsAdapter(this, offices, officials);
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

    private static class GetOfficialsTask extends AsyncTask<String, String, String> {
        public interface AsyncResponse {
            void processFinish(String output);
        }

        public AsyncResponse delegate = null;

        public GetOfficialsTask(AsyncResponse delegate) {
            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(String... strings) {
            String zipcode = strings[0];
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.scheme("https")
                    .authority("www.googleapis.com")
                    .appendPath("civicinfo")
                    .appendPath("v2")
                    .appendPath("representatives")
                    .appendQueryParameter("address", zipcode)
                    .appendQueryParameter("key", "AIzaSyCyQostDmLeQvf-Q5O0wCb21D6EPswr-i4");
            try {
                URL url = new URL(uriBuilder.build().toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            delegate.processFinish(response);
        }
    }

    // Create option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_option_menu, menu);
        return true;
    }

    // Option menu select
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.search) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Enter a city, state or a Zip Code: ");
            EditText editText = new EditText(this);
            alert.setView(editText);

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    zipcode = editText.getText().toString();
                    Log.d("zipcode", zipcode);
                    Toast.makeText(MainActivity.this, zipcode, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtra("zipcode", zipcode);
                    startActivity(intent);
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.show();
        }
        return true;
    }

    public void getData(String response) {
        try {
            JSONObject responseJson = new JSONObject(response);
            JSONObject normalizedInputJson = responseJson.getJSONObject("normalizedInput");
            JSONArray officesJson = responseJson.getJSONArray("offices");
            JSONArray officialsJson = responseJson.getJSONArray("officials");

            // Get address
            searchAddress.setCity(normalizedInputJson.getString("city"));
            searchAddress.setState(normalizedInputJson.getString("state"));
            searchAddress.setZip(normalizedInputJson.getString("zip"));

            // Get offices
            for (int i = 0; i < officesJson.length(); i++) {
                JSONObject object = officesJson.getJSONObject(i);
                String officeName = object.getString("name");
                ArrayList<Integer> officialIndices = new ArrayList<>();

                // Get office
                JSONArray listObject = object.getJSONArray("officialIndices");
                for (int j = 0; j < listObject.length(); j++) {
                    int value = listObject.getInt(j);
                    officialIndices.add(value);
                }

                // Add to list office
                Office office = new Office(officeName, officialIndices);
                offices.add(office);

                //Get official in office
                for (int j : office.getOfficialIndices()) {
                    JSONObject officialObject = officialsJson.getJSONObject(j);

                    // Define
                    String name = officialObject.getString("name");
                    Address address = new Address();
                    String party = "";
                    String phone = "";
                    String url = "";
                    String email = "";
                    String photoUrl = "";
                    ArrayList<Channel> channels = new ArrayList<>();

                    // Try to get data
                    try {
                        address = new Address(officialObject.getJSONArray("address").getJSONObject(0));
                    } catch (JSONException ignored){}
                    try {
                        party = officialObject.getString("party");
                    } catch (JSONException ignored){}
                    try {
                        phone = officialObject.getJSONArray("phones").getString(0);
                    } catch (JSONException ignored){}
                    try {
                        url = officialObject.getJSONArray("urls").getString(0);
                    } catch (JSONException ignored){}
                    try {
                        email = officialObject.getJSONArray("emails").getString(0);
                    } catch (JSONException ignored){}
                    try {
                        photoUrl = officialObject.getString("photoUrl");
                    } catch (JSONException ignored){}


                    // Try to get channels
                    try {
                        JSONArray channelJsonArray = officialObject.getJSONArray("channels");
                        for (int z = 0; z < channelJsonArray.length(); z++) {
                            JSONObject channelJson = channelJsonArray.getJSONObject(z);
                            String type = channelJson.getString("type");
                            String id = channelJson.getString("id");
                            channels.add(new Channel(type, id));
                        }
                    } catch (JSONException ignored){}

                    // Add to list officials
                    Official official = new Official(name, office.getName(), party, address, phone, email, url, photoUrl, channels);
                    Log.d("official", official.toString());
                    officials.add(official);
                }
            }
            officialsAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}