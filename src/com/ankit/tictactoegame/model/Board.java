package com.ankit.tictactoegame.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
        if (!isValidPosition(row, col) || board[row][col] != null) {
            return false;
        }

        board[row][col] = playingPiece;
        return true;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size &&
                col >= 0 && col < size;
    }

    public void printBoard() {
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (board[row][col] != null) {
                    System.out.print(board[row][col].getPieceType().name() + "|");
                } else {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    public List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int row = 0; row < size; row ++) {
            for (int col = 0; col < size; col ++) {
                if (board[row][col] == null) {
                    list.add(new Pair<Integer, Integer>(row, col));
                }
            }
        }
        return list;
    }

    public int getSize() {
        return size;
    }

    public PlayingPiece getPiece(int row, int col) {
        return board[row][col];
    }
}
