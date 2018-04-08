package com.opop.brazius.worldcuprussia2018predictor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.github.jinatonic.confetti.CommonConfetti;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewAddedListener {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ListAdapter mAdapter;
    private ArrayList<Team> myDataset;
    private String LIST_STATE_KEY = "ListKey";
    private String LIST_ADAPTER_KEY = "adapter_key";
    ListAdapter adapter;
    private Context context;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        MobileAds.initialize(this, getString(R.string.ad_id));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("9B4E29286B48BF606EC7E4767C788368")
                .build();
        //adRequest.isTestDevice(context);
        mAdView.loadAd(adRequest);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use a linear layout manager
        if (savedInstanceState != null) {
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LIST_STATE_KEY));
        } else {
            mLayoutManager = new LinearLayoutManager(this);
        }
        // Add your Sections
        adapter = new ListAdapter(this,this);
        adapter.setHasStableIds(true);

        mRecyclerView.setAdapter(adapter);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelable(LIST_STATE_KEY, mLayoutManager.onSaveInstanceState());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        // Retrieve list state and list/item positions
        if (state != null)
            state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    public void onWinnerAdded() {
        mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
        ViewGroup rootView = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        CommonConfetti.rainingConfetti(rootView, new int[] { Color.RED,Color.GREEN,Color.YELLOW,Color.BLACK,Color.BLUE,Color.DKGRAY })
                .oneShot();
    }

    @Override
    public void onOtherViewAdded() {
        mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }
}
