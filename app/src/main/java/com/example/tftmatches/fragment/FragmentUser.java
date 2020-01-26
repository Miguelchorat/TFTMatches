package com.example.tftmatches.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tftmatches.R;
import com.example.tftmatches.model.Summoner;
import com.example.tftmatches.model.SummonerViewModel;

public class FragmentUser extends Fragment {

    private Summoner summoner;
    private OnSummonerSelected callback;
    EditText etsummoner;

    public FragmentUser() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        ImageView image = view.findViewById(R.id.send);
        etsummoner =view.findViewById(R.id.etsummonerName);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSummoner(view);
                etsummoner.setText("");
                hideKeyboard(getActivity());
            }
        });
        return view;
    }

    public void getSummoner(final View view){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = info != null && info.isConnected();
        if(isConnected){
            final SummonerViewModel model = ViewModelProviders.of(this).get(SummonerViewModel.class);

            model.setSummonerName(etsummoner.getText().toString());

            model.getSummoner().observe(this, new Observer<Summoner>() {
                @Override
                public void onChanged(Summoner summon) {
                    if(summon!=null) {
                        summoner=summon;
                        callback.onSummonerSelected(summoner);
                    }
                    else{
                        Toast.makeText(getContext(),"Vuelve a ingresar un nuevo Usuario",Toast.LENGTH_SHORT).show();
                    }
                    model.reset(null);
                }
            });
        }else{
            Toast.makeText(getContext(),R.string.internet,Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (OnSummonerSelected)context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" should implement OnSummonerSelected interface");
        }

    }

    public interface OnSummonerSelected{
        public void onSummonerSelected(Summoner summoner);
    }
}
