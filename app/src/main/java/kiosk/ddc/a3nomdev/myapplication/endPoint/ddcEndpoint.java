package kiosk.ddc.a3nomdev.myapplication.endPoint;



import kiosk.ddc.a3nomdev.myapplication.model.GitHub;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 3nomdev on 10/18/17.
 */

public interface ddcEndpoint {

    String SERVICE_ENDPOINT = "https://api.github.com/";

    @GET("users/{username}")
    Observable<GitHub> getUserData(@Path("username") String username);

}
