package com.ankit.tictactoegame.model;

import com.ankit.tictactoegame.enums.PieceType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    private Deque<Player> players;
    private Board gameBoard;
    private final Scanner scanner = new Scanner(System.in);

    public TicTacToeGame() {
        initializeGame();
    }
    private void initializeGame() {
        players = new LinkedList<>();

        PlayingPiece crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player 1", crossPiece);

        PlayingPiece zeroPiece = new PlayingPieceO();
        Player player2 = new Player("Player 2", zeroPiece);

        players.add(player1);
        players.add(player2);

        // initialize Board of size 3.
        gameBoard = new Board(3);
    }

    // orchestrator
    public String startGame() {
        boolean noWinner = true;

        while(noWinner) {

            // remove the player whose turn is and also put the player in the list back.
            Player currentPlayer = players.removeFirst();

            // Get the free space from the board
            gameBoard.printBoard();

            List<Pair<Integer, Integer>> freeSpaces = gameBoard.getFreeCells();
            if (freeSpaces.isEmpty()) {
                noWinner = false;
                continue;
            }

            // Read the user input
            System.out.println(currentPlayer.getName() +
                    "[" + currentPlayer.getPlayingPiece().getPieceType()+ "] - Please enter [row, column]: ");

            try {
                String[] values = scanner.nextLine().split(",");

                if (values.length != 2) {
                    System.out.println("Invalid input. Use row,column");
                    players.addLast(currentPlayer);
                    continue;
                }

                int inputRow = Integer.valueOf(values[0]);
                int inputColumn = Integer.valueOf(values[1]);

                // Place the piece on the board
                boolean validMove = gameBoard.addPiece(inputRow, inputColumn, currentPlayer.getPlayingPiece());
                if (!validMove) {
                    // Invalid Move: Player cannot put the piece in this cell, player has to choose another cell
                    System.out.println("Incorrect position chosen, try again !!!");
                    players.add(currentPlayer);
                    continue;
                }
                players.addLast(currentPlayer);

                // check if the valid move is a winning move or not
                boolean isWinner = checkForWinner(inputRow, inputColumn, currentPlayer.getPlayingPiece().getPieceType());
                if (isWinner) {
                    return currentPlayer.getName();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter valid numbers");
                players.addLast(currentPlayer);
            }
        }
        return "Tie";
    }

    private boolean checkForWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // checkRow
        for (int idx = 0; idx < gameBoard.getSize(); idx++) {
            PlayingPiece cellPiece = gameBoard.getPiece(row, idx);
            if (cellPiece == null || cellPiece.getPieceType() != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // checkColumn
        for (int idx = 0; idx < gameBoard.getSize(); idx++) {
            PlayingPiece cellPiece = gameBoard.getPiece(idx, column);
            if (cellPiece == null || cellPiece.getPieceType() != pieceType) {
                columnMatch = false;
                break;
            }
        }

        // checkDiagonally
        for (int idx = 0, idx2 = 0; idx < gameBoard.getSize(); idx++, idx2 ++) {
            PlayingPiece cellPiece = gameBoard.getPiece(idx, idx2);
            if (cellPiece == null || cellPiece.getPieceType() != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        // check Anti-Diagonally
        for (int idx = 0, idx2 = gameBoard.getSize() - 1; idx < gameBoard.getSize(); idx++, idx2 --) {
            PlayingPiece cellPiece = gameBoard.getPiece(idx, idx2);
            if (cellPiece == null || cellPiece.getPieceType() != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
