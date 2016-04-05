package com.dreamteam.organizeyourday.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.adapter.CardListAdapter;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

public class FragmentHome extends android.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Context context;
    int counter =0;
    private View view;
    DatabaseHelper db;
    private CardListAdapter cdAdapter;


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
        context = ContextContainer.getContainer();
        db = new DatabaseHelper(context);
        cdAdapter = new CardListAdapter(db.getListOfDataBaseComponent());
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
                                return false;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    db.removeCardInformation(CardListAdapter.getData().get(position).getID());
                                    CardListAdapter.getData().remove(position);
                                    cdAdapter.notifyItemRemoved(position);
                                }
                                cdAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                }
                            }
                        });

        rv.addOnItemTouchListener(swipeTouchListener);

        return view;

    }

    public void refreshAdapter(){
        cdAdapter.setData(db.getListOfDataBaseComponent());
        cdAdapter.notifyItemChanged(CardListAdapter.getData().size());
        }
}
