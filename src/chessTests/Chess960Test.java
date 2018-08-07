package chessTests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chessGame.Bishop;
import chessGame.GameType;
import chessGame.King;
import chessGame.Rook;
import chessGame.StandardBoard;
import chessGame.Board.Color;
import junit.framework.TestCase;

public class Chess960Test extends TestCase {
	
	public void testValidWhiteBishopPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int b1 = 0, b2 = 0;
		Bishop whiteBishop1 = null, whiteBishop2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(0,1,2,3,4,5,6,7);
			Collections.shuffle(positions);
			b1 = positions.get(0);
			b2 = positions.get(1);
			
			if((b1 % 2 == 0 && b2 % 2 == 1) || (b1 % 2 == 1 && b2 % 2 == 0)) {
				shuffling = false;
				whiteBishop1 = new Bishop(b1, 0, Color.white, board);
				whiteBishop2 = new Bishop(b2, 0, Color.white, board);
			}
		}
		assertTrue((whiteBishop1.xLocation % 2 == 0 && whiteBishop2.xLocation % 2 == 1) || (whiteBishop1.xLocation % 2 == 1 && whiteBishop2.xLocation % 2 == 0));
	}
	
	public void testInvalidWhiteBishopPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int b1 = 0, b2 = 0;
		Bishop whiteBishop1 = null, whiteBishop2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(1,3,5,7);
			Collections.shuffle(positions);
			b1 = positions.get(0);
			b2 = positions.get(1);
			
			shuffling = false;
			whiteBishop1 = new Bishop(b1, 0, Color.white, board);
			whiteBishop2 = new Bishop(b2, 0, Color.white, board);

		}
		assertFalse((whiteBishop1.xLocation % 2 == 0 && whiteBishop2.xLocation % 2 == 1) || (whiteBishop1.xLocation % 2 == 1 && whiteBishop2.xLocation % 2 == 0));
	}
	
	public void testValidBlackBishopPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int b1 = 0, b2 = 0;
		Bishop blackBishop1 = null, blackBishop2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(0,1,2,3,4,5,6,7);
			Collections.shuffle(positions);
			b1 = positions.get(0);
			b2 = positions.get(1);
			
			if((b1 % 2 == 0 && b2 % 2 == 1) || (b1 % 2 == 1 && b2 % 2 == 0)) {
				shuffling = false;
				blackBishop1 = new Bishop(b1, 7, Color.black, board);
				blackBishop2 = new Bishop(b2, 7, Color.black, board);
			}
		}
		assertTrue((blackBishop1.xLocation % 2 == 0 && blackBishop2.xLocation % 2 == 1) || (blackBishop1.xLocation % 2 == 1 && blackBishop2.xLocation % 2 == 0));
	}
	
	public void testInvalidBlackBishopPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int b1 = 0, b2 = 0;
		Bishop blackBishop1 = null, blackBishop2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(1,3,5,7);
			Collections.shuffle(positions);
			b1 = positions.get(0);
			b2 = positions.get(1);

			shuffling = false;
			blackBishop1 = new Bishop(b1, 7, Color.black, board);
			blackBishop2 = new Bishop(b2, 7, Color.black, board);
		}
		assertFalse((blackBishop1.xLocation % 2 == 0 && blackBishop2.xLocation % 2 == 1) || (blackBishop1.xLocation % 2 == 1 && blackBishop2.xLocation % 2 == 0));
	}
	
	public void testValidWhiteKingPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int king = 0, r1 = 0, r2 = 0;
		King whiteKing = null;
		Rook whiteRook1 = null, whiteRook2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(0,1,2,3,4,5,6,7);
			Collections.shuffle(positions);
			king = positions.get(0);
			r1 = positions.get(1);
			r2 = positions.get(2);
			
			if((king > r1 && king < r2) || (king > r2 && king < r1)) {
				shuffling = false;
				whiteKing = new King(king, 0, Color.white, board);
				whiteRook1 = new Rook(r1, 0, Color.white, board);
				whiteRook2 = new Rook(r2, 0, Color.white, board);
			}
		}
		
		assertTrue((whiteKing.xLocation > whiteRook1.xLocation && whiteKing.xLocation < whiteRook2.xLocation)
					|| (whiteKing.xLocation > whiteRook2.xLocation && whiteKing.xLocation < whiteRook1.xLocation));
	}
	
	public void testInvalidWhiteKingPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int king = 0, r1 = 0, r2 = 0;
		King whiteKing = null;
		Rook whiteRook1 = null, whiteRook2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(1,2,3,4,5,6,7);
			Collections.shuffle(positions);
			r1 = positions.get(1);
			r2 = positions.get(2);
			
			shuffling = false;
			whiteKing = new King(king, 0, Color.white, board);
			whiteRook1 = new Rook(r1, 0, Color.white, board);
			whiteRook2 = new Rook(r2, 0, Color.white, board);
		}
		
		assertFalse((whiteKing.xLocation > whiteRook1.xLocation && whiteKing.xLocation < whiteRook2.xLocation)
				|| (whiteKing.xLocation > whiteRook2.xLocation && whiteKing.xLocation < whiteRook1.xLocation));
	}
	
	public void testValidBlackKingPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int king = 0, r1 = 0, r2 = 0;
		King blackKing = null;
		Rook blackRook1 = null, blackRook2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(0,1,2,3,4,5,6,7);
			Collections.shuffle(positions);
			king = positions.get(0);
			r1 = positions.get(1);
			r2 = positions.get(2);
			
			if((king > r1 && king < r2) || (king > r2 && king < r1)) {
				shuffling = false;
				blackKing = new King(king, 7, Color.black, board);
				blackRook1 = new Rook(r1, 7, Color.black, board);
				blackRook2 = new Rook(r2, 7, Color.black, board);
			}
		}
		
		assertTrue((blackKing.xLocation > blackRook1.xLocation && blackKing.xLocation < blackRook2.xLocation)
					|| (blackKing.xLocation > blackRook2.xLocation && blackKing.xLocation < blackRook1.xLocation));
		
	}
	
	public void testInvalidBlackKingPlacement() {
		StandardBoard board = new StandardBoard(8,8);
		boolean shuffling = true;
		int king = 0, r1 = 0, r2 = 0;
		King blackKing = null;
		Rook blackRook1 = null, blackRook2 = null;
		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(1,2,3,4,5,6,7);
			Collections.shuffle(positions);
			r1 = positions.get(1);
			r2 = positions.get(2);
			
			shuffling = false;
			blackKing = new King(king, 7, Color.black, board);
			blackRook1 = new Rook(r1, 7, Color.black, board);
			blackRook2 = new Rook(r2, 7, Color.black, board);
		}
		
		assertFalse((blackKing.xLocation > blackRook1.xLocation && blackKing.xLocation < blackRook2.xLocation)
				|| (blackKing.xLocation > blackRook2.xLocation && blackKing.xLocation < blackRook1.xLocation));
	}
	
	
}
