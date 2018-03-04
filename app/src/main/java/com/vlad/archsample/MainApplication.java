package com.vlad.archsample;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.vlad.archsample.common.RestAdapter;
import com.vlad.archsample.sample3_mvp_nolifecycle.view.ServiceLocator;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Vlad on 04.02.2018.
 */

public class MainApplication extends Application {

    RestAdapter restAdapter;
    ServiceLocator serviceLocator;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        initRetrofit();
        initServiceLocator();
        Timber.plant(new Timber.DebugTree());
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    // --------------------------------------------------------------------------------------------
    private void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        restAdapter = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))  // Gson
                .baseUrl(RestAdapter.ENDPOINT)
                .client(client)
                .build()
                .create(RestAdapter.class);
    }

    private void initServiceLocator() {
        serviceLocator = new ServiceLocator();
    }
}
