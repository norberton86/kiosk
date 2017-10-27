package kiosk.ddc.a3nomdev.myapplication.endPoint;



import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.model.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 3nomdev on 10/18/17.
 */

public interface ddcEndpoint {

    String SERVICE_ENDPOINT = "http://162.251.239.119/api/myUser/";

    @GET("{search}/{type}")
    Observable<List<User>> Get(@Path("search") String search, @Path("type") String type);

    @GET("friends/{id}/{reservationId}")
    Observable<List<User>> GetFriends(@Path("id") int id, @Path("reservationId") int reservationId);

}
