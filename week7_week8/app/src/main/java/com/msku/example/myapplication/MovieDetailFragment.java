import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.msku.example.myapplication.placeholder.Movie;

public class MovieDetailFragment extends Fragment {

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("selected_movie", movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        if (getArguments() != null) {
            Movie selectedMovie = getArguments().getParcelable("selected_movie");
            if (selectedMovie != null) {
                TextView textViewTitle = view.findViewById(R.id.textViewTitle);
                TextView textViewDirector = view.findViewById(R.id.textViewDirector);
                TextView textViewYear = view.findViewById(R.id.textViewYear);
                TextView textViewDescription = view.findViewById(R.id.textViewDescription);

                textViewTitle.setText(selectedMovie.getName());
                textViewDirector.setText("Director: " + selectedMovie.getDirector());
                textViewYear.setText("Year: " + selectedMovie.getYear());
                textViewDescription.setText(selectedMovie.getDescription());
            }
        }
        return view;
    }
}
