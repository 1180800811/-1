package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
		//Testing strategy 
		//Partition for Board.setGoPiece()
		//	input :  go piece 
		//Partition for Board.setChessPiece()
		//	input : chess piece
		//Partition for RemoveGoPiece()
		//	input : go piece  
		// Partition for RemoveChessPiece()
		//	input : chess piece
		//Partition for getZhanYong()
		//	input : Position existed with piece , Position existed without piece
		//			Board : go board , chess board
		//Partition for getWhoZhanYong()
		//			Position existed with player1 , Position existed with player2
		//			Board : go board , chess board
	@Test
	/*
	 * 测试将棋子添加到围棋棋盘上
	 */
	public void testSetGoPiece() {
		Board board = new Board("go") ;
		Piece piece = new Piece(1, 1, "white", true) ;
		board.setGoPiece(piece);
		Position position = new Position() ;
		position.setPosition(1, 1);
		assertEquals(true,board.getZhanYong(position,"go") );
		assertEquals(true,board.getWhoZhanYong(position,"go"));
	}
	
	@Test
	/*
	 * 测试将棋子添加到象棋棋盘上
	 */
	public void testSetChessPiece() {
		Board board = new Board("chess") ;
		Position position = new Position() ;
		position.setPosition(3, 3);
		Piece piece = new Piece(3, 3, "white", true) ;
		board.setChessPiece(piece);
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(true,board.getWhoZhanYong(position,"chess"));
	}

	@Test
	/*
	 * 测试将棋子从象棋棋盘上移除
	 */
	public void testRemoveChessPiece() {
		Board board = new Board("chess") ;
		Position position = new Position() ;
		position.setPosition(1, 1);
		Piece piece = new Piece(1, 1, "white", true) ;
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(true,board.getWhoZhanYong(position,"chess"));
		board.RemoveChessPiece(piece);
		assertEquals(false,board.getZhanYong(position,"chess") );
		assertEquals(true,board.getWhoZhanYong(position,"chess"));
	}
	
	@Test
	/*
	 * 测试将棋子从围棋棋盘上移除
	 */
	public void testRemoveGoPiece() {
		Board board = new Board("go") ;
		Position position = new Position() ;
		position.setPosition(1, 1);
		Piece piece = new Piece(1, 1, "white", true) ;
		board.setGoPiece(piece);
		assertEquals(true,board.getZhanYong(position,"go") );
		assertEquals(true,board.getWhoZhanYong(position,"go"));
		board.RemoveGoPiece(piece);
		assertEquals(false,board.getZhanYong(position,"go") );
		assertEquals(true,board.getWhoZhanYong(position,"go"));
	}

	@Test 
	/*测试象棋棋子是否被占用的getZhanYong()方法和被哪个player占用的getWhoZhanYong()方法
	 * 
	 */
	public void testChessgetWhoZhanYong() {
		Board board = new Board("chess") ;
		Position position = new Position() ;
		position.setPosition(1, 1);
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(true,board.getWhoZhanYong(position,"chess"));
		
		position.setPosition(0, 0);
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(true,board.getWhoZhanYong(position,"chess"));
		
		position.setPosition(2, 2);
		assertEquals(false,board.getZhanYong(position,"chess") );
		assertEquals(false,board.getWhoZhanYong(position,"chess"));
		
		position.setPosition(6, 6);
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(false,board.getWhoZhanYong(position,"chess"));
		
		position.setPosition(7, 7);
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(false,board.getWhoZhanYong(position,"chess"));
		
		Piece piece = new Piece(1, 1, "white", true) ;
		board.setGoPiece(piece);
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(false,board.getWhoZhanYong(position,"chess"));
		board.RemoveGoPiece(piece);
		position.setPosition(1, 1);
		assertEquals(true,board.getZhanYong(position,"chess") );
		assertEquals(true,board.getWhoZhanYong(position,"chess"));
		
		
	}
	@Test
	/*
	 * 测试围棋棋子是否被占用的getZhanYong()方法和被哪个player占用的getWhoZhanYong()方法
	 */
	public void testGogetWhoZhanYong() {
		Board board = new Board("go") ;
		Position position = new Position() ;
		position.setPosition(1, 1);
		assertEquals(false,board.getZhanYong(position,"go"));
		assertEquals(true,board.getWhoZhanYong(position,"go"));
		Piece piece = new Piece(1, 1, "white", false) ;
		board.setGoPiece(piece);
		assertEquals(true,board.getZhanYong(position,"go"));
		assertEquals(false,board.getWhoZhanYong(position,"go"));
		board.RemoveGoPiece(piece);
		assertEquals(false,board.getZhanYong(position,"go"));
		assertEquals(false,board.getWhoZhanYong(position,"go"));
		
}
}