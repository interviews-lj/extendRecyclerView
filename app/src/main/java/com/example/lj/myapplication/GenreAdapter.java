package com.example.lj.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.List;

public class GenreAdapter extends ExpandableRecyclerViewAdapter<GenreViewHolder, ArtistViewHolder> {

    LayoutInflater inflater;

    public GenreAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.genre_item, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public ArtistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.artist_item, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ArtistViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        final Artist artist = ((Genre) group).getItems().get(childIndex);
        holder.onBind(artist);
    }

    @Override
    public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setGenreTitle(group);
    }

    public int toggleOnScrollUp(int flatPosition) {
        int type = this.getItemViewType(flatPosition);
        while (type != ExpandableListPosition.GROUP && flatPosition > 0) {
            flatPosition--;
            type = this.getItemViewType(flatPosition);
        }
        return flatPosition;
    }

    public int toggleOnScrollDown(int flatPosition) {
        int type = this.getItemViewType(flatPosition);
        while (type != ExpandableListPosition.GROUP && flatPosition < this.getItemCount()-1) {
            flatPosition++;
            type = this.getItemViewType(flatPosition);
        }
        return flatPosition;
    }

    public boolean isGroup(int flatPosition) {
        return this.getItemViewType(flatPosition) == ExpandableListPosition.GROUP;
    }
}