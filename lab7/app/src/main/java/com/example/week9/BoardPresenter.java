package com.example.week9;

import android.view.View;

public class BoardPresenter implements BoardListener{
    private  BoardView boardView;
    private Board board;

    public BoardPresenter(BoardView boardView) {
        this.boardView = boardView;
        board = new Board(this);
    }

    public void move(byte row, byte col){
        board.move(row,col);
    }

    @Override
    public void playedAt(byte player, byte row, byte col) {
        if(player == BoardListener.PLAYER_1){
            boardView.putSymbol(BoardView.PLAYER_1_SYMBOL, row, col);
        }else if(player == BoardListener.PLAYER_2){
            boardView.putSymbol(BoardView.PLAYER_2_SYMBOL, row, col);
        }

    }

    @Override
    public void gameEnded(byte winner) {
        switch (winner){
            case BoardListener.NO_ONE:
                boardView.gameEnded(BoardView.DRAW);
            case BoardListener.PLAYER_1:
                boardView.gameEnded(BoardView.PLAYER_1_WINNER);
            case BoardListener.PLAYER_2:
                boardView.gameEnded(BoardView.PLAYER_2_WINNER);
        }
    }

    @Override
    public void invalidPlay(byte row, byte col) {
        boardView.invalidPlay(row, col);
    }


    public static class CellClickListener implements View.OnClickListener{
        BoardPresenter boardPresenter;

        public CellClickListener(BoardPresenter boardPresenter, byte row, byte col) {
            this.boardPresenter = boardPresenter;
            this.row = row;
            this.col = col;
        }

        byte row, col;
        @Override
        public void onClick(View view) {
            boardPresenter.move(row, col);

        }
    }
}