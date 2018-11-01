package com.example.my_shopping2.activity.MyAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_shopping2.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<mHolder> {
    private Context context;
    private ArrayList<String> arrayList;

    public Adapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public mHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.head, null);
        mHolder mHolder = new mHolder(inflate);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull mHolder mHolder, int i) {
        mHolder.tv.setText(arrayList.get(i));
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
