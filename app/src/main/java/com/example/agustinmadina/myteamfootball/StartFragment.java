package com.example.agustinmadina.myteamfootball;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    Button mButtonStart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareViews();
        prepareListeners();

    }

    private void prepareListeners() {
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerEntryFragment playerEntryFragment = new PlayerEntryFragment();
                ArrayList<Player> mPlayers = new ArrayList<Player>();
                prepareBundle(playerEntryFragment, mPlayers);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_layout, playerEntryFragment)
                        .addToBackStack(null)
                        .commit();
            }

            private void prepareBundle(PlayerEntryFragment playerEntryFragment, ArrayList<Player> mPlayers) {
                Bundle extrasBundle = new Bundle();
                extrasBundle.putParcelableArrayList("players", mPlayers);
                playerEntryFragment.setArguments(extrasBundle);


            }
        }   );
    }


    private void prepareViews() {
        mButtonStart=(Button) getView().findViewById(R.id.button_start_team);

    }


}
