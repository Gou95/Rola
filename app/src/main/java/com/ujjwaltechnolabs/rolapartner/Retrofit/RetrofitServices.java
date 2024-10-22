package com.ujjwaltechnolabs.rolapartner.Retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ujjwaltechnolabs.rolapartner.Utils.ApiUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitServices {
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit retrofit=null;

    public static Retrofit getRetrofit() {
 
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor ();
        httpLoggingInterceptor.setLevel (HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder ()
                .addInterceptor (httpLoggingInterceptor).build ();

        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder()
                    .baseUrl(ApiUtils.BASE_URL_TWO)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }
}
