package com.example.vania.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vania.myapplication.R;
import com.example.vania.myapplication.adapter.CartListAdapter;
import com.example.vania.myapplication.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vania on 27.02.2016.
 */
public class DoneEventFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.done_event_fragment;
    private List<Event> data = new ArrayList<>();
    private CartListAdapter adapter;

    public static DoneEventFragment getInstance(Context context, List<Event> data){
        Bundle args = new Bundle();
       DoneEventFragment fragment = new DoneEventFragment();
        fragment.setArguments(args);
        fragment.setData(data);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_done));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleViewDoneFr);
        rv.setLayoutManager(new LinearLayoutManager(context));


        adapter = new CartListAdapter(data);
        rv.setAdapter(adapter);

        return view;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<Event> data) {
        this.data = data;
    }

    public void refreshData(List<Event> data){
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
