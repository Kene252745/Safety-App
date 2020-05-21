package com.example.covid_19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowDetails extends AppCompatActivity {

    private int country;
    TextView showCountry, showCases, showRecovered, showCritical, showActive, showNew, showDeath, showNewDeaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        Intent intent = getIntent();
        country = intent.getIntExtra("position", 0);
        getSupportActionBar().setTitle(NationCounts.nationList.get(country).getCountry() + "'s Analysis");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        showCountry = findViewById(R.id.showCountry);
        showCases = findViewById(R.id.showCases);
        showRecovered = findViewById(R.id.showRecovered);
        showCritical = findViewById(R.id.showCritical);
        showActive = findViewById(R.id.showActive);
        showDeath = findViewById(R.id.showDeath);
        showNewDeaths = findViewById(R.id.showNewDeaths);
        showNew = findViewById(R.id.showNew);



        showCountry.setText(NationCounts.nationList.get(country).getCountry());
        showCases.setText(NationCounts.nationList.get(country).getCases());
        showRecovered.setText(NationCounts.nationList.get(country).getRecovered());
        showCritical.setText(NationCounts.nationList.get(country).getCritical());
        showActive.setText(NationCounts.nationList.get(country).getActive());
        showDeath.setText(NationCounts.nationList.get(country).getTodayCases());
        showNewDeaths.setText(NationCounts.nationList.get(country).getDeaths());
        showNew.setText(NationCounts.nationList.get(country).getTodayDeaths());



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
