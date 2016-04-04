package com.dreamteam.organizeyourday.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;

import com.dreamteam.organizeyourday.DataBase.DataBase;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.adapter.CardListAdapter;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends android.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Context context;
    int counter =0;
    private View view;
    DataBase data;
    private CardListAdapter cdAdapter;
    List<CardsData> testData;


    public static FragmentHome getInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = (RecyclerView)view.findViewById(R.id.cardList);
        RecyclerView.ItemAnimator animation = new DefaultItemAnimator();
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setItemAnimator(animation);
        cdAdapter = new CardListAdapter(testData());
        rv.setAdapter(cdAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(rv,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    testData.remove(position);
                                    data.removeCardInformation(testData.get(position).getTitle());
                                    cdAdapter.notifyItemRemoved(position);
                                }
                                cdAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    testData.remove(position);
                                    cdAdapter.notifyItemRemoved(position);
                                }
                                cdAdapter.notifyDataSetChanged();
                            }
                        });

        rv.addOnItemTouchListener(swipeTouchListener);

        return view;

    }

    public List<CardsData> testData() {
        data = new DataBase();
        testData = data.getListOfDataBaseComponent();
        return testData;
    }

    public void refreshAdapter(){
        counter++;
        testData.add(new CardsData("Simple item " + counter));
        cdAdapter.setData(testData);
        cdAdapter.notifyItemInserted(testData.size());
        }
}
