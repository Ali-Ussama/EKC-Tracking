package com.ekc.ekctracking.api_network;

import com.ekc.ekctracking.models.pojo.StatusRoot;
import com.ekc.ekctracking.models.pojo.User;
import com.ekc.ekctracking.models.findTrip.FindTrip;
import com.ekc.ekctracking.models.hereMapRoutModel.HRoute;
import com.ekc.ekctracking.models.pojo.carType.CarByType;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkAPIS {

    String JSON_CONTENT_TYPE = "Content-Type: application/json";

    @FormUrlEncoded
    @POST("token")
    Observable<User> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @GET("api/Cars/variousCars")
    Observable<StatusRoot> getOnGoingStatus(@Header("Authorization") String auth);

    @GET("api/Cars/finTripAuthFull/{gps_unit_number}/{from_date}{from_time}/{to_date}{to_time}/0")
    Observable<FindTrip> findTrip(@Header("Authorization") String token, @Path("gps_unit_number") String gpsUnitNo,
                                  @Path("from_date") String fromDate, @Path("from_time") String fromTime,
                                  @Path("to_date") String toDate, @Path("to_time") String toTime);

    //https://route.ls.hereapi.com/routing/7.2/calculateroute.json?
    @GET("calculateroute.json?")
    Observable<HRoute> getHereRouting(@Query("waypoint0") String wayPoint0, @Query("waypoint1") String wayPoint1,
                                      @Query("mode") String mode, @Query("app_id") String app_id,
                                      @Query("app_code") String app_code);

    @GET("api/Cars/byType/0")
    Observable<CarByType> getCarByType(@Header("Authorization") String auth);

}
