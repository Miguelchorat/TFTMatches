package com.example.tftmatches.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.tftmatches.R;
import com.example.tftmatches.activity.MainActivity;
import com.example.tftmatches.activity.MatchActivity;
import com.example.tftmatches.model.Rank;
import com.example.tftmatches.model.RankViewModel;
import com.example.tftmatches.model.Summoner;
import com.squareup.picasso.Picasso;

public class FragmentRank extends Fragment {

    private Rank rank;
    private Summoner summoner;
    TextView name;
    TextView tier;
    TextView wins;
    TextView losses;
    TextView lp;
    TextView rankTv;
    ImageView emblem;
    ImageView icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        tier= view.findViewById(R.id.tvtier);
        wins = view.findViewById(R.id.tvwins);
        lp = view.findViewById(R.id.tvlp);
        losses = view.findViewById(R.id.tvlosses);
        emblem = view.findViewById(R.id.imageRank);
        icon = view.findViewById(R.id.iconSummoner);
        name=view.findViewById(R.id.tvsummonerName);
        rankTv=view.findViewById(R.id.tvrank);
        try {
            MatchActivity activity = (MatchActivity) getActivity();
            summoner = activity.getSummoner();
        }catch (ClassCastException e){
            MainActivity mainActivity = (MainActivity)getActivity();
            summoner = mainActivity.getSummoner();
        }
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/9.24.2/img/profileicon/"+summoner.getIcon()+".png").error(R.drawable.zoe).into(icon);
        setRank();
        return view;
    }

    private void setRank(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = info != null && info.isConnected();
        if(isConnected){
            final RankViewModel modelRank = ViewModelProviders.of(this).get(RankViewModel.class);
            if(summoner!=null){
                modelRank.setIdSummoner(summoner.getId());
                modelRank.getRank().observe(this, new Observer<Rank>() {
                    @Override
                    public void onChanged(Rank r) {
                        rank=r;
                        if(r!=null){
                            rankTv.setText(rank.getRank().toUpperCase());
                            tier.setText(rank.getTier().substring(0,1).toUpperCase()+rank.getTier().substring(1).toLowerCase());
                            wins.setText(""+rank.getWins());
                            losses.setText(""+rank.getLosses());
                            lp.setText(""+rank.getLp());
                            setEmblem(rank.getTier());
                        }
                        modelRank.reset(null);
                    }
                });
                Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/9.24.2/img/profileicon/"+summoner.getIcon()+".png").into(icon);
                name.setText(summoner.getName());
            }


        }else{
            Toast.makeText(getContext(),R.string.internet,Toast.LENGTH_SHORT).show();
        }

    }

    private void setEmblem(String rank){

        switch (rank.toLowerCase()){
            case "bronze":
                emblem.setImageResource(R.drawable.emblem_bronze);
                break;
            case "silver":
                emblem.setImageResource(R.drawable.emblem_silver);
                break;
            case "gold":
                emblem.setImageResource(R.drawable.emblem_gold);
                break;
            case "platinum":
                emblem.setImageResource(R.drawable.emblem_platinum);
                break;
            case "diamond":
                emblem.setImageResource(R.drawable.emblem_diamond);
                break;
            case "master":
                emblem.setImageResource(R.drawable.emblem_master);
                break;
            case "grandmaster":
                emblem.setImageResource(R.drawable.emblem_grandmaster);
                break;
            case "challenger":
                emblem.setImageResource(R.drawable.emblem_challenger);
                break;
        }
    }

    public void setSummoner(Summoner summoner){
        this.summoner=summoner;
        setRank();
    }
}
