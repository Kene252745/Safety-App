package com.example.covid_19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView showCases, showRecovered, showCritical, showActive, showNew, showDeath, showNewDeaths, showAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showCases = findViewById(R.id.showCases);
        showRecovered = findViewById(R.id.showRecovered);
        showCritical = findViewById(R.id.showCritical);
        showActive = findViewById(R.id.showActive);
        showDeath = findViewById(R.id.showDeath);
        showNewDeaths = findViewById(R.id.showNewDeaths);
        showNew = findViewById(R.id.showNew);
        showAffectedCountries = findViewById(R.id.showAffectedCountries);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.status);
        pieChart = findViewById(R.id.piechart);

        getData();

    }

    private void getData() {
        String url = "https://corona.lmao.ninja/v2/all";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {

                            try {


                                JSONObject jsonObject = new JSONObject(response.toString());

                                showCases.setText(jsonObject.getString("cases"));
                                showRecovered.setText(jsonObject.getString("recovered"));
                                showCritical.setText(jsonObject.getString("critical"));
                                showActive.setText(jsonObject.getString("active"));
                                showNew.setText(jsonObject.getString("todayCases"));
                                showDeath.setText(jsonObject.getString("deaths"));
                                showNewDeaths.setText(jsonObject.getString("todayDeaths"));
                                showAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                                pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(showCases.getText().toString()), Color.parseColor("#FFA726")));
                                pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(showRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                                pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(showDeath.getText().toString()), Color.parseColor("#EF5350")));
                                pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(showActive.getText().toString()), Color.parseColor("#29B6F6")));
                                pieChart.startAnimation();

                                simpleArcLoader.stop();
                                simpleArcLoader.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);



                            } catch (JSONException e) {
                                e.printStackTrace();
                                simpleArcLoader.stop();
                                simpleArcLoader.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }



                        }
                    }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void location(View view) {
        startActivity(new Intent(getApplicationContext(),NationCounts.class));
    }

    public void call(View view) {
        startActivity(new Intent(getApplicationContext(),HelpLine.class));
    }
}
