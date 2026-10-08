package com.ankit.tictactoegame.model;

import com.ankit.tictactoegame.enums.PieceType;

public class PlayingPiece {
    private PieceType pieceType;

    public PlayingPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
