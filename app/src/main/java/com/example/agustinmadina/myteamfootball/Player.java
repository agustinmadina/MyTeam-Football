package com.example.agustinmadina.myteamfootball;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by agustin.madina on 09/02/2015.
 */
public class Player implements Parcelable {
    public String mName;
    public String mPosition;



    public Player(String mName, String mPosition){
        this.mName = mName;
        this.mPosition=mPosition;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPosition() {
        return mPosition;
    }

    public void setmPosition(String mPosition) {
        this.mPosition = mPosition;
    }

    private Player(Parcel source) {

        mName=source.readString();
        mPosition=source.readString();

    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mPosition);
    }
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {

        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}

