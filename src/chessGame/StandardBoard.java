package chessGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Subclass of a board. Standard version of a chess Board. Has methods for creating a standard
 * chess board and populating it with regular chess pieces.
 * Can be used to create a standard game of chess.
 * @author Pratik Naik
 */
public class StandardBoard extends Board {
	
	/**
	 * Trackers for the white and black kings for check, checkmate and game ending conditions.
	 */
	public King whiteKingTracker;
	public King blackKingTracker;
	
	/**
	 * Method to initialize the chess board.
	 * @param xSquares
	 * @param ySquares
	 */
	public StandardBoard(int xSquares, int ySquares) {

		this.numXSquares = xSquares;
		this.numYSquares = ySquares;
		this.totalSquares = this.numXSquares * this.numYSquares;
		this.squaresList = new Square[this.numXSquares][this.numYSquares];
		populateBoardWithSquares();
		this.whiteKingTracker = null;
		this.blackKingTracker = null;
	}

	/**
	 * Method to populate our board with Squares.
	 * General pattern of white and black squares on the board.
	 */
	public void populateBoardWithSquares() {
		for (int i = 0; i < this.numXSquares; i++) {
			for (int j = 0; j < this.numYSquares; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0)
						squaresList[i][j] = new Square(false, Color.black);
					else
						squaresList[i][j] = new Square(false, Color.white);
				} 
				else {
					if (j % 2 == 0)
						squaresList[i][j] = new Square(false, Color.white);
					else
						squaresList[i][j] = new Square(false, Color.black);
				}
			}
		}
	}
	
	/**
	 * Method to populate our chess board with standard pieces.
	 */
	public void populateBoardWithPieces(GameType gameType) {
		if(gameType == GameType.Normal) {
			normalSetup();
		}
		else if(gameType == GameType.Special) {
			specialSetup();
		}
		else if(gameType == GameType.Chess960) {
			chess960Setup();
		}
	}

	public void normalSetup() {
		setupPawns();
		setupKnights(1, 6);
		setupBishops(2, 5);
		setupRooks(0, 7);
		setupQueens(3);
		setupKings(4);

	}
	
	public void specialSetup() {
		setupPawns();
		setupSpecialPieces();
		setupRooks(0, 7);
		setupQueens(3);
		setupKings(4);
		
	}
	
	public void chess960Setup() {
		boolean shuffling = true;
		int king = 0, queen = 0, k1 = 0, k2 = 0, b1 = 0, b2 = 0, r1 = 0, r2 = 0;
		setupPawns();

		
		while(shuffling) {
			List<Integer> positions = Arrays.asList(0,1,2,3,4,5,6,7);
			Collections.shuffle(positions);
			king = positions.get(0);
			queen = positions.get(1);
			k1 = positions.get(2);
			k2 = positions.get(3);
			b1 = positions.get(4);
			b2 = positions.get(5);
			r1 = positions.get(6);
			r2 = positions.get(7);
			
			if((b1 % 2 == 0 && b2 % 2 == 1) || (b1 % 2 == 1 && b2 % 2 == 0)) {
				if((king > r1 && king < r2) || (king > r2 && king < r1)) {
					shuffling = false;
				}
			}
		}
		
		setupKnights(k1, k2);
		setupBishops(b1, b2);
		setupRooks(r1, r2);
		setupQueens(queen);
		setupKings(king);
		
		
		
	}

	/**
	 * Method to setup Archbishop and Chancellor as special pieces in special game.
	 */
	public void setupSpecialPieces() {
		Archbishop whiteArchbishopOne = new Archbishop(2, 0, Color.white, this);
		Archbishop whiteArchbishopTwo = new Archbishop(5, 0, Color.white, this);
		Archbishop blackArchbishopOne = new Archbishop(2, 7, Color.black, this);
		Archbishop blackArchbishopTwo = new Archbishop(5, 7, Color.black, this);
		this.squaresList[2][0].isOccupied = true;
		this.squaresList[5][0].isOccupied = true;
		this.squaresList[2][0].occupyingPiece = whiteArchbishopOne;
		this.squaresList[5][0].occupyingPiece = whiteArchbishopTwo;
		this.squaresList[2][7].isOccupied = true;
		this.squaresList[5][7].isOccupied = true;
		this.squaresList[2][7].occupyingPiece = blackArchbishopOne;
		this.squaresList[5][7].occupyingPiece = blackArchbishopTwo;
		
		Chancellor whiteKnightOne = new Chancellor(1, 0, Color.white, this);
		Chancellor whiteKnightTwo = new Chancellor(6, 0, Color.white, this);
		Chancellor blackKnightOne = new Chancellor(1, 7, Color.black, this);
		Chancellor blackKnightTwo = new Chancellor(6, 7, Color.black, this);
		this.squaresList[1][0].isOccupied = true;
		this.squaresList[6][0].isOccupied = true;
		this.squaresList[1][0].occupyingPiece = whiteKnightOne;
		this.squaresList[6][0].occupyingPiece = whiteKnightTwo;
		this.squaresList[1][7].isOccupied = true;
		this.squaresList[6][7].isOccupied = true;
		this.squaresList[1][7].occupyingPiece = blackKnightOne;
		this.squaresList[6][7].occupyingPiece = blackKnightTwo;
		
	}

	/**
	 * Setup 8 black and 8 white pawns in their initial positions.
	 */
	public void setupPawns(){
		for(int i = 0; i < 8; i++){
			Pawn newWhitePawn = new Pawn(i, 1, Color.white, this);
			Pawn newBlackPawn = new Pawn(i, 6, Color.black, this);
			this.squaresList[i][1].isOccupied = true;
			this.squaresList[i][6].isOccupied = true;
			this.squaresList[i][1].occupyingPiece = newWhitePawn;
			this.squaresList[i][6].occupyingPiece = newBlackPawn;
			
		}
	}
	
	/**
	 * Setup 2 black rooks and 2 white rooks in their initial positions.
	 */
	public void setupRooks(int r1, int r2){
		Rook whiteRookOne = new Rook(r1, 0, Color.white, this);
		Rook whiteRookTwo = new Rook(r2, 0, Color.white, this);
		Rook blackRookOne = new Rook(r1, 7, Color.black, this);
		Rook blackRookTwo = new Rook(r2, 7, Color.black, this);
		this.squaresList[r1][0].isOccupied = true;
		this.squaresList[r2][0].isOccupied = true;
		this.squaresList[r1][0].occupyingPiece = whiteRookOne;
		this.squaresList[r2][0].occupyingPiece = whiteRookTwo;
		this.squaresList[r1][7].isOccupied = true;
		this.squaresList[r2][7].isOccupied = true;
		this.squaresList[r1][7].occupyingPiece = blackRookOne;
		this.squaresList[r2][7].occupyingPiece = blackRookTwo;
		
	}
	
	/**
	 * Setup 2 black Bishops and 2 white Bishops in their initial positions.
	 */
	public void setupBishops(int b1, int b2){
		Bishop whiteBishopOne = new Bishop(b1, 0, Color.white, this);
		Bishop whiteBishopTwo = new Bishop(b2, 0, Color.white, this);
		Bishop blackBishopOne = new Bishop(b1, 7, Color.black, this);
		Bishop blackBishopTwo = new Bishop(b2, 7, Color.black, this);
		this.squaresList[b1][0].isOccupied = true;
		this.squaresList[b2][0].isOccupied = true;
		this.squaresList[b1][0].occupyingPiece = whiteBishopOne;
		this.squaresList[b2][0].occupyingPiece = whiteBishopTwo;
		this.squaresList[b1][7].isOccupied = true;
		this.squaresList[b2][7].isOccupied = true;
		this.squaresList[b1][7].occupyingPiece = blackBishopOne;
		this.squaresList[b2][7].occupyingPiece = blackBishopTwo;
	}
	
	/**
	 * Setup 2 black Knights and 2 white Knights in their initial positions.
	 */
	public void setupKnights(int k1, int k2){
		Knight whiteKnightOne = new Knight(k1, 0, Color.white, this);
		Knight whiteKnightTwo = new Knight(k2, 0, Color.white, this);
		Knight blackKnightOne = new Knight(k1, 7, Color.black, this);
		Knight blackKnightTwo = new Knight(k2, 7, Color.black, this);
		this.squaresList[k1][0].isOccupied = true;
		this.squaresList[k2][0].isOccupied = true;
		this.squaresList[k1][0].occupyingPiece = whiteKnightOne;
		this.squaresList[k2][0].occupyingPiece = whiteKnightTwo;
		this.squaresList[k1][7].isOccupied = true;
		this.squaresList[k2][7].isOccupied = true;
		this.squaresList[k1][7].occupyingPiece = blackKnightOne;
		this.squaresList[k2][7].occupyingPiece = blackKnightTwo;
	}
	
	/**
	 * Setup 2 queens white and black in their initial positions.
	 */	
	public void setupQueens(int queen){
		Queen whiteQueen = new Queen(queen, 0, Color.white, this);
		Queen blackQueen = new Queen(queen, 7, Color.black, this);
		this.squaresList[queen][0].isOccupied = true;
		this.squaresList[queen][7].isOccupied = true;
		this.squaresList[queen][0].occupyingPiece = whiteQueen;
		this.squaresList[queen][7].occupyingPiece = blackQueen;
	}
	
	/**
	 * Setup 2 queens white and black in their initial positions.
	 */
	public void setupKings(int king){
		King whiteKing = new King(king, 0, Color.white, this);
		King blackKing = new King(king, 7, Color.black, this);
		this.squaresList[king][0].isOccupied = true;
		this.squaresList[king][7].isOccupied = true;
		this.squaresList[king][0].occupyingPiece = whiteKing;
		this.squaresList[king][7].occupyingPiece = blackKing;
		whiteKingTracker = whiteKing;
		blackKingTracker = blackKing;
	}
	
	/**
	 * Helper method to check if locations passed in are mapped on our generated board.
	 * @see chessGame.Board#inBoardBounds(int, int)
	 * @param newX
	 * @param newY
	 * @return boolean true if move is in board bounds
	 */
	public boolean inBoardBounds(int newX, int newY){
		if(newX < numXSquares && newY < numYSquares && newX > -1 && newY > -1){
			return true;
		}
		else
			return false;
	}

}
