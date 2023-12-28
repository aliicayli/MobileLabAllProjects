import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.msku.example.myapplication.placeholder.Movie;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnMovieSelected {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            findViewById(R.id.fragment_container_land).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.fragment_container_land).setVisibility(View.GONE);
        }

    }

    @Override
    public void movieSelected(Movie movie) {

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape mode: Use dual-pane layout
            MovieDetailFragment detailFragment = MovieDetailFragment.newInstance(movie);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_land, detailFragment)
                    .commit();
        } else {
            // Portrait mode: Start a new activity
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra("selected_movie", movie);
            startActivity(intent);
        }

    }
}