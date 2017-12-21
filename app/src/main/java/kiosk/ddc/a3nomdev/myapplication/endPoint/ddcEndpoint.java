package kiosk.ddc.a3nomdev.myapplication.endPoint;


import java.util.List;

import kiosk.ddc.a3nomdev.myapplication.model.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 3nomdev on 10/18/17.
 */

public interface ddcEndpoint {

    @GET("{search}/{type}")
    Observable<List<User>> Get(@Path("search") String search, @Path("type") String type);

    @GET("friends/{id}/{familyId}")
    Observable<List<User>> GetFriends(@Path("id") int id, @Path("familyId") int familyId);

    @POST("{kioskId}")
    Observable<String> Post( @Path("kioskId") String kioskId,@Body List<Integer> value);

    @POST("createKiosk/{guid}/{description}")
    Observable<String> createKiosk( @Path("guid") String guid,@Path("description") String description);
}
