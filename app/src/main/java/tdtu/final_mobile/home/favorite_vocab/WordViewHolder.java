package tdtu.final_mobile.home.favorite_vocab;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.final_mobile.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    TextView tvEnglish;
    TextView tvVietnamese;

    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvEnglish = itemView.findViewById(R.id.tvEnglish);
        this.tvVietnamese = itemView.findViewById(R.id.tvVietnamese);
    }
}
