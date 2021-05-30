package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionTest {

	//Testing strategy
	//partition for checkMove()
	//	input : 原位置和目标位置相同、目标位置被棋子占用、
	//			当前的棋子不是被己方占用、当前需要移动的棋子的位置无棋子、
	//			目标位置超出棋盘范围、当前棋子的位置超出棋盘范围
	//			目标位置和当前位置合理
	//
	//partition for checkeatPiece()
	//	input : 原位置和目标位置相同、目标位置的棋子不是敌方的
	//			目标位置无棋子、当前的棋子不是被己方占用
	//			当前需要移动的棋子的位置无棋子、目标位置超出棋盘范围
	//			当前棋子的位置超出棋盘范围、 目标位置和当前位置合理
	//
	//partition for checkTiPiece()
	// input : 待提的子不是对方的棋子 、 待提子的位置无棋子、
	//			待提子的位置超过了棋盘范围、待提子的位置合理
	//	
	//partition for checkLuoPiece()
	// input : 待落的子不是己方的棋子、待落子的位置已经有棋子
	//			待落子的位置超过了棋盘范围、待落子的位置合理
	//
	/**
	 * 测试象棋的checkMove()方法
	 * 覆盖：原位置和目标位置相同
	 */
	@Test
	public void testCheckMove1() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(1, 1, 1, 1, true, chessBoard) );	
	}
	
	/**
	 * 测试象棋的checkMove()方法
	 * 覆盖：目标位置被棋子占用
	 */
	@Test
	public void testCheckMove2() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(0, 0, 1, 1, true, chessBoard) );	//(1,1)这个位置被占用
	}
	
	/**
	 * 测试象棋的checkMove()方法
	 * 覆盖：当前的棋子不是被己方占用
	 */
	@Test
	public void testCheckMove3() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(0, 0, 2, 2, false, chessBoard) );	//(0,0)这个位置不是被false这个player占用
	}
	
	/**
	 * 测试象棋的checkMove()方法
	 * 覆盖：当前需要移动的棋子的位置无棋子
	 */
	@Test
	public void testCheckMove4() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(3, 3, 2, 2, false, chessBoard) );	//(3,3)这个位置未被棋子占用
	}
	
	/**
	 * 测试象棋的checkMove()方法
	 * 覆盖：目标位置超出棋盘范围
	 */
	@Test
	public void testCheckMove5() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(0, 0, 9, 9, true, chessBoard) );	//(9,9)目标位置超出棋盘范围
	}
	
	/**
	 * 测试象棋的checkMove()方法
	 * 覆盖：当前棋子的位置超出棋盘范围
	 */
	@Test
	public void testCheckMove6() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(9, 9, 3, 3, true, chessBoard) );	//(9,9)当前位置超出棋盘范围
	}
	
	/**
	 * 测试象棋的checkMove()方法
	 * 覆盖：目标位置和当前位置合理
	 */
	@Test
	public void testCheckMove7() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(true, action.checkMove(0, 0, 3, 3, true, chessBoard) );	//正确
	}
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：原位置和目标位置相同
	 */
	@Test
	public void testeatPiece1() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 1, 1, true, chessBoard) );//当前位置和目标位置相同	
	}
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：目标位置的棋子不是敌方的
	 */
	@Test
	public void testeatPiece2() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 1, 2, true, chessBoard) );//目标位置的棋子不是敌方的
	}
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：目标位置无棋子
	 */
	@Test
	public void testeatPiece3() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 3, 3, true, chessBoard) );//目标位置无棋子
	}
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：当前的棋子不是被己方占用
	 */
	@Test
	public void testeatPiece4() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 2, 2, false, chessBoard) );//当前位置的棋子不是被己方占用
	}
	
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：当前需要移动的棋子的位置无棋子
	 */
	@Test
	public void testeatPiece5() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(3, 3, 2, 2, false, chessBoard) );//当前需要移动的棋子的位置无棋子
	}
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：目标位置超出棋盘范围
	 */
	@Test
	public void testeatPiece6() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 9, 9, true, chessBoard) );//目标位置超出棋盘范围
	}
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：当前位置超出棋盘范围
	 */
	@Test
	public void testeatPiece7() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(9, 9, 1, 1, true, chessBoard) );//当前位置超出棋盘范围
	}
	
	/**
	 * 测试象棋的checkeatPiece()方法
	 * 覆盖：当前位置和目标位置合理
	 */
	@Test
	public void testeatPiece8() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(true, action.checkeatPiece(1, 1, 6, 6, true, chessBoard) );//当前位置和目标位置合理
	}
	/**
	 * 测试围棋的checkTipiece方法
	 * 覆盖：待提的子不是对方的棋子
	 */
	@Test
	public void testCheckTipiece1() {
		Action action = new Action();
		Piece piece = new Piece(1, 1, "white", true) ;
		Board goBoard = new Board("go") ;
		goBoard.setGoPiece(piece);
		assertEquals(false,action.checkTiPiece(1, 1, true, goBoard));//待提的子不是对方的棋子
	}
	
	/**
	 * 测试围棋的checkTipiece方法
	 * 覆盖：待提子的位置无棋子
	 */
	@Test
	public void testCheckTipiece2() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false,action.checkTiPiece(1, 1, true, goBoard));//待提子的位置无棋子
	}
	
	/**
	 * 测试围棋的checkTipiece方法
	 * 覆盖：待提子的位置超过了棋盘范围
	 */
	@Test
	public void testCheckTipiece3() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false,action.checkTiPiece(20, 20, true, goBoard));//待提子的位置超过了棋盘范围
	}
	
	/**
	 * 测试围棋的checkTipiece方法
	 * 覆盖：待提子的位置超过了棋盘范围
	 */
	@Test
	public void testCheckTipiece4() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false,action.checkTiPiece(20, 20, true, goBoard));//待提子的位置超过了棋盘范围
	}
	
	/**
	 * 测试围棋的checkTipiece方法
	 * 覆盖：待提子的位置合理
	 */
	@Test
	public void testCheckTipiece5() {
		Action action = new Action();
		Piece piece = new Piece(1, 1, "white", true) ;
		Board goBoard = new Board("go") ;
		goBoard.setGoPiece(piece);
		assertEquals(true,action.checkTiPiece(1, 1, false, goBoard));//待提子的位置超过了棋盘范围合理
	}
	
	/**
	 * 测试围棋的checkLuoPiece方法
	 * 覆盖：待落子的位置已经有棋子
	 */
	@Test
	public void testLuoPiece1() {
		Action action = new Action();
		Piece piece = new Piece(1, 1, "white", true) ;
		Board goBoard = new Board("go") ;
		goBoard.setGoPiece(piece);
		assertEquals(false, action.checkLuoPiece(1, 1, goBoard, true));//待落子的位置已经有棋子
	}
	
	/**
	 * 测试围棋的checkLuoPiece方法
	 * 覆盖：待落子的位置超过了棋盘范围
	 */
	@Test
	public void testLuoPiece2() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false, action.checkLuoPiece(20, 20, goBoard, true));//待落子的位置超过了棋盘范围
	}
	
	/**
	 * 测试围棋的checkLuoPiece方法
	 * 覆盖：待落子的位置合理
	 */
	@Test
	public void testLuoPiece3() {
		Action action1 = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(true, action1.checkLuoPiece(1, 2, goBoard, true));//待落子的位置合理
	}
	
}
