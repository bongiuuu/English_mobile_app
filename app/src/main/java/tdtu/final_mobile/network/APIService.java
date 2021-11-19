package tdtu.final_mobile.network;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.data.QuizCate;
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

    @GET("quizs/{id}")
    Call<ArrayList<Quiz>> getQuizs(@Path("id") int id);

    @GET("quizs/cates")
    Call<ArrayList<QuizCate>> getQuizCates();
}