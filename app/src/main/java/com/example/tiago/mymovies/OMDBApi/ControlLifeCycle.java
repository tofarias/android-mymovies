package com.example.tiago.mymovies.OMDBApi;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.IntentFilter;
import android.util.Log;

import com.example.tiago.mymovies.Service.WifiReceiver;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControlLifeCycle extends Application implements LifecycleObserver {

    public static OMDBService service;

    private WifiReceiver receiver = new WifiReceiver();

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OMDBService.class);

        registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop(){
        unregisterReceiver(receiver);
    }

}
