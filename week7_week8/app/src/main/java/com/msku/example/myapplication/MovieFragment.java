import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.msku.example.myapplication.placeholder.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieFragment extends Fragment {

    private OnMovieSelected listener;

    List<Movie> movies = new ArrayList<>();


    public MovieFragment() {
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnMovieSelected){
            listener = (OnMovieSelected) context;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    //main activity şuan listeneri dinliyo
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        movies.add(new Movie("The Shawshank Redemption","Frank Darabont",1994,
                Arrays.asList(new String[]{"Tim Robbins", "Morgan Freeman", "Bob Gunton"}),
                "Two imprisoned men bond over a number of years, " +
                        "finding solace and eventual redemption through acts of common decency."));
        movies.add(new Movie("The Godfather","Francis Ford Coppola",1972,
                Arrays.asList(new String[]{"Marlon Brando", "Al Pacino", " James Caan"}),
                "The aging patriarch of an organized crime dynasty transfers control of his "
                        +
                        "clandestine empire to his reluctant son."));
        movies.add(new Movie("Pulp Fiction","Quentin Tarantino",1994,
                Arrays.asList(new String[]{"John Travolta", "Uma Thurman", "Samuel L. Jackson"}),
                        "The aging patriarch of an organized crime dynasty transfers control of " +
                                "his clandestine empire to his reluctant son."));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyMovieRecyclerViewAdapter(movies,listener));
        }
        return view;
    }

    public interface OnMovieSelected{
        void movieSelected(Movie movie);

    }
}