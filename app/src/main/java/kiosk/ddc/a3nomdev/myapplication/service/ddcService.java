package kiosk.ddc.a3nomdev.myapplication.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.endPoint.ddcEndpoint;
import kiosk.ddc.a3nomdev.myapplication.model.Id;
import kiosk.ddc.a3nomdev.myapplication.model.User;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 3nomdev on 10/18/17.
 */

public class ddcService {
    static Retrofit retrofit;
    static ddcEndpoint ddcEndPoint;

    private static void Create()
    {
        if(retrofit==null)
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(ddcEndpoint.SERVICE_ENDPOINT)
                    .build();

            ddcEndPoint = retrofit.create(ddcEndpoint.class);
        }
    }

    public static Observable<List<User>> Get(String search, String type)
    {
        Create();
        return  ddcEndPoint.Get(search,type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<User>> GetFriends(int id, int reservationId)
    {
        Create();
        return  ddcEndPoint.GetFriends(id,reservationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<String> Post(int reservationId, ArrayList<Integer> value)
    {

        Create();
        return  ddcEndPoint.Post(reservationId,value)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
