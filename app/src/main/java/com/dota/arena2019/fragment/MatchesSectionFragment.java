package com.dota.arena2019.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dota.arena2019.R;
import com.dota.arena2019.adapter.MatchScheduleAdapter;
import com.dota.arena2019.model.MatchDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MatchesSectionFragment extends Fragment {
    private int section;
    private String eventId;
    private RecyclerView rootView;
    private TextView status;
    private RecyclerView.Adapter adapter;

    public MatchesSectionFragment(){}

    public static MatchesSectionFragment newInstance(int sectionNumber, String eventId) {
        MatchesSectionFragment fragment = new MatchesSectionFragment();
        Bundle args = new Bundle();
        args.putInt("section", sectionNumber);
        args.putString("ID",eventId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        section = this.getArguments().getInt("section",0);
        eventId = this.getArguments().getString("ID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches_result, container, false);
        rootView = view.findViewById(R.id.recycleView);
        status = view.findViewById(R.id.status);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        rootView.setLayoutManager(mLayoutManager2);

        switch (section){
            case 1:
                break;
            case 2:
                break;
            case 3:
                final ArrayList<MatchDetails> list = new ArrayList<>();
                FirebaseDatabase.getInstance().getReference().child("Scores").child("Upcoming Matches").child(eventId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot!=null){
                            for(DataSnapshot ds:dataSnapshot.getChildren())
                                list.add(ds.getValue(MatchDetails.class));

                        }
                        if(list.size()==0) {
                            status.setText("Schedule not yet Updated");
                            status.setVisibility(View.VISIBLE);
                        }
                        else {
                            status.setVisibility(View.GONE);
                            adapter = new MatchScheduleAdapter(getActivity(),list);
                            rootView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
        }
        return view;
    }
}