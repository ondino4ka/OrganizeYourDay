package com.dreamteam.organizeyourday.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.EditCardActivity;
import com.dreamteam.organizeyourday.MainActivity;
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
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.description.setText(data.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Image animation
                CardView cardView = holder.card;
                if(img.getTranslationY()<0f) {
                    ValueAnimator valueAnimator = ObjectAnimator.ofFloat(img, "TranslationY", -1000, 0);
                    ValueAnimator valueAnimator1 = ObjectAnimator.ofInt(img,"Visibility", View.GONE,View.VISIBLE);
                    valueAnimator1.setDuration(200);
                    valueAnimator.setDuration(400);
                    valueAnimator.start();
                    valueAnimator1.start();
                }
                else
                {
                    ValueAnimator valueAnimator = ObjectAnimator.ofFloat(img, "TranslationY", 0, -1000);
                    ValueAnimator valueAnimator1 = ObjectAnimator.ofInt(img, "Visibility", View.VISIBLE, View.GONE);
                    valueAnimator.setDuration(400);
                    valueAnimator1.setDuration(200);
                    valueAnimator.start();
                    valueAnimator1.start();
                }*/

                Intent intent = new Intent(v.getContext(), EditCardActivity.class);
                intent.putExtra("title", holder.title.getText().toString());

                Activity activity = (Activity) v.getContext();
                activity.startActivityForResult(intent, Activity.RESULT_OK);
                activity.overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CardsData> data){
        this.data = data;
    }

    public static List<CardsData> getData() {
        return data;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        TextView title;
        TextView description;

        public CardViewHolder(final View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.cardDescription);
        }
    }

}
