package com.example.my_shopping2.activity.myView;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.my_shopping2.activity.MyAdapter.Adapter;
import com.example.my_shopping2.activity.MyInterface.javabeanservice;
import com.example.my_shopping2.R;
import com.example.my_shopping2.activity.bean;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    private XRecyclerView mXRecyclerView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private  int mDistanceY ;
    private Adapter adapter;
    private  View   searchView;
    private ImageButton ib;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout, null);
        initview(inflate);
        addData();
        adapter = new Adapter(getContext(),arrayList);
        mXRecyclerView.setAdapter(adapter);

        mXRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistanceY += dy;
                int toolbarHeight = searchView.getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    searchView.setBackgroundColor(Color.argb((int) alpha, 220, 255, 255));
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    searchView.setBackgroundResource(R.color.colorAccent);
                }
            }
        });
        mXRecyclerView.setArrowImageView(R.drawable.asy);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                searchView.setVisibility(View.GONE);
                initData();    //初始化数据
                adapter.notifyDataSetChanged();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new Thread().sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchView.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                }).start();

                mXRecyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                // load more data here
                addData();  //上拉加载添加数据


                mXRecyclerView.loadMoreComplete();
            }
        });
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://169.254.41.150:9527/guigushangc/atguigu/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        javabeanservice javabeanservice = retrofit.create(javabeanservice.class);

        Call<bean> getresult = javabeanservice.getresult();
        getresult.enqueue(new Callback<bean>() {
            @Override
            public void onResponse(Call<bean> call, Response<bean> response) {
                bean bean = response.body();
            }

            @Override
            public void onFailure(Call<bean> call, Throwable t) {

            }
        });

    }

    private void addData() {
        for (int i = 0; i < 100; i++) {
            arrayList.add(i+"");
        }
    }

    private void initview(View inflate) {
        mXRecyclerView   = inflate.findViewById(R.id.mxrc);
        searchView = inflate.findViewById(R.id.searchView);
        ib = inflate.findViewById(R.id.zd);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mXRecyclerView.setLayoutManager(linearLayoutManager);
        lisner();
    }

    private void lisner() {
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mXRecyclerView.scrollToPosition(0);
            }
        });
    }

}
