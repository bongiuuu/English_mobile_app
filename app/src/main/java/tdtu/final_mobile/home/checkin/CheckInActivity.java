package tdtu.final_mobile.home.checkin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.QuizCate;
import tdtu.final_mobile.data.request.CheckIn;
import tdtu.final_mobile.databinding.ActivityCheckinBinding;
import tdtu.final_mobile.databinding.ActivityQuizzesBinding;
import tdtu.final_mobile.home.quiz.QuizActivity;
import tdtu.final_mobile.home.quiz.QuizAdapter;
import tdtu.final_mobile.presentation.BaseActivity;

public class CheckInActivity extends BaseActivity {

    private ActivityCheckinBinding binding;
    @Override
    protected void doBusiness() {

        CheckIn checkIn = new CheckIn();
        checkIn.setDay("Tue");
        checkIn.setUserId(1);
        Call<CheckIn> call = apiInterface.checkIn(checkIn);
        call.enqueue(new Callback<CheckIn>() {
            @Override
            public void onResponse(Call<CheckIn> call, Response<CheckIn> response) {

            }

            @Override
            public void onFailure(Call<CheckIn> call, Throwable t) {
                call.cancel();
            }
        });

        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityCheckinBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public void getAllDayOfWeek() {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        String[] days = new String[7];
        for (int i = 0; i < 6; i++) {
            days[i] = format.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Log.d("hailpt", "== " + calendar.getTime());
        }

    }
}