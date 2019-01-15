package com.dota.festmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dota.festmanager.R;
import com.dota.festmanager.activity.MainActivity;
import com.dota.festmanager.activity.MoreItemsActivity;

import java.util.ArrayList;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreViewHolder> {

    private Context context;
    private ArrayList<String> moreitemsList = new ArrayList<>();

    public MoreAdapter(Context context, ArrayList<String> moreitemsList) {
        this.context = context;
        this.moreitemsList = moreitemsList;
    }

    @NonNull
    @Override
    public MoreAdapter.MoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_more_item, parent, false);
        return new MoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreAdapter.MoreViewHolder holder, final int i) {
        holder.moreitemText.setText(moreitemsList.get(i));
        final MainActivity activity = (MainActivity) context;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MoreItemsActivity.class);
                intent.putExtra("position", i);
                view.getContext().startActivity(intent);
            }
        });
        switch (i) {
            case 0:
                holder.moreitemIcon.setImageResource(R.drawable.ic_baseline_info_24px);
                break;
            case 1:
                holder.moreitemIcon.setImageResource(R.drawable.ic_bus_24px);
                break;
            case 2:
                //todo set icon
                break;
            case 3:
                break;
            case 4:
                holder.moreitemIcon.setImageResource(R.drawable.ic_credits);
                break;
            case 5:
                holder.moreitemIcon.setImageResource(R.drawable.ic_baseline_settings_20px);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return moreitemsList.size();
    }


    public class MoreViewHolder extends RecyclerView.ViewHolder {
        TextView moreitemText;
        ImageView moreitemIcon;

        public MoreViewHolder(@NonNull View itemView) {
            super(itemView);
            moreitemText = itemView.findViewById(R.id.more_item_text);
            moreitemIcon = itemView.findViewById(R.id.more_item_icon);
        }
    }
}
