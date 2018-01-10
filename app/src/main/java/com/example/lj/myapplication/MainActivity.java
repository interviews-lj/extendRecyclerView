package com.example.lj.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    int parentPositionToExpand;
    int parentPositionToCollapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Artist[] values = new Artist[]{new Artist("1"),
                new Artist("2"),
                new Artist("3"),
                new Artist("4"),
                new Artist("5"),
                new Artist("6"),
                new Artist("7"),
                new Artist("8"),
                new Artist("9"),
                new Artist("10"),
                new Artist("11"),
                new Artist("12"),
                new Artist("13"),
                new Artist("14"),
                new Artist("15"),
                new Artist("16"),
                new Artist("17"),
                new Artist("18"),
                new Artist("19"),
                new Artist("20"),
        };

        List<Artist> artistList = new ArrayList<>();
        Collections.addAll(artistList, values);
        Genre genre = new Genre("title", artistList);
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            genres.add(genre);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //instantiate your adapter with the list of genres
        final GenreAdapter adapter = new GenreAdapter(this, genres);
        adapter.setOnGroupExpandCollapseListener(new GroupExpandCollapseListener() {
            @Override
            public void onGroupExpanded(ExpandableGroup group) {

            }

            @Override
            public void onGroupCollapsed(ExpandableGroup group) {

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        int currentItem = 0;
        int numberOfItemsBeforeExpand = genres.size();
        while (currentItem < adapter.getItemCount()) {
            if (!(adapter.isGroupExpanded(currentItem)) && adapter.isGroup(currentItem)) {
                adapter.toggleGroup(currentItem);
                int currentNumberOfItems = adapter.getItemCount();
                currentItem = currentItem + currentNumberOfItems - numberOfItemsBeforeExpand;
                numberOfItemsBeforeExpand = currentNumberOfItems;
            } else {
                currentItem++;
            }
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int currentNumberOfItems = adapter.getItemCount();

                //if scroll up
                if (dy < 0) {

                } else if (dy > 0) {
                    Toast.makeText(MainActivity.this, "DOWN", Toast.LENGTH_SHORT).show();
                    int currentFirstVisible = layoutManager.findFirstVisibleItemPosition();

                    //find which parent to expand
                    boolean flagToExpand = false;
                    parentPositionToExpand = currentFirstVisible + 1;
                    while (parentPositionToExpand <= currentNumberOfItems - 1) {
                        if (adapter.isGroup(parentPositionToExpand) && (!(adapter.isGroupExpanded(parentPositionToExpand)))) {
                            flagToExpand = true;
                            break;
                        }
                        parentPositionToExpand++;
                        flagToExpand = false;
                    }

                    //find parent to collapse and if visible toggle
                    boolean flagToToggle = false;
                    if (flagToExpand) {
                        int currentLastVisible = layoutManager.findLastVisibleItemPosition();
                        while (currentFirstVisible != currentLastVisible) {
                            if (adapter.isGroup(parentPositionToExpand) && (!(adapter.isGroupExpanded(parentPositionToExpand)))) {
                                flagToToggle = true;
                                break;
                            }
                            currentLastVisible--;
                            flagToToggle = false;
                        }

                        if (flagToToggle) {
                            parentPositionToCollapse = currentFirstVisible;
                            while (parentPositionToCollapse > -1) {
                                if (adapter.isGroupExpanded(parentPositionToCollapse) && adapter.isGroup(parentPositionToCollapse)) {
                                    Handler handler = new android.os.Handler();
                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.toggleGroup(parentPositionToCollapse);
                                            adapter.toggleGroup(parentPositionToExpand);
                                        }
                                    };
                                    handler.post(runnable);
                                    break;
                                }
                                parentPositionToCollapse--;
                            }
                        }

                    }

                }
/*
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached && adapter.isGroup(lastVisible)) {
                    adapter.toggleGroup(lastVisible);//you have reached to the bottom of your recycler view
                }*/

/*

                System.out.println("CURRENT VISIBLE = " + currentFirstVisible);
                System.out.println("NEXT to expand -> " + nextToExpand);
                System.out.println("PREVIOUS to collapse <- " + nextToColapse);
                System.out.println("=======================================");
                if (adapter.isGroup(currentFirstVisible)) {
                    Handler handler = new android.os.Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            adapter.toggleGroup(nextToExpand);
                            adapter.toggleGroup(nextToColapse);
                        }
                    };
                    handler.post(runnable);
                }

                if (dy > 0) {
                    nextToExpand = adapter.toggleOnScrollUp(currentFirstVisible);
                    nextToColapse = adapter.toggleOnScrollDown(currentFirstVisible);
                } else {
                    nextToExpand = adapter.toggleOnScrollDown(currentFirstVisible);
                    nextToColapse = adapter.toggleOnScrollUp(currentFirstVisible);
                }

*/
                /*
                if (!(adapter.isGroupExpanded(firstVisible))) {
                    adapter.toggleGroup(firstVisible);
                }

*/
            }
        });

    }


}
