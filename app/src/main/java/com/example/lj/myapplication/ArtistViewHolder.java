package com.example.lj.myapplication;


import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ArtistViewHolder extends ChildViewHolder {

    private TextView artistName;

    public ArtistViewHolder(View itemView) {
        super(itemView);
        artistName = itemView.findViewById(R.id.artistTitle);
    }

    public void onBind(Artist artist) {
        artistName.setText(artist.name);
    }
}