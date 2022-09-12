package com.project.e_learning.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;

import com.project.e_learning.Database.MyDatabase;
import com.project.e_learning.Model.Common;
import com.project.e_learning.Model.History;
import com.project.e_learning.databinding.ActivityHistoryBinding;
import com.project.e_learning.ui.adapters.HistoryAdapter;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ActivityHistoryBinding binding;
    private ArrayList<History> list;
    private HistoryAdapter adapter;
    private MyDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        mydb = new MyDatabase(this);
        Cursor c = mydb.gethistorydata(Common.currentUsername);
        if (c != null){
            if(c.moveToFirst());
            do {
                History h = new History(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getInt(4),c.getInt(5),c.getInt(6),c.getString(7));
                list.add(h);
            }while (c.moveToNext());
        }
        binding.HistoryRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(list);
        adapter.notifyDataSetChanged();
    }
}