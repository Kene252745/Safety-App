package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NationCounts extends AppCompatActivity {

    ListView countryList;
    EditText search_button;
    SimpleArcLoader simpleArcLoader;

    public static List<NationDisplay> nationList = new ArrayList<>();
    NationDisplay nationDisplay;
    NationAdapter nationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation_counts);

        countryList = findViewById(R.id.countryList);
        search_button = findViewById(R.id.search_button);
        simpleArcLoader = findViewById(R.id.countryLoader);

        getData();

    }

    private void getData() {
        String url = "https://corona.lmao.ninja/v2/countries";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String countryName = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String todayCases = jsonObject.getString("todayCases");
                                String deaths = jsonObject.getString("deaths");
                                String todayDeaths = jsonObject.getString("todayDeaths");
                                String recovered = jsonObject.getString("recovered");
                                String active = jsonObject.getString("active");
                                String critical = jsonObject.getString("critical");

                                JSONObject object = jsonObject.getJSONObject("countryInfo");
                                String flagUrl = object.getString("flag");

                                nationDisplay = new NationDisplay (flagUrl,countryName,cases,todayCases,deaths,todayDeaths,recovered,active,critical);
                                nationList.add(nationDisplay);


                            }

                            nationAdapter = new NationAdapter(NationCounts.this,nationList);
                            countryList.setAdapter(nationAdapter);
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);











                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        }


                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(NationCounts.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
