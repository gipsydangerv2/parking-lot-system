package com.ankit.tictactoegame;

import com.ankit.tictactoegame.model.TicTacToeGame;

public class TicTacToeClient {
    public static void main(String[] args) {
        System.out.println("<<<<<<<<<<<<<<< TicTacToe Game >>>>>>>>>>>>>>>>>>>>");
        TicTacToeGame game = new TicTacToeGame();
        System.out.println("game winner is " + game.startGame());
    }
}
