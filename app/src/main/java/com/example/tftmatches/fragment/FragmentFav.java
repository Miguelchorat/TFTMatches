package com.example.tftmatches.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tftmatches.R;
import com.example.tftmatches.adapter.MatchAdapter;
import com.example.tftmatches.data.MatchLab;
import com.example.tftmatches.model.Match;

import java.util.List;

public class FragmentFav extends Fragment {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private RecyclerView recyclerView;
    public LinearLayout emptyView;
    private RecyclerView.LayoutManager layoutManager;
    private MatchAdapter adapter;
    private List<Match> matches;
    private ImageView button;
    private MatchLab matchLab;
    private EditText filter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_fav, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_fav);
        filter = view.findViewById(R.id.filter);
        emptyView = view.findViewById(R.id.emptyView_fav);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        button = view.findViewById(R.id.sendFilter);
        matchLab = MatchLab.get(getContext());
        matches = matchLab.getMatches();
        adapter = new MatchAdapter(matches, R.layout.match_row, getActivity(), new MatchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Match match, int i) {
                createPopUp(match,i);
            }
        });
        if(matches.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter(view);
            }
        });
        return view;
    }

    public void filter(View v){
        String word = filter.getText().toString();
        filter.setText("");
        hideKeyboard(getActivity());

        if(!word.equals("")){
            matches = matchLab.getMatchesSearch(word);
        }else{
            matches = matchLab.getMatches();
        }
        adapter = new MatchAdapter(matches, R.layout.match_row, getActivity(), new MatchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Match match, int i) {
                createPopUp(match,i);
            }
        });
        if(matches.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void createPopUp(final Match match,final int i){
        builder=new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_delete,null);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();

        Button save = view.findViewById(R.id.ok_delete);
        TextView level = view.findViewById(R.id.deleteLevel);
        TextView date = view.findViewById(R.id.deleteDate);
        level.setText(""+match.getLevel());
        date.setText(""+match.getDate());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMatch(match);
                matches.remove(i);
                adapter.notifyItemRemoved(i);
                adapter.notifyItemRangeChanged(i, matches.size());
                dialog.dismiss();
                if(matches.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
    }



    public void deleteMatch(Match match){
        matchLab = MatchLab.get(getContext());
        matchLab.deleteMatch(match);
    }
}
