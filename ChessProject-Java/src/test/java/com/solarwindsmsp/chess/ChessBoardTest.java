package com.solarwindsmsp.chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;

// replaced junit4 with junit5

public class ChessBoardTest {

    // renaming to testChessBoard because testSubject is a bit confusing
    private static ChessBoard testChessBoard;

    @BeforeAll
    public static void setUp() {
        testChessBoard = ChessBoard.getInstance();
    }

    @AfterEach
    public void cleanUp() {
        testChessBoard.clean();
    }

    @Test
    public void testIs_MaxBoardWidth_of_7() {
        // it was the same test as bellow and the method name says Width so it should test the WIDTH instead
        Assertions.assertEquals(7, ChessBoard.MAX_BOARD_WIDTH);
    }

    @Test
    public void testIs_MaxBoardHeight_of_7() {
        Assertions.assertEquals(7, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(0, 0);
        Assertions.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(5, 5);
        Assertions.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(11, 5);
        // should assert false because xCoordinate 11 is not valid
        Assertions.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(0, 9);
        Assertions.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(11, 0);
        Assertions.assertFalse(isValidPosition);
    }

    @Test
    // changed the name to match with the other upper test names
    public void testIsLegalBoardPosition_False_X_equals_5_Y_equals_negativeValue() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(5, -1);
        Assertions.assertFalse(isValidPosition);
    }

    // added more coverage tests
    @Test
    public void testIsLegalBoardPosition_False_X_equals_negativeValue_Y_equals_6() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(-5, 6);
        Assertions.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_negativeValue_Y_equals_negativeValue() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(-4, -3);
        Assertions.assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_12_Y_equals_15() {
        boolean isValidPosition = testChessBoard.isLegalBoardPosition(12, 15);
        Assertions. assertFalse(isValidPosition);
    }

    // since
    @Test
    public void test_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testChessBoard.add(firstPawn, 6, 3, PieceColor.BLACK);
        testChessBoard.add(secondPawn, 6, 3, PieceColor.BLACK);
        Assertions.assertEquals(6, firstPawn.getXCoordinate());
        Assertions.assertEquals(3, firstPawn.getYCoordinate());
        Assertions.assertEquals(-1, secondPawn.getXCoordinate());
        Assertions. assertEquals(-1, secondPawn.getYCoordinate());

        // i think the correct assert should compare the coordinates of the first and second pawn
        Assertions.assertNotEquals(firstPawn.getXCoordinate(), secondPawn.getXCoordinate());
        Assertions.assertNotEquals(firstPawn.getYCoordinate(), secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testChessBoard.add(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1)
            {
                Assertions.assertEquals(6 + row, pawn.getXCoordinate());
                Assertions.assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                Assertions.assertEquals(-1, pawn.getXCoordinate());
                Assertions.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }

    @Test
    public  void testBoardPosition_Free() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        testChessBoard.add(pawn, 1, 3, PieceColor.BLACK);
        Assertions.assertTrue(testChessBoard.isBoardPositionFree(1,2));
    }

    @Test
    public  void testBoardPosition_NotFree() {
        Pawn pawn = new Pawn(PieceColor.BLACK);
        testChessBoard.add(pawn, 1, 3, PieceColor.BLACK);
        Assertions.assertFalse(testChessBoard.isBoardPositionFree(1,3));
    }
}