package com.project.e_learning.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.e_learning.Model.History;
import com.project.e_learning.databinding.HistoryrowitemBinding;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private ArrayList<History> list;
    private ArrayList<History> copylist;
    Context context;

    public HistoryAdapter(ArrayList<History> list) {
        this.list = list;
        copylist = list;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        HistoryrowitemBinding binding = HistoryrowitemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        HistoryViewHolder holder = new HistoryViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History h = list.get(position);
        holder.Hbinding.setObj(h);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        HistoryrowitemBinding Hbinding;
        public HistoryViewHolder(HistoryrowitemBinding Hbinding) {
            super(Hbinding.getRoot());
            this.Hbinding = Hbinding;
        }
    }
}
