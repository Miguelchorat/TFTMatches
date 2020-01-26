package com.example.tftmatches.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.tftmatches.R;
import com.example.tftmatches.model.Summoner;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MatchActivity extends AppCompatActivity {

    protected Summoner summoner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        Intent intent=getIntent();
        if (intent != null){
            summoner = (Summoner)intent.getSerializableExtra("Summoner");
        }

    }

    public Summoner getSummoner(){
        return summoner;
    }
}
