package com.example.my_shopping2.activity.MyAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.my_shopping2.R;

public class mHolder extends RecyclerView.ViewHolder {
    public TextView tv;
    public mHolder(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }
}
