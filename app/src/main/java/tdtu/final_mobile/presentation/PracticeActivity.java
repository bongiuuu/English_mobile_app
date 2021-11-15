package tdtu.final_mobile.presentation;

import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.final_mobile.data.response.MultipleResource;
import tdtu.final_mobile.databinding.ActivityPracticeBinding;
import tdtu.final_mobile.network.APIClient;
import tdtu.final_mobile.network.APIService;

public class PracticeActivity extends BaseActivity {
    private ActivityPracticeBinding binding;
    APIService apiInterface;
    @Override
    public void doBusiness() {
        FirebaseApp.initializeApp(this);
        apiInterface = APIClient.getClient().create(APIService.class);
        Call<MultipleResource> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {

                Log.d("TAG",response.code()+"");

                String displayResponse = "";
                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                for (MultipleResource.Datum datum : datumList) {
                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                }

//                binding.tvName.setText(displayResponse);

            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
                call.cancel();
            }
        });

        binding.tvName.setOnClickListener(v -> testFireBase());

    }

    @Override
    public View layoutId() {
        binding = ActivityPracticeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public void testFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference testRef = database.getReference("message");
        testRef.setValue("Bong Test");
    }
}