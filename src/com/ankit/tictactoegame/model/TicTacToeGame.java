package com.ankit.tictactoegame.model;

import com.ankit.tictactoegame.enums.PieceType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    private Deque<Player> players;
    private Board gameBoard;

    public void initializeGame() {
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
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String[] values = s.split(",");
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
        }
        return "Tie";
    }

    private boolean checkForWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        PlayingPiece[][] board = gameBoard.getBoard();
        // checkRow
        for (int idx = 0; idx < gameBoard.getSize(); idx++) {
            if (board[row][idx] == null || board[row][idx].getPieceType() != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // checkColumn
        for (int idx = 0; idx < gameBoard.getSize(); idx++) {
            if (board[idx][column] == null || board[idx][column].getPieceType() != pieceType) {
                columnMatch = false;
                break;
            }
        }

        // checkDiagonally
        for (int idx = 0, idx2 = 0; idx < gameBoard.getSize(); idx++, idx2 ++) {
            if (board[idx][idx2] == null || board[idx][idx2].getPieceType() != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        // check Anti-Diagonally
        for (int idx = 0, idx2 = gameBoard.getSize() - 1; idx < gameBoard.getSize(); idx++, idx2 --) {
            if (board[idx][idx2] == null || board[idx][idx2].getPieceType() != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
