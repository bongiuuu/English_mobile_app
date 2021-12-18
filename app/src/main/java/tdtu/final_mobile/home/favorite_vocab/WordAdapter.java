package tdtu.final_mobile.home.favorite_vocab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tdtu.final_mobile.R;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> implements Filterable {
    private final List<Word> words;
    private final Context context;
    private final LayoutInflater layoutInflater;
    private List<Word> wordsFull;

    public WordAdapter(List<Word> words, Context context){
        this.words = words;
        this.wordsFull = new ArrayList<>(words);
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

    }


    // FILTER HERE
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Word> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0){
                filteredList.addAll(wordsFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Word word : wordsFull){
                    if (word.getEnglishWord().toLowerCase().contains(filterPattern)){
                        filteredList.add(word);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            words.clear();
            words.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
