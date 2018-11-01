package com.example.my_shopping2.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.my_shopping2.activity.myView.BlankFragment;
import com.example.my_shopping2.activity.myView.BlankFragment2;
import com.example.my_shopping2.activity.myView.BlankFragment3;
import com.example.my_shopping2.activity.myView.BlankFragment4;
import com.example.my_shopping2.activity.myView.BlankFragment5;
import com.example.my_shopping2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
// Glide.with(this).load("gif地址").centerCrop().into(iv_gif);//没有加预加载
//Glide.with(this).load("gif地址").)
// .centerCrop().placeholder(R.drawable.ic_launcher).crossFade().into(iv_gif);//有预加载
    private ViewPager vp;
    private TabLayout tb;
    ArrayList<Fragment> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final String[] name ={"首页","分类","发现","购物车","我的"};
        final int[] pic = {R.drawable.sy,R.drawable.fenlei,R.drawable.faxian,R.drawable.gouwuche,R.drawable.wode};
        final int[] pic2 = {R.drawable.sy2,R.drawable.fenlei2,R.drawable.faxian2,R.drawable.gouwuche2,R.drawable.wode2};

        tb.setupWithViewPager(vp);
        arrayList = new ArrayList<>();
        arrayList.add(new BlankFragment());
        arrayList.add(new BlankFragment2());
        arrayList.add(new BlankFragment3());
        arrayList.add(new BlankFragment4());
        arrayList.add(new BlankFragment5());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return arrayList.get(i);
            }
            @Override
            public int getCount() {
                return arrayList.size();
            }
        });
        for (int i = 0; i < arrayList.size(); i++) {
            tb.getTabAt(i).setText(name[i]).setIcon(pic[i]);

        }
        tb.getTabAt(0).setIcon(pic2[0]);


        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tb.getTabAt(tab.getPosition()).setIcon(pic2[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tb.getTabAt(tab.getPosition()).setIcon(pic[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tb = (TabLayout) findViewById(R.id.tb);
    }
}
