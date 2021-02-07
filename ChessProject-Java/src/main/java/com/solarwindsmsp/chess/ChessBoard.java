package com.solarwindsmsp.chess;

// Singleton
public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    // i think chessboard is a better name than pieces because we refer to the coordinates of the chessboard when moving
    // pieces
    private static Pawn[][] chessboard;

    private static ChessBoard instance = null;

    private ChessBoard() {
        chessboard = new Pawn[MAX_BOARD_WIDTH + 1][MAX_BOARD_HEIGHT + 1];
    }

    public static ChessBoard getInstance() {
        if (instance == null) {
            instance = new ChessBoard();
        }

        return instance;
    }

//     it shouldn't be possible to add a piece over another piece
    public void add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        try {
            if (Pawn.getNumberOfPawns(pieceColor) == 8) {
                throw new UnsupportedOperationException("Can't add more than 8 pawns of " + pieceColor + " color") ;
            }

            if (Pawn.isLegalBoardPawnPosition(xCoordinate)
                    && isLegalBoardPosition(xCoordinate, yCoordinate)
                    && isBoardPositionFree(xCoordinate, yCoordinate)) {

                pawn.setXCoordinate(xCoordinate);
                pawn.setYCoordinate(yCoordinate);

                Pawn.incrementNumberOfPawns(pieceColor);
                chessboard[xCoordinate][yCoordinate] = pawn;
            } else {
                pawn.setXCoordinate(-1);
                pawn.setYCoordinate(-1);
                throw new UnsupportedOperationException("Can't add piece over another piece") ;
            }
        } catch (UnsupportedOperationException illegalAdd) {
            System.out.println(illegalAdd);
        }

        pawn.setChessBoard(instance);
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate <= 7 && yCoordinate >= 0 && yCoordinate <= 7;
    }

    public boolean isBoardPositionFree(int xCoordinate, int yCoordinate) {
        return chessboard[xCoordinate][yCoordinate] == null;
    }

    public int clean() {
        int numberOfPieces = 0;
        for (int i = 0; i <= MAX_BOARD_WIDTH; i++) {
            for (int j = 0; j <= MAX_BOARD_HEIGHT; j++) {
                if (chessboard[i][j] != null) {
                    numberOfPieces++;
                    chessboard[i][j] = null;
                }
            }
        }

        Pawn.resetPawnNumbers();
        return numberOfPieces;
    }
}
