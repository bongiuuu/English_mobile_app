package tdtu.final_mobile.home.checkin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.R;
import tdtu.final_mobile.data.request.CheckIn;
import tdtu.final_mobile.databinding.ActivityCheckinBinding;
import tdtu.final_mobile.home.HomeActivity;
import tdtu.final_mobile.login_register.MainActivity;
import tdtu.final_mobile.presentation.BaseActivity;
import tdtu.final_mobile.utils.Constants;

public class CheckInActivity extends BaseActivity {

    private ActivityCheckinBinding binding;
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private int userId = 0;

    @Override
    protected void doBusiness() {
        SharedPreferences prefs = getSharedPreferences(Constants.KEY_USER_NAME, MODE_PRIVATE);
        userId = prefs.getInt(Constants.KEY_USER_ID, 1);
        doCheckIn();
        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    protected View layoutId() {
        binding = ActivityCheckinBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public void doCheckIn() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String currentDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                currentDay = "Sun";
                break;
            case Calendar.MONDAY:
                currentDay = "Mon";
                break;
            case Calendar.TUESDAY:
                currentDay = "Tue";
                break;
            case Calendar.WEDNESDAY:
                currentDay = "Wed";
                break;
            case Calendar.THURSDAY:
                currentDay = "Thur";
                break;
            case Calendar.FRIDAY:
                currentDay = "Fri";
                break;
            case Calendar.SATURDAY:
                currentDay = "Sat";
                break;
        }
        Constants.CURRENT_DAY = currentDay;
        CheckIn checkIn = new CheckIn();
        checkIn.setDay(currentDay);
        checkIn.setUserId(userId);
        Call<CheckIn> call = apiInterface.checkIn(checkIn);
        call.enqueue(new Callback<CheckIn>() {
            @Override
            public void onResponse(Call<CheckIn> call, Response<CheckIn> response) {
                if(response.isSuccessful()) {
                    getCheckIns();
                }
            }

            @Override
            public void onFailure(Call<CheckIn> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void getCheckIns() {

        Call<CheckIn> call = apiInterface.getCheckIn(userId);
        call.enqueue(new Callback<CheckIn>() {
            @Override
            public void onResponse(Call<CheckIn> call, Response<CheckIn> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    showCheckInIcon(response.body().getDay());
                }
            }

            @Override
            public void onFailure(Call<CheckIn> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void showCheckInIcon(String days) {
        if (days.contains(Constants.MONDAY)) {
            binding.ivMonday.setImageResource(R.drawable.date_checked);
        }

        if (days.contains(Constants.TUESDAY)) {
            binding.ivTuesday.setImageResource(R.drawable.date_checked);
        }

        if (days.contains(Constants.WEDNESDAY)) {
            binding.ivWednesday.setImageResource(R.drawable.date_checked);
        }

        if (days.contains(Constants.THURSDAY)) {
            binding.ivThursday.setImageResource(R.drawable.date_checked);
        }

        if (days.contains(Constants.FRIDAY)) {
            binding.ivFriday.setImageResource(R.drawable.date_checked);
        }

        if (days.contains(Constants.SATURDAY)) {
            binding.ivSaturday.setImageResource(R.drawable.date_checked);
        }

        if (days.contains(Constants.SUNDAY)) {
            binding.ivSunday.setImageResource(R.drawable.date_checked);
        }

        setTodayIcon();
    }

    public void setTodayIcon() {

        imageViews.add(binding.ivMonday);
        imageViews.add(binding.ivTuesday);
        imageViews.add(binding.ivWednesday);
        imageViews.add(binding.ivThursday);
        imageViews.add(binding.ivFriday);
        imageViews.add(binding.ivSaturday);
        imageViews.add(binding.ivSunday);

        ArrayList<String> arrDays = new ArrayList<>();
        arrDays.add(Constants.MONDAY);
        arrDays.add(Constants.TUESDAY);
        arrDays.add(Constants.WEDNESDAY);
        arrDays.add(Constants.THURSDAY);
        arrDays.add(Constants.FRIDAY);
        arrDays.add(Constants.SATURDAY);
        arrDays.add(Constants.SUNDAY);

        for (int i = 0; i < arrDays.size(); i++) {
            if(arrDays.get(i).equals(Constants.CURRENT_DAY)) {
                imageViews.get(i).setImageResource(R.drawable.date_unchecked);
            }
        }
    }
}