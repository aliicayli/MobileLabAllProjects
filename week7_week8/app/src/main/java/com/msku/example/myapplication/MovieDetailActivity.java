import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.msku.example.myapplication.placeholder.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle extras = getIntent().getExtras();
        Movie movie = extras.getParcelable("selected_movie");
        if (movie != null) {
            MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(movie);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_fragment_container, detailFragment)
                    .commit();
        }
    }
}