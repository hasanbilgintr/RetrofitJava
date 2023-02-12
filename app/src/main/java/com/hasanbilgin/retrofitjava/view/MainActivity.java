package com.hasanbilgin.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hasanbilgin.retrofitjava.R;
import com.hasanbilgin.retrofitjava.adapter.RecyclerViewAdapter;
import com.hasanbilgin.retrofitjava.model.CryptoModel;
import com.hasanbilgin.retrofitjava.service.CryptoApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        recyclerView = findViewById(R.id.recyclerView);
        //retrofit &json
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //rxjava kullanımı için bildirdik
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory() derken model ne ise json datasına ona yansıtılcağını bildirmemiz gerekiyor
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        getData();
    }

    private void getData() {
        CryptoApi cryptoApi = retrofit.create(CryptoApi.class);
        compositeDisposable=new CompositeDisposable();

        compositeDisposable.add(cryptoApi.getData()

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));
        /*
        Call<List<CryptoModel>> call = cryptoApi.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()) {
                    cryptoModels=new ArrayList<>(response.body());
                    //Recyclerview
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter=new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);


//                    for (CryptoModel model : response.body()) {
//                        System.out.println(model.paraBirimi + " / " + model.para);
//                    }
                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });*/
    }

    private  void handleResponse(List<CryptoModel> cryptoModelList){
        cryptoModels=new ArrayList<>(cryptoModelList);
        //Recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter=new RecyclerViewAdapter(cryptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}

