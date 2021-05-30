package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {
	
	// Testing strategy
	//	partition for LuoPieceGo()
	//	input : (x,y)λ�ú���(x,y)λ�ò����� ��
	//			player == true , player == false 
	//
	//	partition for TiPieceGo()
	//	input : (x,y)λ�ú���(x,y)λ�ò����� ��
	//			player == true , player == false 	
	//
	//	partition for movePiece()
	//	input:		(x1,y1),(x2,y2)λ�ú���(x1,y1),(x2,y2)λ�ò�����
	//			player == true , ;player == false ;
	//
	//	partition for eatPiece()
	//	input:		(x1,y1),(x2,y2)λ�ú���(x1,y1),(x2,y2)λ�ò�����
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
	 * ����Χ���LuoPieceGo����
	 * ����:λ��(x,y)������ , player == true 
	 */
	@Test
	public void testLuoPieceGo1() {
		Game game = new Game("go") ;
		assertEquals(false, game.LuoPieceGo(20, 20, true));//�����ӵ�λ�ó��������̷�Χ
		assertEquals(0, game.getSize(false));
		assertEquals(true, game.LuoPieceGo(1, 1, true));
		assertEquals(0, game.getSize(false));
		assertEquals(false, game.LuoPieceGo(1, 1, true));//�����ӵ�λ���Ѿ�������	
		assertEquals(0, game.getSize(false));
	}

	/**
	 * ����Χ���LuoPieceGo����
	 * ����:λ��(x,y)���� , player == false 
	 */
	@Test
	public void testLuoPieceGo2() {
		Game game = new Game("go") ;
		assertEquals(true, game.LuoPieceGo(1, 0, false));//�����ӵ�λ�ú���
		assertEquals(1, game.getSize(false));

	}
	
	/**
	 * ����Χ���LuoPieceGo����
	 * ����:λ��(x,y)������ , player == true 
	 */
	@Test
	public void testTiPieceGo1() {
		Game game = new Game("go") ;
		game.LuoPieceGo(1, 1, true);
		assertEquals(1, game.getSize(true));
		assertFalse(game.TiPieceGo(1, 1, true));//������Ӳ��ǶԷ�������
		assertEquals(1, game.getSize(true));
		assertFalse(game.TiPieceGo(1, 2, true));//�����ӵ�λ��������
		assertEquals(1, game.getSize(true));
		assertFalse(game.TiPieceGo(20, 20, true));//�����ӵ�λ�ó��������̷�Χ
		assertEquals(1, game.getSize(true));
		assertTrue(game.TiPieceGo(1, 1, false));//��ȷ
		assertEquals(0, game.getSize(true));
	}
	
	/**
	 * ����Χ���LuoPieceGo����
	 * ����:λ��(x,y)���� , player == false 
	 */
	@Test
	public void testTiPieceGo2() {
		Game game = new Game("go") ;
		game.LuoPieceGo(1, 1, true);
		assertEquals(1, game.getSize(true));
		assertTrue(game.TiPieceGo(1, 1, false));//��ȷ
		assertEquals(0, game.getSize(true));
		game.LuoPieceGo(1, 2, false);
		assertEquals(1, game.getSize(false));
		assertTrue(game.TiPieceGo(1, 2, true));//��ȷ
	}
	
	/**
	 * ���������movePiece����
	 * ����:(x1,y1),(x2,y2)λ�ò����� , player == true , player == false 
	 */
	@Test
	public void testMovepieceChess1() {
		Game game1 = new Game("chess") ;
		assertEquals(false, game1.movePiece(1, 1, 1, 1, true));//ԭλ�ú�Ŀ��λ����ͬ
		assertEquals(false, game1.movePiece(1, 1, 1, 2, true));//Ŀ��λ�ñ�����ռ��
		assertEquals(false, game1.movePiece(1, 1, 3, 3, false));//��ǰ�����Ӳ��Ǳ�����ռ��
		assertEquals(false, game1.movePiece(2, 2, 3, 3, false));//��ǰ��Ҫ�ƶ������ӵ�λ��������
		assertEquals(false, game1.movePiece(1, 1, 8, 8, true));//Ŀ��λ�ó������̷�Χ	
		assertEquals(false, game1.movePiece(8, 8, 3, 3, false));//��ǰ���ӵ�λ�ó������̷�Χ
	}
	
	/**
	 * ���������movePiece����
	 * ����:(x1,y1),(x2,y2)λ�ú��� , player == true , player == false 
	 */
	@Test
	public void testMovepieceChess2() {
		Game game1 = new Game("chess") ;
		assertTrue(game1.movePiece(0, 0, 3, 3, true));
		assertTrue(game1.movePiece(6, 6, 4, 4, false));
		assertTrue(game1.getSize(true) == game1.getSize(false));
	}

	/**
	 * ���������eatPiece����
	 * ����:(x1,y1),(x2,y2)λ�ò����� , player == true , player == false 
	 */
	@Test
	public void testEatpieceChess1() {
		Game game1 = new Game("chess") ;
		assertEquals(false, game1.eatPiece(1, 1, 1, 1, true));//ԭλ�ú�Ŀ��λ����ͬ
		assertEquals(false, game1.eatPiece(1, 1, 1, 2, true));//Ŀ��λ�õ����Ӳ��ǵз���
		assertEquals(false, game1.eatPiece(1, 1, 3, 3, true));//Ŀ��λ��������
		assertEquals(false, game1.eatPiece(1, 1, 6, 6, false));//��ǰ�����Ӳ��Ǳ�����ռ��
		assertEquals(false, game1.eatPiece(2, 2, 7, 7, true));//��ǰ��Ҫ�ƶ������ӵ�λ��������
		assertEquals(false, game1.movePiece(8, 8, 1, 1, false));//��ǰ���ӵ�λ�ó������̷�Χ
		assertEquals(false, game1.movePiece(1, 1, 8, 8, true));//Ŀ��λ�ó������̷�Χ
		
	}
	
	/**
	 * ���������eatPiece����
	 * ����:(x1,y1),(x2,y2)λ�ú��� , player == true , player == false 
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
	 * ����getPiece����
	 * ����: position with piece existed ,  position without piece existed
	 * 		board : chessBoard
	 */
	@Test
	public void testGetPiece1() {
		Game game = new Game("chess");
		Position p = new Position() ;//position with piece existed
		p.setPosition(0, 0);
		Piece piece1= game.getPiece(p) ;
		piece1.setPieceName("��");
		assertEquals("��", piece1.getName());
		assertEquals(true, piece1.getOwner());
		assertTrue(piece1.getPosition().EqualPosition(p));
		p.setPosition(2, 2);//position without piece existed
		piece1 = game.getPiece(p);
		assertEquals(null, piece1);
	}
	
	/**
	 * ����getPiece����
	 * ����: position with piece existed ,  position without piece existed
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
	 * ����getHistory����
	 * ����:LuoPieceGo,TiPiece
	 */
	@Test
	public void testGetHistory1() {
		Game game = new Game("go");
		String result1 = new String() ;
		game.LuoPieceGo(0, 0, true) ;
		result1 ="����:  (" + 0 + "," + 0 +")";
		assertEquals(result1, game.getHistory(true));
		game.TiPieceGo(0, 0,false) ;
		 String result = "����:" + 0 + "," + 0 +")";
		assertEquals(result, game.getHistory(false));
		
		game.LuoPieceGo(4, 4, false) ;
		result +="����:  (" + 4 + "," + 4 +")";
		assertEquals(result, game.getHistory(false));
		game.TiPieceGo(4, 4,true) ;
		result1 += "����:" + 4 + "," + 4 +")";
		assertEquals(result1, game.getHistory(true));
	}
	
	/**
	 * ����getHistory����
	 * ����:movePiece,eatPiece,
	 */
	@Test
	public void testGetHistory21() {
		Game game = new Game("chess");
		String result = new String() ;
		game.movePiece(0, 0, 3, 3, true);
		result +="����:(" + 0 + "," + 0 + ")" + "->(" + 3 + "," +3 + ") ";
		assertEquals(result, game.getHistory(true));
		game.eatPiece(0, 1, 6, 5, true );
		result += "����:(" + 0 + "," + 1 + ")" + "->(" + 6 + "," +5 + ") ";
		assertEquals(result, game.getHistory(true));
		
		game.eatPiece(6, 6, 1, 1, false);
		result = "����:(" + 6 + "," + 6 + ")" + "->(" + 1 + "," + 1 + ") ";
		assertEquals(result, game.getHistory(false));
		
		game.movePiece(6, 4, 3, 2, false);
		result +="����:(" + 6 + "," + 4 + ")" + "->(" + 3 + "," +2 + ") ";
		assertEquals(result, game.getHistory(false));
	}
	/**
	 * ����addHistory�ķ���
	 * ����:	null string , not null string 
	 * 		player : true  
	 */		
	@Test
	public void testAddHistory1() {
		Game game = new Game("chess");
		String result = new String() ;
		game.movePiece(0, 0, 3, 3, true);
		result +="����:(" + 0 + "," + 0 + ")" + "->(" + 3 + "," +3 + ") ";
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
	 * ����addHistory�ķ���
	 * ����:	null string , not null string 
	 * 		player : true  
	 */		
	@Test
	public void testAddHistory2() {
		Game game = new Game("chess");
		String result = new String() ;
		game.movePiece(6, 0, 3, 3, false);
		result +="����:(" + 6 + "," + 0 + ")" + "->(" + 3 + "," +3 + ") ";
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
	 * ����setPlayer1,setPlayer2,getPlayer1,getPlayer2����
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
