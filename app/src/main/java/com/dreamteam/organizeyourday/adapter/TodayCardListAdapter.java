package com.dreamteam.organizeyourday.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamteam.organizeyourday.EditCardActivity;
import com.dreamteam.organizeyourday.R;
import com.dreamteam.organizeyourday.dataOfCards.CardsData;

import java.util.List;


public class TodayCardListAdapter extends RecyclerView.Adapter<TodayCardListAdapter.TodayCardViewHolder>{

    private static List<CardsData> todayCardsData;


    public TodayCardListAdapter(List<CardsData> data) {
        this.todayCardsData = data;
    }

    @Override
    public TodayCardListAdapter.TodayCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new TodayCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TodayCardViewHolder holder, int position) {
      //  holder.title.setText(todayCardsData.get(position).getTitle());
       // holder.description.setText(todayCardsData.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditCardActivity.class);
                intent.putExtra("id","" + todayCardsData.get(holder.getAdapterPosition()).getID());
                intent.putExtra("title", holder.title.getText().toString());

                Activity activity = (Activity) v.getContext();
                activity.startActivityForResult(intent, Activity.RESULT_OK);
                activity.overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todayCardsData.size();
    }

    public static class TodayCardViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        TextView title;
        TextView description;
        public TodayCardViewHolder(final View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.card_description);
        }
    }
}
