//package tdtu.final_mobile.presentation;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//
//import tdtu.final_mobile.R;
//import tdtu.final_mobile.databinding.ActivityPracticeBinding;
//
//public class PracticeActivity extends BaseActivity {
//    private ActivityPracticeBinding binding;
//
//    @Override
//    public void doBusiness() {
//
//    }
//
//    @Override
//    public View layoutId() {
//        binding = ActivityPracticeBinding.inflate(getLayoutInflater());
//        return binding.getRoot();
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityPracticeBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(view);
//
//        binding.tvName.setText("Using Binding");
//    }
//}