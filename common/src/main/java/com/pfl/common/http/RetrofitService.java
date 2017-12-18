package com.pfl.common.http;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @FormUrlEncoded
    @POST("account/login")
    Observable<HttpResponse<UserInfo>> login(
            @Field("userId") String userId,
            @Field("password") String password
    );

    @GET("video/getUrl")
    Observable<HttpResponse<String>> getVideoUrl(
            @Query("id") long id
    );

    @FormUrlEncoded
    @POST("user/addVideo")
    Observable<HttpResponse<Boolean>> addVideo(
            @FieldMap Map<String, Object> map
    );
}
