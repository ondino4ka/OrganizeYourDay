package com.dreamteam.organizeyourday.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.adapter.CardListAdapter;
import com.dreamteam.organizeyourday.adapter.TodayCardListAdapter;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;

import java.util.ArrayList;
import java.util.List;

public class FragmentTodayCards extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context context;
    private View view;
    private TodayCardListAdapter todayCdAdapter;

    public FragmentTodayCards() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentTodayCards newInstance(String param1, String param2) {
        FragmentTodayCards fragment = new FragmentTodayCards();
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
        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.cardList);
        rv.setLayoutManager(new LinearLayoutManager(context));
        context = ContextContainer.getContext();
        todayCdAdapter = new TodayCardListAdapter(mokeData());
        rv.setAdapter(todayCdAdapter);
        return view;
    }

    List<CardsData> mokeData(){
        List<CardsData> cards = new ArrayList<>();
        cards.add(new CardsData(500,"test 0", "test description", 0, "1213", "12342"));
        cards.add(new CardsData(500,"test 1", "test description", 1, "1213", "12342"));
        cards.add(new CardsData(500,"test 2", "test description", 0, "1213", "12342"));
        return cards;
    }
}
