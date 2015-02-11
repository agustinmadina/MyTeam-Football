package com.example.agustinmadina.myteamfootball;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    TextView mTextViewGoalkeeper;
    TextView mTextViewDefender1;
    TextView mTextViewDefender2;
    TextView mTextViewDefender3;
    TextView mTextViewMidfielder1;
    TextView mTextViewMidfielder2;
    TextView mTextViewForward;
    TextView mTextViewTeamName;
    private final static String TEAM_NAME = "team_name_preference";
    private final static String DT_NAME = "dt_name_preference";
    ArrayList<Player> mPlayers;
    String mTeamName;
    String mDtName;
    TextView mTextViewDtName;



    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViews();
        getPreferences();
        setNames();
        Toast.makeText(getActivity(), "Team already completed Successfully", Toast.LENGTH_LONG).show();
    }

    private void setNames() {
        mTextViewTeamName.setText(mTeamName);
        mTextViewDtName.setText("DT: "+mDtName);
        mPlayers=this.getArguments().getParcelableArrayList("players");
        for (Player p:mPlayers){
            if (p.getmPosition()=="Goalkeeper"){
                mTextViewGoalkeeper.setText(p.getmName());
            }
            else if (p.getmPosition()=="Defender"){
                    if (TextUtils.isEmpty(mTextViewDefender1.getText())){
                        mTextViewDefender1.setText(p.getmName());
                    }
                    else if ((TextUtils.isEmpty(mTextViewDefender2.getText()))){
                        mTextViewDefender2.setText(p.getmName());
                    }
                    else if ((TextUtils.isEmpty(mTextViewDefender3.getText()))){
                        mTextViewDefender3.setText(p.getmName());
                    }
            }
            else if (p.getmPosition()=="Midfielder"){
                    if ((TextUtils.isEmpty(mTextViewMidfielder1.getText()))){
                        mTextViewMidfielder1.setText(p.getmName());
                    }
                    else if ((TextUtils.isEmpty(mTextViewMidfielder2.getText()))){
                        mTextViewMidfielder2.setText(p.getmName());
                    }
            }
            else {
                mTextViewForward.setText(p.getmName());
            }

        }
    }

    private void getPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mTeamName = sharedPreferences.getString(TEAM_NAME, getString(R.string.default_team_name));
        mDtName = sharedPreferences.getString(DT_NAME, getString(R.string.default_dt_name));
        if (mTeamName.trim().equals("")) {
            mTeamName = "My Team";
        }
        if (mDtName.trim().equals("")) {
            mDtName = "My DT";
        }
    }

    private void getViews() {
        mTextViewGoalkeeper=(TextView)getView().findViewById(R.id.text_view_goalkeeper);
        mTextViewDefender1=(TextView)getView().findViewById(R.id.text_view_defender1);
        mTextViewDefender2=(TextView)getView().findViewById(R.id.text_view_defender2);
        mTextViewDefender3=(TextView)getView().findViewById(R.id.text_view_defender3);
        mTextViewMidfielder1=(TextView)getView().findViewById(R.id.text_view_midfielder1);
        mTextViewMidfielder2=(TextView)getView().findViewById(R.id.text_view_midfielder2);
        mTextViewForward=(TextView)getView().findViewById(R.id.text_view_forward);
        mTextViewTeamName=(TextView)getView().findViewById(R.id.text_view_teamname);
        mTextViewDtName=(TextView)getView().findViewById(R.id.text_view_dtname);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }


}
