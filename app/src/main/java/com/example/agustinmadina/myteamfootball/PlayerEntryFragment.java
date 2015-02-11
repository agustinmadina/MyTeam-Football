package com.example.agustinmadina.myteamfootball;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerEntryFragment extends Fragment {

    static int mTotalQuantity = 7;
    static int mForward = 1;
    static int mMidFielder = 2;
    static int mDefender = 3;
    static int mGoalkeeper=1;
    String mPosition;
    String mName;
    ArrayList<Player> mPlayers;
    Button mButtonNext;
    RadioButton mRadioButtonGoalkeeper;
    RadioButton mRadioButtonDefender;
    RadioButton mRadioButtonMidfielder;
    RadioButton mRadioButtonForward;
    RadioGroup mRadioGroupPosition;
    Player mPlayer;
    EditText mEditTextName;
    TextView mTextViewTeamFinished;

    public PlayerEntryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_entry, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareViews();
        disableRadioButtons();
        mPlayers=this.getArguments().getParcelableArrayList("players");
        prepareListeners();

    }

    private void prepareListeners() {
        prepareRadioGroupListener();
        mEditTextName.addTextChangedListener(watcher);
        prepareNextButtonListener();
    }

    private void prepareRadioGroupListener() {
        mRadioGroupPosition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (TextUtils.isEmpty(mEditTextName.getText())) {
                    mButtonNext.setEnabled(false);
                } else mButtonNext.setEnabled(true);
            }
        });
    }

    private void prepareNextButtonListener() {
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                decidePosition();
                mName = mEditTextName.getText().toString();
                mPlayer = new Player(mName, mPosition);
                mPlayers.add(mPlayer);
                Bundle extrasBundle = new Bundle();
                extrasBundle.putParcelableArrayList("players", mPlayers);
                mTotalQuantity--;
                if (mTotalQuantity <= 0) {
                    SummaryFragment summaryFragment = new SummaryFragment();
                    summaryFragment.setArguments(extrasBundle);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, summaryFragment)
                            .addToBackStack(null)
                            .commit();
                } else {

                    Toast.makeText(getActivity(), "Player added", Toast.LENGTH_SHORT).show();
                    PlayerEntryFragment playerEntryFragment = new PlayerEntryFragment();
                    playerEntryFragment.setArguments(extrasBundle);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, playerEntryFragment)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });
    }

    private final TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(mEditTextName.getText())) {
                mButtonNext.setEnabled(false);
            }
            else if (mRadioButtonGoalkeeper.isChecked()|| (mRadioButtonDefender.isChecked()|| (mRadioButtonMidfielder.isChecked())|| (mRadioButtonForward.isChecked()))){
                mButtonNext.setEnabled(true);
            }
            else{
                mButtonNext.setEnabled(false);}
        }
    };
    private void disableRadioButtons() {
        if (mGoalkeeper<=0){
            mRadioButtonGoalkeeper.setEnabled(false);
        }
        if (mDefender<=0){
            mRadioButtonDefender.setEnabled(false);
        }
        if (mMidFielder<=0){
            mRadioButtonMidfielder.setEnabled(false);
        }
        if (mForward<=0){
            mRadioButtonForward.setEnabled(false);
        }
        if (mTotalQuantity <= 0){
            mEditTextName.setEnabled(false);
            mButtonNext.setEnabled(true);
            mTextViewTeamFinished.setText(R.string.team_completed);

        }
    }

    private void decidePosition() {
        if (mRadioButtonGoalkeeper.isChecked()) {
            mPosition="Goalkeeper";
            mGoalkeeper--;
        }
        else
            if (mRadioButtonDefender.isChecked()){
                mPosition="Defender";
                mDefender--;
            }
            else
                if(mRadioButtonMidfielder.isChecked()){
                    mPosition="Midfielder";
                    mMidFielder--;
                }
                else{
                    mPosition="Forward";
                    mForward--;}
    }




    private void prepareViews() {
        mRadioButtonGoalkeeper=(RadioButton)getView().findViewById(R.id.radio_button_goalkeeper);
        mRadioButtonDefender=(RadioButton)getView().findViewById(R.id.radio_button_defender);
        mRadioButtonMidfielder=(RadioButton)getView().findViewById(R.id.radio_button_midfielder);
        mRadioButtonForward=(RadioButton)getView().findViewById(R.id.radio_button_forward);
        mButtonNext=(Button)getView().findViewById(R.id.button_next);
        mRadioGroupPosition=(RadioGroup)getView().findViewById((R.id.radio_group_position));
        mEditTextName=(EditText)getView().findViewById(R.id.edit_text_name);
        mTextViewTeamFinished=(TextView)getView().findViewById(R.id.text_view_teamfinished);


    }


}
