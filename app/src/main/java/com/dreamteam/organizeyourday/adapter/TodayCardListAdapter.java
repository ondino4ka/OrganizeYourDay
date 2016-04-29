package com.dreamteam.organizeyourday.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public void onBindViewHolder(final TodayCardViewHolder holder, final int position) {
        holder.title.setText(todayCardsData.get(position).getTitle());
        holder.description.setText(todayCardsData.get(position).getDescription());
        holder.time.setText(todayCardsData.get(position).getTime());
        holder.date.setText(todayCardsData.get(position).getDate());
        setCardFlagPriorityIcon(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditCardActivity.class);
                intent.putExtra("id","" + todayCardsData.get(holder.getAdapterPosition()).getID());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("description", holder.description.getText().toString());
                intent.putExtra("time",todayCardsData.get(position).getTime());
                intent.putExtra("data",todayCardsData.get(position).getDate());

                Activity activity = (Activity) v.getContext();
                activity.startActivityForResult(intent, Activity.RESULT_OK);
                activity.overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
            }
        });
    }

    private void setCardFlagPriorityIcon(final TodayCardListAdapter.TodayCardViewHolder holder, int position)
    {
        switch (todayCardsData.get(position).getPriority())
        {
            case 0:
                holder.flagImage.setImageResource(R.drawable.flag_green);
                break;
            case 1:
                holder.flagImage.setImageResource(R.drawable.flag_yellow);
                break;
            case 2:
                holder.flagImage.setImageResource(R.drawable.flag_red);
                break;
            default:
                holder.flagImage.setImageResource(R.drawable.flag_green);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return todayCardsData.size();
    }

    public void setData(List<CardsData> data){
        this.todayCardsData = data;
    }

    public static class TodayCardViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        TextView title;
        TextView description;
        TextView time;
        TextView date;
        ImageView flagImage;
        public TodayCardViewHolder(final View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            title = (TextView)itemView.findViewById(R.id.card_title);
            description = (TextView)itemView.findViewById(R.id.card_description);
            date = (TextView)itemView.findViewById(R.id.card_date);
            time= (TextView)itemView.findViewById(R.id.card_time);
            flagImage = (ImageView)itemView.findViewById(R.id.priority_flag);
        }
    }
}
