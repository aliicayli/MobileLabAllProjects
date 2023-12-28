package com.example.week9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.week9.BoardPresenter;
import com.example.week9.BoardView;
import com.example.week9.R;

public class MainActivity extends AppCompatActivity implements BoardView {

    TableLayout board;
    BoardPresenter boardPresenter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = findViewById(R.id.board);
        boardPresenter = new BoardPresenter(this);


        for(byte row= 0; row<3; row++){
            TableRow tableRow = (TableRow) board.getChildAt(row);
            for(byte col = 0; col<3; col++){
                Button button = (Button) tableRow.getChildAt(col);
                BoardPresenter.CellClickListener cellClickListener =
                        new BoardPresenter.CellClickListener(boardPresenter, row, col);
                button.setOnClickListener(cellClickListener);
            }
        }



    }

    @Override
    public void newGame() {
        TableLayout boardView = findViewById(R.id.board);
        for (byte row = 0; row < 3; row++) {
            TableRow tableRow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col < 3; col++) {
                Button button = (Button) tableRow.getChildAt(col);
                button.setText("");
                button.setEnabled(true);
            }

        }
    }

    @Override
    public void putSymbol (char symbol, byte row, byte col){
        TableRow tableRow = (TableRow) board.getChildAt(row);
        Button button = (Button) tableRow.getChildAt(col);
        button.setText(Character.toString(symbol));


    }
    @Override
    public void gameEnded (byte winner){
        TableLayout boardView = findViewById(R.id.board);
        for(byte row = 0; row<3; row++) {
            TableRow tableRow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col < 3; col++) {
                Button button = (Button) tableRow.getChildAt(col);
                button.setText("");
                button.setEnabled(false);
            }
        }

        switch (winner) {
            case BoardView.DRAW:
                Toast.makeText(this,"Game is DRAW",Toast.LENGTH_LONG).show();
                break;
            case BoardView.PLAYER_1_WINNER:
                Toast.makeText(this,"Player 1 wins",Toast.LENGTH_LONG).show();
                break;
            case BoardView.PLAYER_2_WINNER:
                Toast.makeText(this,"Player 2 wins",Toast.LENGTH_LONG).show();
                break;

        }



    }


    @Override
    public void invalidPlay ( byte row, byte col){
        Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show();
    }

}