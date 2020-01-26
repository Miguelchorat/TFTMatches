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
import com.example.tftmatches.fragment.FragmentUser;
import com.example.tftmatches.model.Summoner;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements FragmentUser.OnSummonerSelected {

    private boolean isMultiPanel;
    private Summoner summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isMultiPanel = findViewById(R.id.container) !=null;
    }

    @Override
    public void onSummonerSelected(Summoner summoner) {
        this.summoner=summoner;
        if(!isMultiPanel){
            Intent intent = new Intent(MainActivity.this,MatchActivity.class);
            intent.putExtra("Summoner",summoner);
            startActivity(intent);
        }else{
            BottomNavigationView navView = findViewById(R.id.nav_view);
            navView.setVisibility(View.VISIBLE);
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_rank, R.id.navigation_history,R.id.navigation_fav)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupWithNavController(navView, navController);
            NavigationUI.onNavDestinationSelected(navView.getMenu().getItem(2),navController);
        }
    }


    public Summoner getSummoner(){
        return summoner;
    }


}
