package com.dreamteam.organizeyourday.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamteam.organizeyourday.ContextContainer;
import com.dreamteam.organizeyourday.EditCardActivity;
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
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.description.setText(data.get(position).getDescription());
        holder.time.setText(data.get(position).getTime());
        holder.date.setText(data.get(position).getDate());
        setCardFlagPriorityIcon(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditCardActivity.class);
                intent.putExtra("id","" + data.get(holder.getAdapterPosition()).getID());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("description", holder.description.getText().toString());
                intent.putExtra("time",data.get(position).getTime());
                intent.putExtra("data", data.get(position).getDate());

                Activity activity = (Activity) v.getContext();
                activity.startActivityForResult(intent, Activity.RESULT_OK);
                activity.overridePendingTransition(R.anim.down_anim_in, R.anim.down_anim_fade_out);
            }
        });
    }

    private void setCardFlagPriorityIcon(final CardViewHolder holder, int position)
    {
        final ImageView flag = holder.flagImage;
        switch (data.get(position).getPriority())
        {
            case 0:
                flag.setImageBitmap(BitmapFactory.decodeResource(ContextContainer.getContext().getResources(),R.drawable.flag_green));
                break;
            case 1:
                flag.setImageBitmap(BitmapFactory.decodeResource(ContextContainer.getContext().getResources(),R.drawable.flag_yellow));
                break;
            case 2:
                flag.setImageBitmap(BitmapFactory.decodeResource(ContextContainer.getContext().getResources(),R.drawable.flag_red));
                break;
            default:
                flag.setImageBitmap(BitmapFactory.decodeResource(ContextContainer.getContext().getResources(),R.drawable.flag_green));
                break;
        }
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
        TextView time;
        TextView date;
        ImageView flagImage;
        public CardViewHolder(final View itemView) {
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
