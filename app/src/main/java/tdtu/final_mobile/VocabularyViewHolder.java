package tdtu.final_mobile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class VocabularyViewHolder extends RecyclerView.ViewHolder {
    View vCircle;
    TextView tvNumber;
    TextView tvVocabularyName;
    TextView tvVietnameseName;
    CardView cvVocabulary;

    public VocabularyViewHolder(@NonNull View itemView){
        super(itemView);
        this.vCircle = itemView.findViewById(R.id.vCircle);
        this.tvNumber = itemView.findViewById(R.id.tvNumber);
        this.tvVocabularyName = itemView.findViewById(R.id.tvVocabularyName);
        this.tvVietnameseName = itemView.findViewById(R.id.tvVietnameseName);
        this.cvVocabulary = itemView.findViewById(R.id.cvVocabulary);
    }
}