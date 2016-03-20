package com.dreamteam.organizeyourday.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder>  {



    private static List<CardsData> data;

    public CardListAdapter(List<CardsData> data) {
        this.data = data;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static void data1() {
        data.add(new CardsData("123"));
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView title;

        public CardViewHolder(View itemView) {
            super(itemView);
            CardView card = (CardView)itemView.findViewById(R.id.card);
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }

}
