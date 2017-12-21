package kiosk.ddc.a3nomdev.myapplication.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.endPoint.ddcEndpoint;
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

    private static Retrofit retrofit;

    private static ddcEndpoint ddcEndPoint;

    private static ddcService DDCService=null;

    private static String url;

     private  ddcService(String url)
     {
         this.url=url;
         Create();
     }


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
                    .baseUrl(url)
                    .build();

            ddcEndPoint = retrofit.create(ddcEndpoint.class);
        }
    }


    public static ddcService getService(String url)
    {
        if(DDCService==null)
            DDCService=new ddcService(url);

        return DDCService;
    }

    public static Observable<List<User>> Get(String search, String type)
    {

        return  ddcEndPoint.Get(search,type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<User>> GetFriends(int id, int familyId)
    {

        return  ddcEndPoint.GetFriends(id,familyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<String> Post(String kioskId, ArrayList<Integer> value)
    {


        return  ddcEndPoint.Post(kioskId,value)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<String> createKiosk(String kioskId, String description)
    {


        return  ddcEndPoint.createKiosk(kioskId,description)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
