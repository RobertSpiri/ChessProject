package com.solarwindsmsp.chess;

import org.junit.jupiter.api.*;

public class PawnTest {

    private static ChessBoard chessBoard;
    private static Pawn testBlackPawn;
    private static Pawn testWhitePawn;

    @BeforeEach
    public void setUp() {
        chessBoard = ChessBoard.getInstance();
        testBlackPawn = new Pawn(PieceColor.BLACK);
        testWhitePawn = new Pawn(PieceColor.WHITE);
    }

    @AfterEach
    public void cleanUp() {
        chessBoard.clean();
    }

    @Test
    public void testChessBoard_Add_BlackPawn_Sets_XCoordinate() {
        this.chessBoard.add(testBlackPawn, 6, 3, PieceColor.BLACK);
        Assertions.assertEquals(6, testBlackPawn.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_BlackPawn_Sets_YCoordinate() {
        this.chessBoard.add(testBlackPawn, 6, 3, PieceColor.BLACK);
        Assertions.assertEquals(3, testBlackPawn.getYCoordinate());
    }

    // in the README file it states that X coordinate is the row and with that logic the tests bellow don't make sense
    // so i will change them to match the criteria

    @Test
    public void testPawn_Move_IllegalCoordinates_BlackPawn_Right_DoesNotMove() {
        chessBoard.add(testBlackPawn, 6, 3, PieceColor.BLACK);
        testBlackPawn.Move(MovementType.MOVE, 6, 4);
        Assertions.assertEquals(6, testBlackPawn.getXCoordinate());
        Assertions.assertEquals(3, testBlackPawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_WhitePawn_Right_DoesNotMove() {
        chessBoard.add(testWhitePawn, 1, 3, PieceColor.WHITE);
        testWhitePawn.Move(MovementType.MOVE, 1, 4);
        Assertions.assertEquals(1, testWhitePawn.getXCoordinate());
        Assertions.assertEquals(3, testWhitePawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_BlackPawn_Left_DoesNotMove() {
        chessBoard.add(testBlackPawn, 6, 3, PieceColor.BLACK);
        testBlackPawn.Move(MovementType.MOVE, 6, 2);
        Assertions.assertEquals(6, testBlackPawn.getXCoordinate());
        Assertions.assertEquals(3, testBlackPawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_WhitePawn_Left_DoesNotMove() {
        chessBoard.add(testWhitePawn, 1, 3, PieceColor.WHITE);
        testWhitePawn.Move(MovementType.MOVE, 1, 2);
        Assertions.assertEquals(1, testWhitePawn.getXCoordinate());
        Assertions.assertEquals(3, testWhitePawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_BlackPawn_Forward_UpdatesCoordinates() {
        chessBoard.add(testBlackPawn, 6, 3, PieceColor.BLACK);
        testBlackPawn.Move(MovementType.MOVE, 5, 3);
        Assertions.assertEquals(5, testBlackPawn.getXCoordinate());
        Assertions.assertEquals(3, testBlackPawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_LegalCoordinates_WhitePawn_Forward_UpdatesCoordinates() {
        chessBoard.add(testWhitePawn, 1, 3, PieceColor.WHITE);
        testWhitePawn.Move(MovementType.MOVE, 2, 3);
        Assertions.assertEquals(2, testWhitePawn.getXCoordinate());
        Assertions.assertEquals(3, testWhitePawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_BlackPawn_Backward_DoesNotMove() {
        chessBoard.add(testBlackPawn, 6, 3, PieceColor.BLACK);
        testBlackPawn.Move(MovementType.MOVE, 7, 3);
        Assertions.assertEquals(6, testBlackPawn.getXCoordinate());
        Assertions.assertEquals(3, testBlackPawn.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_WhitePawn_Backward_DoesNotMove() {
        chessBoard.add(testWhitePawn, 1, 3, PieceColor.BLACK);
        testWhitePawn.Move(MovementType.MOVE, 0, 3);
        Assertions.assertEquals(1, testWhitePawn.getXCoordinate());
        Assertions.assertEquals(3, testWhitePawn.getYCoordinate());
    }

    @Test
    public void testPawn_Get_NumberOfPawns_OnePawn() {
        chessBoard.add(testWhitePawn, 1, 3, PieceColor.BLACK);
        Assertions.assertEquals(1, Pawn.getNumberOfPawns(PieceColor.BLACK));
    }

    @Test
    public void testPawn_Get_NumberOfPawns_MultiplePawns() {
        chessBoard.add(testWhitePawn, 1, 3, PieceColor.BLACK);
        chessBoard.add(testWhitePawn, 1, 2, PieceColor.BLACK);
        chessBoard.add(testWhitePawn, 1, 1, PieceColor.BLACK);
        Assertions.assertEquals(3, Pawn.getNumberOfPawns(PieceColor.BLACK));
    }

    @Test
    public void testLimits_Number_of_Black_Pawns_Max_8() {
        testLimits_Number_of_Pawns_Max_8(PieceColor.BLACK);
    }

    @Test
    public void testLimits_Number_of_White_Pawns_Max_8() {
        testLimits_Number_of_Pawns_Max_8(PieceColor.WHITE);
    }

    public void testLimits_Number_of_Pawns_Max_8(PieceColor pieceColor) {
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(pieceColor);
            chessBoard.add(pawn, 1, i, pieceColor);
        }
        Assertions.assertEquals(8, Pawn.getNumberOfPawns(pieceColor));
    }

    @Test
    public void testLimits_Number_of_Black_Pawns_Over_8() {
        testLimits_Number_of_Pawns_Over_8(PieceColor.BLACK);
    }

    @Test
    public void testLimits_Number_of_White_Pawns_Over_8() {
        testLimits_Number_of_Pawns_Over_8(PieceColor.WHITE);
    }

    public void testLimits_Number_of_Pawns_Over_8(PieceColor pieceColor) {
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(pieceColor);
            chessBoard.add(pawn, 1, i, pieceColor);
        }
        Pawn pawn = new Pawn(pieceColor);
        chessBoard.add(pawn, 2, 2, pieceColor);

        Assertions.assertEquals(8, Pawn.getNumberOfPawns(pieceColor));
    }
}