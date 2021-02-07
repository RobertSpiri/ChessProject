package com.solarwindsmsp.chess;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private static int numberOfWhitePawns = 0;
    private static int numberOfBlackPawns = 0;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public static int getNumberOfPawns(PieceColor pieceColor) {
        if (pieceColor == PieceColor.BLACK)
            return  numberOfBlackPawns;
        else if (pieceColor == PieceColor.WHITE)
            return numberOfWhitePawns;
        else
            return -1;
    }

    public static void resetPawnNumbers() {
        numberOfBlackPawns = 0;
        numberOfWhitePawns = 0;
    }

    public static void incrementNumberOfPawns(PieceColor pieceColor) {
        if (pieceColor == PieceColor.BLACK)
            numberOfBlackPawns++;
        else if (pieceColor == PieceColor.WHITE)
            numberOfWhitePawns++;
    }

    // pawns can't be placed in the first or last raw because in that case a promotion is happening and the pawn is
    // replaced with a queen, a rook, a bishop or a knight
    public static boolean isLegalBoardPawnPosition(int xCoordinate) {
        return xCoordinate > 0 && xCoordinate < 7;
    }

    public void Move(MovementType movementType, int newX, int newY) {
        switch (movementType) {
            case MOVE:
                try {
                    if (newY != yCoordinate)
                        throw new UnsupportedOperationException("Pawn can't move sideways");

                    final int spacesToMove = newX - xCoordinate;

                    // the pawn can move 2 spaces if it's in its first position(1 for WHITE and 6 for BLACK)
                    switch (pieceColor) {
                        case BLACK:
                            // spacesToMove will be always a negative value for a valid move for BLACK because
                            // xCoordinate will go from 6 to 5 to 4 and so on and the difference will be a negative
                            // ex: 5 - 6, 4 - 5
                            if (spacesToMove == -1 || (spacesToMove == -2 && xCoordinate == 6)) {
                                xCoordinate = newX;
                            } else {
                                throw new UnsupportedOperationException("Illegal xCoordinate pawn placement position");
                            }
                            break;

                        case WHITE:
                            // spacesToMove will be always a positive value for a valid move for WHITE because
                            // xCoordinate will go from 1 to 2 to 3 and so on and the difference will be a negative
                            // ex: 2 - 1, 3 - 2
                            if (spacesToMove == 1 || (spacesToMove == 2 && xCoordinate == 1)) {
                                xCoordinate = newX;
                            } else {
                                throw new UnsupportedOperationException("Illegal xCoordinate pawn placement position");
                            }
                            break;
                    }
                } catch (UnsupportedOperationException illegalMove) {
                    System.out.println(illegalMove);
                }

                break;
            case CAPTURE:
                break;
        }
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
