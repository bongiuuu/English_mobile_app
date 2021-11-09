package tdtu.final_mobile.home.extra;

import static com.sangcomz.fishbun.define.Define.INTENT_PATH;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;

import java.util.ArrayList;

import tdtu.final_mobile.databinding.ActivityMeBinding;
import tdtu.final_mobile.presentation.BaseActivity;

public class MeActivity extends BaseActivity {


    private ActivityMeBinding binding;

    @Override
    protected View layoutId() {
        binding = ActivityMeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void doBusiness() {
        binding.iBtnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.ivUserProfilePicture.setOnClickListener(view -> {
            FishBun.with(MeActivity.this)
                    .setImageAdapter(new GlideAdapter())
                    .setMaxCount(1).setCamera(true)
                    .startAlbum();
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageData) {
        super.onActivityResult(requestCode, resultCode, imageData);
        if (resultCode == RESULT_OK) {
            ArrayList<Uri> path = imageData.getParcelableArrayListExtra(INTENT_PATH);
            binding.ivUserProfilePicture.setImageURI(path.get(0));
        }
    }
}