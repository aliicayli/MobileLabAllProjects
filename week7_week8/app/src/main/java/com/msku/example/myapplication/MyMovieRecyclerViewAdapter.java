import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msku.example.myapplication.placeholder.Movie;

import java.util.List;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> movies;
    private final MovieFragment.OnMovieSelected movieClickListener;
    private int selectedIndex = -1; // Initialize with an invalid index

    public MyMovieRecyclerViewAdapter(List<Movie> items, MovieFragment.OnMovieSelected listener) {
        movies = items;
        movieClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = movies.get(position);
        holder.mIdView.setText(Integer.toString(position));
        holder.mContentView.setText(movies.get(position).getName());

        if (selectedIndex == position) {
            holder.itemView.setBackgroundColor(Color.GREEN);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.view.setOnClickListener(view -> {
            if (movieClickListener != null) {
                movieClickListener.movieSelected(holder.mItem);
                int previousIndex = selectedIndex;
                selectedIndex = holder.getLayoutPosition();
                notifyItemChanged(previousIndex);
                notifyItemChanged(selectedIndex);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final View view;
        public final TextView mContentView;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            this.view = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
