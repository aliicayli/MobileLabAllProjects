import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<String> cardTexts;
    private List<Integer> cardImages;

    public CardAdapter(List<String> cardTexts, List<Integer> cardImages) {
        this.cardTexts = cardTexts;
        this.cardImages = cardImages;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        String text = cardTexts.get(position);
        int imageResource = cardImages.get(position);

        holder.cardTextView.setText(text);
        holder.cardImageView.setImageResource(imageResource);
    }

    @Override
    public int getItemCount() {
        return cardTexts.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView cardTextView;
        ImageView cardImageView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTextView = itemView.findViewById(R.id.text_card);
            cardImageView = itemView.findViewById(R.id.image_card);
        }
    }
}
