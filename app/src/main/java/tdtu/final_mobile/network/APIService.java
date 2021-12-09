package tdtu.final_mobile.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tdtu.final_mobile.data.Quiz;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.data.Vocab;
import tdtu.final_mobile.data.request.CheckIn;
import tdtu.final_mobile.data.response.BaseResponse;
import tdtu.final_mobile.data.response.MultipleResource;
import tdtu.final_mobile.data.response.User;
import tdtu.final_mobile.home.notification.Notification;
import tdtu.final_mobile.home.vocabulary.Vocabulary;

public interface APIService {

    @GET("unknown")
    Call<MultipleResource> doGetListResources();

    @POST("users/register")
    Call<BaseResponse<User>> createUser(@Body User user);

    @POST("users/login")
    Call<BaseResponse<User>> login(@Body User user);

    @POST("users/google/login")
    Call<User> loginWithGoogle(@Body User user);

    @GET("cates/quizs/{cateId}")
    Call<ArrayList<Quiz>> getQuizs(@Path("cateId") int id);

    @GET("users/cates/{userId}")
    Call<ArrayList<QuizCate>> getQuizCates(@Path("userId") int id);

    @POST("quizs/create")
    Call<Quiz> createQuiz(@Body Quiz user);

    @POST("users/checkin")
    Call<CheckIn> checkIn(@Body CheckIn checkIn);

    @GET("users/checkin/{userId}")
    Call<CheckIn> getCheckIn(@Path("userId") int id);

    @GET("vocabulary/cates")
    Call<ArrayList<QuizCate>> getAllVocabularyCates();

    @GET("vocabulary/cates/{cateId}")
    Call<ArrayList<Vocab>> getVocabularyFromCateId(@Path("cateId") int id);

    @GET("users/notifications")
    Call<ArrayList<Notification>> getNotifications();
}