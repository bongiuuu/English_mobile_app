package tdtu.final_mobile.home.progress.already_known;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tdtu.final_mobile.R;
import tdtu.final_mobile.home.progress.already_known.Word;
import tdtu.final_mobile.home.progress.already_known.WordViewHolder;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private final List<Word> words;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public WordAdapter(List<Word> words, Context context){
        this.words = words;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = layoutInflater.inflate(R.layout.custom_word_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView)parent, v));
        return new WordViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = this.words.get(position);

        holder.tvEnglish.setText(word.getEnglishWord());
        holder.tvVietnamese.setText(word.getVietnameseWord());
    }

    @Override
    public int getItemCount() {
        return this.words.size();
    }

    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Word word  = this.words.get(itemPosition);

        Toast.makeText(this.context, word.getEnglishWord(), Toast.LENGTH_SHORT).show();
    }
}
