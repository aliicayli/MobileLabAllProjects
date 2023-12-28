import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> cardTexts = new ArrayList<>();
        cardTexts.add("Card 1");
        cardTexts.add("Card 2");
        cardTexts.add("Card 3");

        List<Integer> cardImages = new ArrayList<>();
        cardImages.add(R.drawable.ic_launcher_background);
        cardImages.add(R.drawable.ic_launcher_background);
        cardImages.add(R.drawable.ic_launcher_background);

        CardAdapter adapter = new CardAdapter(cardTexts, cardImages);
        recyclerView.setAdapter(adapter);
    }
}