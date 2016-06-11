package com.example.vania.myapplication.adapter;

/**
 * Created by vania on 27.02.2016.
 */
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vania.myapplication.activities.EventActivity;
import com.example.vania.myapplication.R;
import com.example.vania.myapplication.model.Event;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.RemindViewHolder> {

    private List<Event> data;

    public CartListAdapter(List<Event> data) {
        this.data = data;
    }

    @Override
    public RemindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remind_item, parent, false);
        return new RemindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RemindViewHolder holder, int position) {
        Event item = data.get(position);


        holder.title.setText(item.getTitle());
        holder.author.setText(( Integer.toString((int) item.getId_author())));
        holder.postDate.setText(item.getPostDate());
        holder.eventDate.setText(item.getEventDate());
        holder.eventType.setText(Integer.toString((int) item.getId_type()));
        holder.eventId.setText(Long.toString(item.getId()));
        holder.description.setText(item.getDescription());
        holder.imageUrl.setText(item.getImageUrl());

        //TODO RATE

        Picasso.with(holder.author.getContext())
                .load(item.getImageUrl())
                .resize(500, 280)
                .into(holder.eventImage);

        Log.d("MY_URL",item.getImageUrl());



        //Ставиться іконка типу івента на лісті івентів
        /**
         0: disco party
         1: IT event
         2: rally event
         3: presentation
         4: sport
         5: education
         */
        switch ((int) item.getId_type()){
            case 0:
                holder.typeImage.setImageResource(R.drawable.type_party);
                break;
            case 1:
                holder.typeImage.setImageResource(R.drawable.type_it);
                break;
            case 2:
                holder.typeImage.setImageResource(R.drawable.type_presentation);
                break;
            case 3:
                holder.typeImage.setImageResource(R.drawable.type_presentation);
                break;
            case 4:
                holder.typeImage.setImageResource(R.drawable.type_sport);
                break;
            case 5:
                holder.typeImage.setImageResource(R.drawable.type_education);
            default:
                holder.typeImage.setImageResource(R.drawable.type_default);
                break;
        }


    }

    public void setData(List<Event> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class RemindViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView eventDate;
        TextView postDate;
        TextView author;
        TextView eventType;
        TextView eventId;
        TextView imageUrl;
        TextView description;
        ImageView typeImage;
        ImageView eventImage;

        public RemindViewHolder(final View itemView) {
            super(itemView);

            //ініціалізація елементів
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            title = (TextView) itemView.findViewById(R.id.title_event_cart);
            eventDate = (TextView) itemView.findViewById(R.id.date_event);
            postDate = (TextView) itemView.findViewById(R.id.post_date);
            author = (TextView) itemView.findViewById(R.id.author);
            eventType = (TextView) itemView.findViewById(R.id.type);
            typeImage = (ImageView) itemView.findViewById(R.id.type_event_image);
            eventId = (TextView) itemView.findViewById(R.id.id_current_event);
            eventImage = (ImageView) itemView.findViewById(R.id.event_image);
            description = (TextView) itemView.findViewById(R.id.c_description);
            imageUrl = (TextView) itemView.findViewById(R.id.image_url);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), EventActivity.class);

                    //передача інфи про івент в актівіті івента
                    intent.putExtra("id", eventId.getText());
                    intent.putExtra("title",title.getText());
                    intent.putExtra("event_date", eventDate.getText());
                    intent.putExtra("post_date", postDate.getText());
                    intent.putExtra("author",author.getText());
                    intent.putExtra("type", eventType.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    intent.putExtra("imageUrl", imageUrl.getText().toString());

                    itemView.getContext().startActivity(intent);

                }
            });
        }


    }
}