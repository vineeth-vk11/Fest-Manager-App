package com.dota.festmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.festmanager.R;
import com.dota.festmanager.activity.DetailsActivity;
import com.dota.festmanager.model.EventDetails;

import java.util.ArrayList;

public class EventsGridAdapter extends BaseAdapter{

    private ArrayList<EventDetails> list;
    private Context context;

    public EventsGridAdapter(ArrayList<EventDetails> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view = LayoutInflater.from(context).inflate(R.layout.grid_event, viewGroup, false);
        if(i%2==0){
            ((ImageView)view.findViewById(R.id.eventBg)).setBackgroundColor(Color.BLACK);
            ((TextView)view.findViewById(R.id.eventName)).setTextColor(Color.WHITE);
        }
        else {
            ((ImageView)view.findViewById(R.id.eventBg)).setBackgroundColor(Color.WHITE);
            ((TextView)view.findViewById(R.id.eventName)).setTextColor(Color.BLACK);
        }
        ((TextView)view.findViewById(R.id.eventName)).setText(list.get(i).getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id", list.get(i).getId());
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }

}
