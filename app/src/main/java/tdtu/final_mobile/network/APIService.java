package tdtu.final_mobile.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import tdtu.final_mobile.data.response.BaseResponse;
import tdtu.final_mobile.data.response.MultipleResource;
import tdtu.final_mobile.data.response.User;

public interface APIService {

    @GET("unknown")
    Call<MultipleResource> doGetListResources();

    @POST("users/register")
    Call<BaseResponse<User>> createUser(@Body User user);

    @POST("users/login")
    Call<BaseResponse<User>> login(@Body User user);


}