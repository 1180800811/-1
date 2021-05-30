package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionTest {

	//Testing strategy
	//partition for checkMove()
	//	input : ԭλ�ú�Ŀ��λ����ͬ��Ŀ��λ�ñ�����ռ�á�
	//			��ǰ�����Ӳ��Ǳ�����ռ�á���ǰ��Ҫ�ƶ������ӵ�λ�������ӡ�
	//			Ŀ��λ�ó������̷�Χ����ǰ���ӵ�λ�ó������̷�Χ
	//			Ŀ��λ�ú͵�ǰλ�ú���
	//
	//partition for checkeatPiece()
	//	input : ԭλ�ú�Ŀ��λ����ͬ��Ŀ��λ�õ����Ӳ��ǵз���
	//			Ŀ��λ�������ӡ���ǰ�����Ӳ��Ǳ�����ռ��
	//			��ǰ��Ҫ�ƶ������ӵ�λ�������ӡ�Ŀ��λ�ó������̷�Χ
	//			��ǰ���ӵ�λ�ó������̷�Χ�� Ŀ��λ�ú͵�ǰλ�ú���
	//
	//partition for checkTiPiece()
	// input : ������Ӳ��ǶԷ������� �� �����ӵ�λ�������ӡ�
	//			�����ӵ�λ�ó��������̷�Χ�������ӵ�λ�ú���
	//	
	//partition for checkLuoPiece()
	// input : ������Ӳ��Ǽ��������ӡ������ӵ�λ���Ѿ�������
	//			�����ӵ�λ�ó��������̷�Χ�������ӵ�λ�ú���
	//
	/**
	 * ���������checkMove()����
	 * ���ǣ�ԭλ�ú�Ŀ��λ����ͬ
	 */
	@Test
	public void testCheckMove1() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(1, 1, 1, 1, true, chessBoard) );	
	}
	
	/**
	 * ���������checkMove()����
	 * ���ǣ�Ŀ��λ�ñ�����ռ��
	 */
	@Test
	public void testCheckMove2() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(0, 0, 1, 1, true, chessBoard) );	//(1,1)���λ�ñ�ռ��
	}
	
	/**
	 * ���������checkMove()����
	 * ���ǣ���ǰ�����Ӳ��Ǳ�����ռ��
	 */
	@Test
	public void testCheckMove3() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(0, 0, 2, 2, false, chessBoard) );	//(0,0)���λ�ò��Ǳ�false���playerռ��
	}
	
	/**
	 * ���������checkMove()����
	 * ���ǣ���ǰ��Ҫ�ƶ������ӵ�λ��������
	 */
	@Test
	public void testCheckMove4() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(3, 3, 2, 2, false, chessBoard) );	//(3,3)���λ��δ������ռ��
	}
	
	/**
	 * ���������checkMove()����
	 * ���ǣ�Ŀ��λ�ó������̷�Χ
	 */
	@Test
	public void testCheckMove5() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(0, 0, 9, 9, true, chessBoard) );	//(9,9)Ŀ��λ�ó������̷�Χ
	}
	
	/**
	 * ���������checkMove()����
	 * ���ǣ���ǰ���ӵ�λ�ó������̷�Χ
	 */
	@Test
	public void testCheckMove6() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkMove(9, 9, 3, 3, true, chessBoard) );	//(9,9)��ǰλ�ó������̷�Χ
	}
	
	/**
	 * ���������checkMove()����
	 * ���ǣ�Ŀ��λ�ú͵�ǰλ�ú���
	 */
	@Test
	public void testCheckMove7() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(true, action.checkMove(0, 0, 3, 3, true, chessBoard) );	//��ȷ
	}
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ�ԭλ�ú�Ŀ��λ����ͬ
	 */
	@Test
	public void testeatPiece1() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 1, 1, true, chessBoard) );//��ǰλ�ú�Ŀ��λ����ͬ	
	}
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ�Ŀ��λ�õ����Ӳ��ǵз���
	 */
	@Test
	public void testeatPiece2() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 1, 2, true, chessBoard) );//Ŀ��λ�õ����Ӳ��ǵз���
	}
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ�Ŀ��λ��������
	 */
	@Test
	public void testeatPiece3() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 3, 3, true, chessBoard) );//Ŀ��λ��������
	}
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ���ǰ�����Ӳ��Ǳ�����ռ��
	 */
	@Test
	public void testeatPiece4() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 2, 2, false, chessBoard) );//��ǰλ�õ����Ӳ��Ǳ�����ռ��
	}
	
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ���ǰ��Ҫ�ƶ������ӵ�λ��������
	 */
	@Test
	public void testeatPiece5() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(3, 3, 2, 2, false, chessBoard) );//��ǰ��Ҫ�ƶ������ӵ�λ��������
	}
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ�Ŀ��λ�ó������̷�Χ
	 */
	@Test
	public void testeatPiece6() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(1, 1, 9, 9, true, chessBoard) );//Ŀ��λ�ó������̷�Χ
	}
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ���ǰλ�ó������̷�Χ
	 */
	@Test
	public void testeatPiece7() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(false, action.checkeatPiece(9, 9, 1, 1, true, chessBoard) );//��ǰλ�ó������̷�Χ
	}
	
	/**
	 * ���������checkeatPiece()����
	 * ���ǣ���ǰλ�ú�Ŀ��λ�ú���
	 */
	@Test
	public void testeatPiece8() {
		Action action = new Action() ;
		Board chessBoard = new Board("chess") ;
		assertEquals(true, action.checkeatPiece(1, 1, 6, 6, true, chessBoard) );//��ǰλ�ú�Ŀ��λ�ú���
	}
	/**
	 * ����Χ���checkTipiece����
	 * ���ǣ�������Ӳ��ǶԷ�������
	 */
	@Test
	public void testCheckTipiece1() {
		Action action = new Action();
		Piece piece = new Piece(1, 1, "white", true) ;
		Board goBoard = new Board("go") ;
		goBoard.setGoPiece(piece);
		assertEquals(false,action.checkTiPiece(1, 1, true, goBoard));//������Ӳ��ǶԷ�������
	}
	
	/**
	 * ����Χ���checkTipiece����
	 * ���ǣ������ӵ�λ��������
	 */
	@Test
	public void testCheckTipiece2() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false,action.checkTiPiece(1, 1, true, goBoard));//�����ӵ�λ��������
	}
	
	/**
	 * ����Χ���checkTipiece����
	 * ���ǣ������ӵ�λ�ó��������̷�Χ
	 */
	@Test
	public void testCheckTipiece3() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false,action.checkTiPiece(20, 20, true, goBoard));//�����ӵ�λ�ó��������̷�Χ
	}
	
	/**
	 * ����Χ���checkTipiece����
	 * ���ǣ������ӵ�λ�ó��������̷�Χ
	 */
	@Test
	public void testCheckTipiece4() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false,action.checkTiPiece(20, 20, true, goBoard));//�����ӵ�λ�ó��������̷�Χ
	}
	
	/**
	 * ����Χ���checkTipiece����
	 * ���ǣ������ӵ�λ�ú���
	 */
	@Test
	public void testCheckTipiece5() {
		Action action = new Action();
		Piece piece = new Piece(1, 1, "white", true) ;
		Board goBoard = new Board("go") ;
		goBoard.setGoPiece(piece);
		assertEquals(true,action.checkTiPiece(1, 1, false, goBoard));//�����ӵ�λ�ó��������̷�Χ����
	}
	
	/**
	 * ����Χ���checkLuoPiece����
	 * ���ǣ������ӵ�λ���Ѿ�������
	 */
	@Test
	public void testLuoPiece1() {
		Action action = new Action();
		Piece piece = new Piece(1, 1, "white", true) ;
		Board goBoard = new Board("go") ;
		goBoard.setGoPiece(piece);
		assertEquals(false, action.checkLuoPiece(1, 1, goBoard, true));//�����ӵ�λ���Ѿ�������
	}
	
	/**
	 * ����Χ���checkLuoPiece����
	 * ���ǣ������ӵ�λ�ó��������̷�Χ
	 */
	@Test
	public void testLuoPiece2() {
		Action action = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(false, action.checkLuoPiece(20, 20, goBoard, true));//�����ӵ�λ�ó��������̷�Χ
	}
	
	/**
	 * ����Χ���checkLuoPiece����
	 * ���ǣ������ӵ�λ�ú���
	 */
	@Test
	public void testLuoPiece3() {
		Action action1 = new Action();
		Board goBoard = new Board("go") ;
		assertEquals(true, action1.checkLuoPiece(1, 2, goBoard, true));//�����ӵ�λ�ú���
	}
	
}
