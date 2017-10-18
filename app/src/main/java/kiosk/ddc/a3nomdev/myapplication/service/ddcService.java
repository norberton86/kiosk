package kiosk.ddc.a3nomdev.myapplication.service;


import kiosk.ddc.a3nomdev.myapplication.endPoint.ddcEndpoint;
import kiosk.ddc.a3nomdev.myapplication.model.GitHub;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

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
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ddcEndpoint.SERVICE_ENDPOINT)
                    .build();

            ddcEndPoint = retrofit.create(ddcEndpoint.class);
        }
    }

    public static Observable<GitHub> getUserData()
    {
        Create();
        return ddcEndPoint.getUserData("ahmedrizwan");
    }
}
