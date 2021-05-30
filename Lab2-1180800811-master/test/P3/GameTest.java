package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	
	// Testing strategy
	//	partition for LuoPieceGo()
	//	input : (x,y)位置合理，(x,y)位置不合理 ，
	//			player == true , player == false 
	//
	//	partition for TiPieceGo()
	//	input : (x,y)位置合理，(x,y)位置不合理 ，
	//			player == true , player == false 	
	//
	//	partition for movePiece()
	//	input:		(x1,y1),(x2,y2)位置合理，(x1,y1),(x2,y2)位置不合理
	//			player == true , ;player == false ;
	//
	//	partition for eatPiece()
	//	input:		(x1,y1),(x2,y2)位置合理，(x1,y1),(x2,y2)位置不合理
	//				player == true , ;player == false ;
	//
	//	partition for getHistory()
	//	input :     LuoPieceGo,movePiece,eatPiece,TiPiece,showBoard
	//				player : player1 , player2
	//
	//	partition for getPiece()
	//	input :  position with piece existed , position without piece existed 
	//			 board : goBoard , chessBoard
	//
	//	partition for addHistory()
	//	input : null string , not null string 
	//			player: true ,false
	/**
	 * 测试围棋的LuoPieceGo方法
	 * 覆盖:位置(x,y)不合理 , player == true 
	 */
	@Test
	public void testLuoPieceGo1() {
		Game game = new Game("go") ;
		assertEquals(false, game.LuoPieceGo(20, 20, true));//待落子的位置超过了棋盘范围
		assertEquals(0, game.getSize(false));
		assertEquals(true, game.LuoPieceGo(1, 1, true));
		assertEquals(0, game.getSize(false));
		assertEquals(false, game.LuoPieceGo(1, 1, true));//待落子的位置已经有棋子	
		assertEquals(0, game.getSize(false));
	}

	/**
	 * 测试围棋的LuoPieceGo方法
	 * 覆盖:位置(x,y)合理 , player == false 
	 */
	@Test
	public void testLuoPieceGo2() {
		Game game = new Game("go") ;
		assertEquals(true, game.LuoPieceGo(1, 0, false));//待落子的位置合理
		assertEquals(1, game.getSize(false));

	}
	
	/**
	 * 测试围棋的LuoPieceGo方法
	 * 覆盖:位置(x,y)不合理 , player == true 
	 */
	@Test
	public void testTiPieceGo1() {
		Game game = new Game("go") ;
		game.LuoPieceGo(1, 1, true);
		assertEquals(1, game.getSize(true));
		assertFalse(game.TiPieceGo(1, 1, true));//待提的子不是对方的棋子
		assertEquals(1, game.getSize(true));
		assertFalse(game.TiPieceGo(1, 2, true));//待提子的位置无棋子
		assertEquals(1, game.getSize(true));
		assertFalse(game.TiPieceGo(20, 20, true));//待提子的位置超过了棋盘范围
		assertEquals(1, game.getSize(true));
		assertTrue(game.TiPieceGo(1, 1, false));//正确
		assertEquals(0, game.getSize(true));
	}
	
	/**
	 * 测试围棋的LuoPieceGo方法
	 * 覆盖:位置(x,y)合理 , player == false 
	 */
	@Test
	public void testTiPieceGo2() {
		Game game = new Game("go") ;
		game.LuoPieceGo(1, 1, true);
		assertEquals(1, game.getSize(true));
		assertTrue(game.TiPieceGo(1, 1, false));//正确
		assertEquals(0, game.getSize(true));
		game.LuoPieceGo(1, 2, false);
		assertEquals(1, game.getSize(false));
		assertTrue(game.TiPieceGo(1, 2, true));//正确
	}
	
	/**
	 * 测试象棋的movePiece方法
	 * 覆盖:(x1,y1),(x2,y2)位置不合理 , player == true , player == false 
	 */
	@Test
	public void testMovepieceChess1() {
		Game game1 = new Game("chess") ;
		assertEquals(false, game1.movePiece(1, 1, 1, 1, true));//原位置和目标位置相同
		assertEquals(false, game1.movePiece(1, 1, 1, 2, true));//目标位置被棋子占用
		assertEquals(false, game1.movePiece(1, 1, 3, 3, false));//当前的棋子不是被己方占用
		assertEquals(false, game1.movePiece(2, 2, 3, 3, false));//当前需要移动的棋子的位置无棋子
		assertEquals(false, game1.movePiece(1, 1, 8, 8, true));//目标位置超出棋盘范围	
		assertEquals(false, game1.movePiece(8, 8, 3, 3, false));//当前棋子的位置超出棋盘范围
	}
	
	/**
	 * 测试象棋的movePiece方法
	 * 覆盖:(x1,y1),(x2,y2)位置合理 , player == true , player == false 
	 */
	@Test
	public void testMovepieceChess2() {
		Game game1 = new Game("chess") ;
		assertTrue(game1.movePiece(0, 0, 3, 3, true));
		assertTrue(game1.movePiece(6, 6, 4, 4, false));
		assertTrue(game1.getSize(true) == game1.getSize(false));
	}

	/**
	 * 测试象棋的eatPiece方法
	 * 覆盖:(x1,y1),(x2,y2)位置不合理 , player == true , player == false 
	 */
	@Test
	public void testEatpieceChess1() {
		Game game1 = new Game("chess") ;
		assertEquals(false, game1.eatPiece(1, 1, 1, 1, true));//原位置和目标位置相同
		assertEquals(false, game1.eatPiece(1, 1, 1, 2, true));//目标位置的棋子不是敌方的
		assertEquals(false, game1.eatPiece(1, 1, 3, 3, true));//目标位置无棋子
		assertEquals(false, game1.eatPiece(1, 1, 6, 6, false));//当前的棋子不是被己方占用
		assertEquals(false, game1.eatPiece(2, 2, 7, 7, true));//当前需要移动的棋子的位置无棋子
		assertEquals(false, game1.movePiece(8, 8, 1, 1, false));//当前棋子的位置超出棋盘范围
		assertEquals(false, game1.movePiece(1, 1, 8, 8, true));//目标位置超出棋盘范围
		
	}
	
	/**
	 * 测试象棋的eatPiece方法
	 * 覆盖:(x1,y1),(x2,y2)位置合理 , player == true , player == false 
	 */
	@Test
	public void testEatpieceChess2() {
		Game game1 = new Game("chess") ;
		assertEquals(true, game1.eatPiece(1, 1, 6, 6, true));
		assertEquals(15, game1.getSize(false));
		assertEquals(true, game1.eatPiece(6, 5, 6, 6, false));
		assertEquals(15, game1.getSize(true));
	}
	/**
	 * 测试getPiece方法
	 * 覆盖: position with piece existed ,  position without piece existed
	 * 		board : chessBoard
	 */
	@Test
	public void testGetPiece1() {
		Game game = new Game("chess");
		Position p = new Position() ;//position with piece existed
		p.setPosition(0, 0);
		Piece piece1= game.getPiece(p) ;
		piece1.setPieceName("车");
		assertEquals("车", piece1.getName());
		assertEquals(true, piece1.getOwner());
		assertTrue(piece1.getPosition().EqualPosition(p));
		p.setPosition(2, 2);//position without piece existed
		piece1 = game.getPiece(p);
		assertEquals(null, piece1);
	}
	
	/**
	 * 测试getPiece方法
	 * 覆盖: position with piece existed ,  position without piece existed
	 * 		board : goBoard
	 */
	@Test
	public void testGetPiece2() {
		Game game = new Game("go");
		game.LuoPieceGo(0, 0, true) ;
		Position p = new Position() ;//position with piece existed
		p.setPosition(0, 0);
		Piece piece1= game.getPiece(p) ;
		assertEquals("white", piece1.getName());
		assertEquals(true, piece1.getOwner());
		assertTrue(piece1.getPosition().EqualPosition(p));
		p.setPosition(2, 2);//position without piece existed
		piece1 = game.getPiece(p);
		assertEquals(null, piece1);
	}
	/**
	 * 测试getHistory方法
	 * 覆盖:LuoPieceGo,TiPiece
	 */
	@Test
	public void testGetHistory1() {
		Game game = new Game("go");
		String result1 = new String() ;
		game.LuoPieceGo(0, 0, true) ;
		result1 ="落子:  (" + 0 + "," + 0 +")";
		assertEquals(result1, game.getHistory(true));
		game.TiPieceGo(0, 0,false) ;
		 String result = "提子:" + 0 + "," + 0 +")";
		assertEquals(result, game.getHistory(false));
		
		game.LuoPieceGo(4, 4, false) ;
		result +="落子:  (" + 4 + "," + 4 +")";
		assertEquals(result, game.getHistory(false));
		game.TiPieceGo(4, 4,true) ;
		result1 += "提子:" + 4 + "," + 4 +")";
		assertEquals(result1, game.getHistory(true));
	}
	
	/**
	 * 测试getHistory方法
	 * 覆盖:movePiece,eatPiece,
	 */
	@Test
	public void testGetHistory21() {
		Game game = new Game("chess");
		String result = new String() ;
		game.movePiece(0, 0, 3, 3, true);
		result +="移子:(" + 0 + "," + 0 + ")" + "->(" + 3 + "," +3 + ") ";
		assertEquals(result, game.getHistory(true));
		game.eatPiece(0, 1, 6, 5, true );
		result += "吃子:(" + 0 + "," + 1 + ")" + "->(" + 6 + "," +5 + ") ";
		assertEquals(result, game.getHistory(true));
		
		game.eatPiece(6, 6, 1, 1, false);
		result = "吃子:(" + 6 + "," + 6 + ")" + "->(" + 1 + "," + 1 + ") ";
		assertEquals(result, game.getHistory(false));
		
		game.movePiece(6, 4, 3, 2, false);
		result +="移子:(" + 6 + "," + 4 + ")" + "->(" + 3 + "," +2 + ") ";
		assertEquals(result, game.getHistory(false));
	}
	/**
	 * 测试addHistory的方法
	 * 覆盖:	null string , not null string 
	 * 		player : true  
	 */		
	@Test
	public void testAddHistory1() {
		Game game = new Game("chess");
		String result = new String() ;
		game.movePiece(0, 0, 3, 3, true);
		result +="移子:(" + 0 + "," + 0 + ")" + "->(" + 3 + "," +3 + ") ";
		assertEquals(result, game.getHistory(true));
		String add = null ;
		game.addHistory(add, true);//null string
		assertEquals(result, game.getHistory(true));
		add = "add1" ;
		game.addHistory(add, true);//not null string
		result += add ;
		assertEquals(result, game.getHistory(true));
		
	}
	
	/**
	 * 测试addHistory的方法
	 * 覆盖:	null string , not null string 
	 * 		player : true  
	 */		
	@Test
	public void testAddHistory2() {
		Game game = new Game("chess");
		String result = new String() ;
		game.movePiece(6, 0, 3, 3, false);
		result +="移子:(" + 6 + "," + 0 + ")" + "->(" + 3 + "," +3 + ") ";
		assertEquals(result, game.getHistory(false));
		String add = null ;
		game.addHistory(add, false);//null string
		assertEquals(result, game.getHistory(false));
		add = "add1" ;
		game.addHistory(add, false);//not null string
		result += add ;
		assertEquals(result, game.getHistory(false));
	}
	/**
	 * 测试setPlayer1,setPlayer2,getPlayer1,getPlayer2方法
	 */
	@Test
	public void testPlayer() {
		Game game = new Game("go");
		game.setPlayer1("a");
		game.setPlayer2("b");
		assertEquals("a", game.getPlayer1());
		assertEquals("b", game.getPlayer2());
	}
	
}
