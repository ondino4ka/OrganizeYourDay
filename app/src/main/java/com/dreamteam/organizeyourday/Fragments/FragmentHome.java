package com.dreamteam.organizeyourday.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.adapter.CardListAdapter;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.List;

public class FragmentHome extends android.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Animation translateAnim;
    private RecyclerView.ItemAnimator animation;
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

    private void setAnimator(android.view.View view)
    {
        translateAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.right_translate);
        animation = new RecyclerView.ItemAnimator() {
            @Override
            public boolean animateDisappearance(RecyclerView.ViewHolder viewHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
                return false;
            }

            @Override
            public boolean animateAppearance(RecyclerView.ViewHolder viewHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
                viewHolder.itemView.startAnimation(translateAnim);
                return true;
            }

            @Override
            public boolean animatePersistence(RecyclerView.ViewHolder viewHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
                return false;
            }

            @Override
            public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
                return false;
            }

            @Override
            public void runPendingAnimations() {

            }

            @Override
            public void endAnimation(RecyclerView.ViewHolder item) {

            }

            @Override
            public void endAnimations() {

            }

            @Override
            public boolean isRunning() {
                return false;
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setAnimator(view);

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.cardList);
        animation.runPendingAnimations();

        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setItemAnimator(animation);
        context = ContextContainer.getContext();
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
                                    db.removeCardInformation(CardListAdapter.getData().get(position).getID());
                                    CardListAdapter.getData().remove(position);
                                    cdAdapter.notifyItemRemoved(position);
                                }
                                cdAdapter.notifyDataSetChanged();
                            }
                        });

        rv.addOnItemTouchListener(swipeTouchListener);
        return view;

    }

    public void refreshAdapter(){
        cdAdapter.setData(db.getListOfDataBaseComponent());
        cdAdapter.notifyItemChanged(CardListAdapter.getData().size());
        }

    public void refreshAdapter(List<CardsData> data){
        cdAdapter.setData(data);
        cdAdapter.notifyDataSetChanged();
    }
}
