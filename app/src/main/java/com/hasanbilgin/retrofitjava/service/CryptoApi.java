package com.hasanbilgin.retrofitjava.service;

import com.hasanbilgin.retrofitjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableAny;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoApi {

    //GET,POST,UPDATE,DELETE

    //URL BASE->https://raw.githubusercontent.com/
    //GET-> atilsamancioglu/K21-JSONDataSet/master/crypto.json


    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    //Call<List<CryptoModel>> getData();
    //Observable rxjava olması şart
    Observable<List<CryptoModel>>  getData();

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json


}
