package com.example.tftmatches.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tftmatches.R;
import com.example.tftmatches.activity.MainActivity;
import com.example.tftmatches.activity.MatchActivity;
import com.example.tftmatches.adapter.MatchAdapter;
import com.example.tftmatches.data.MatchLab;
import com.example.tftmatches.model.Match;
import com.example.tftmatches.model.MatchViewModel;
import com.example.tftmatches.model.Summoner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FragmentHistory extends Fragment {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Summoner summoner;
    private RecyclerView recyclerView;
    public LinearLayout emptyView;
    private RecyclerView.LayoutManager layoutManager;
    private MatchAdapter adapter;
    public ImageView progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        emptyView = view.findViewById(R.id.emptyView);
        progressBar = view.findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        try {
            MatchActivity activity = (MatchActivity) getActivity();
            summoner = activity.getSummoner();
        }catch (ClassCastException e){
            MainActivity mainActivity = (MainActivity)getActivity();
            summoner = mainActivity.getSummoner();
        }
        Glide.with(getContext()).asGif().load(R.drawable.katarina).into(progressBar);
        setMatches();
        return view;
    }

    private void setMatches() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = info != null && info.isConnected();

        if(isConnected){
            final MatchViewModel modelMatch = ViewModelProviders.of(this).get(MatchViewModel.class);
            modelMatch.setPuuid(summoner.getPuuid());
            modelMatch.reset(null);
            modelMatch.getMatches().observe(this, new Observer<List<Match>>() {
                @Override
                public void onChanged(List<Match> r) {
                    emptyView.setVisibility(View.GONE);
                    Collections.sort(r);
                    adapter = new MatchAdapter(r, R.layout.match_row, getActivity(), new MatchAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Match match, int i) {
                            createPopUp(match);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else{
            Toast.makeText(getContext(),R.string.internet,Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
        checkVisibility();
    }

    public void createPopUp(final Match match){
        builder=new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup,null);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();

        Button save = view.findViewById(R.id.ok);
        TextView level = view.findViewById(R.id.addLevel);
        TextView date = view.findViewById(R.id.addDate);
        level.setText(""+match.getLevel());
        date.setText(""+match.getDate());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMatch(match);
                dialog.dismiss();
            }
        });

    }

    public void saveMatch(Match m){
        MatchLab matchLab = MatchLab.get(getContext());
        matchLab.addMatch(m);
    }

    private void checkVisibility(){
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (this){
                        wait(3000);

                        Activity a = getActivity();
                        if (a!=null){
                            a.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (progressBar.getVisibility() != View.GONE) {
                                        progressBar.setVisibility(View.GONE);
                                        emptyView.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }
}
