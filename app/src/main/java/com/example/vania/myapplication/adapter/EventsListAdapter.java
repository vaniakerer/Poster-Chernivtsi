package com.example.vania.myapplication.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vania.myapplication.R;
import com.example.vania.myapplication.activities.EventActivity;
import com.example.vania.myapplication.model.Event;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ivan on 30.05.16.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.MyViewHolder> {



    private List<Event> eventList;

    public void setData(List<Event> data) {
        eventList = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CircleImageView postImage;
        public TextView id;
        public TextView eventDate;
        public  TextView postDate;
        public TextView author_id;
        public TextView eventType;
        public TextView eventId;
        public TextView imageUrl;
        public TextView description;
        public TextView idRate;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.event_title_up);
            postImage = (CircleImageView) view.findViewById(R.id.event_image);
            eventDate = (TextView) view.findViewById(R.id.event_event_date_up);
            postDate = (TextView) view.findViewById(R.id.event_post_date_up);
            author_id = (TextView) view.findViewById(R.id.event_id_author_up);
            eventType = (TextView) view.findViewById(R.id.event_id_type_up);
            eventId = (TextView) view.findViewById(R.id.event_id_up);
            imageUrl = (TextView) view.findViewById(R.id.event_img_url_up);
            description = (TextView) view.findViewById(R.id.event_description_up);
            idRate = (TextView) view.findViewById(R.id.event_id_rate_up);

        }
    }

    public EventsListAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public EventsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventsListAdapter.MyViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.title.setText(event.getTitle());
        holder.eventDate.setText(event.getEventDate());
        holder.postDate.setText(event.getPostDate());
        holder.author_id.setText( event.getId_author());
        holder.eventType.setText(Integer.toString((int) event.getId_type()));
        holder.eventId.setText(Integer.toString((int) event.getId()));
        holder.imageUrl.setText(event.getImageUrl());
        holder.description.setText(event.getDescription());
        holder.idRate.setText(Integer.toString((int) event.getId_rate()));

        Picasso.with(holder.title.getContext())
                .load(event.getImageUrl())
                .into(holder.postImage);

        //onClick для перехода на актівіті вибраного івента
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.itemView.getContext(), EventActivity.class);

                //передача інфи про івент в актівіті івента

                Log.d("_TEST", holder.description.getText().toString());

                intent.putExtra("id", holder.eventId.getText().toString());
                intent.putExtra("title", holder.title.getText().toString());
                intent.putExtra("event_date",holder.eventDate.getText().toString());
                intent.putExtra("post_date", holder.postDate.getText().toString());
                intent.putExtra("author", holder.author_id.getText().toString());
                intent.putExtra("type", /*holder.eventType.getText().toString()*/ "2");
                intent.putExtra("description", holder.description.getText().toString());
                intent.putExtra("imageUrl", holder.imageUrl.getText().toString());

                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
