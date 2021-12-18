package tdtu.final_mobile.presentation;

import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tdtu.final_mobile.databinding.ActivityPracticeBinding;

public class PracticeActivity extends BaseActivity {
    private ActivityPracticeBinding binding;
    @Override
    public void doBusiness() {
        FirebaseApp.initializeApp(this);
//        binding.tvName.setOnClickListener(v -> testFireBase());
        testFireBase();
//        Call<MultipleResource> call = apiInterface.doGetListResources();
//        call.enqueue(new Callback<MultipleResource>() {
//            @Override
//            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
//
//                Log.d("TAG",response.code()+"");
//
//                String displayResponse = "";
//                MultipleResource resource = response.body();
//                Integer text = resource.page;
//                Integer total = resource.total;
//                Integer totalPages = resource.totalPages;
//                List<MultipleResource.Datum> datumList = resource.data;
//
//                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";
//
//                for (MultipleResource.Datum datum : datumList) {
//                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
//                }
//
////                binding.tvName.setText(displayResponse);
////                binding.tvName.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onFailure(Call<MultipleResource> call, Throwable t) {
//                call.cancel();
//            }
//        });
//
//        binding.tvName.setOnClickListener(v -> testFireBase());

    }

    @Override
    public View layoutId() {
        binding = ActivityPracticeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public void testFireBase() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference testRef = database.getReference("name");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference playersRef = rootRef.child("name").child("cat");

        playersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String lat = dataSnapshot.getValue(String.class);
                binding.tvName.setText(lat);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
}