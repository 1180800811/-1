package P3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PositionTest {
	// Testing strategy 
	//
	//partition for EqualPosition()
	//	input : equal position , not equal position
	//
	//partition for FanWei()
	//	input: board��go ,chess
	//			(x,y)������Χ��(x,y)δ������Χ
	//partition for setPosition()
	//	input : (x,y)������Χ��(x,y)δ������Χ
	//			
	//
	//partition for getX()
	//	input : (x,y)
	//
	//partition for getY()
	//	input : (x,y)
	//
	//partition for toString()
	//   input :(x,y)
	/**
	 * ����EqualPosition()����
	 * ���ǣ�equal position , not equal position
	 */
	@Test
	public void testEqualPosition() {
		Position position1 = new Position();
		position1.setPosition(1, 1);
		Position position2 = new Position();
		position2.setPosition(1, 1);
		Position position3 = new Position();
		position3.setPosition(1, 4);
		assertTrue(position1.EqualPosition(position2));
		assertFalse(position1.EqualPosition(position3));
	}
	
	/**
	 * ����FanWei()����
	 * ���ǣ� board��chess
	 * 		(x,y)������Χ��(x,y)δ������Χ
	 */
	@Test
	public void testFanWeiChess() {
		Position position1 = new Position();
		position1.setPosition(1, 1);
		Position position2 = new Position();
		position2.setPosition(9, 9);
		Position position3 = new Position();
		position3.setPosition(1, 4);
		assertTrue(position1.FanWei("chess"));
		assertFalse(position2.FanWei("chess"));
		assertTrue(position3.FanWei("chess"));
	}
	
	/**
	 * ����FanWei()����
	 * ���ǣ� board��go
	 * 		(x,y)������Χ��(x,y)δ������Χ
	 */
	@Test
	public void testFanWeiGo() {
		Position position1 = new Position();
		position1.setPosition(1, 1);
		Position position2 = new Position();
		position2.setPosition(20, 20);
		Position position3 = new Position();
		position3.setPosition(1, 4);
		assertTrue(position1.FanWei("go"));
		assertFalse(position2.FanWei("go"));
		assertTrue(position3.FanWei("go"));
	}
	
	/**
	 * ����setPosition()����
	 * ���ǣ� (x,y)������Χ��(x,y)δ������Χ
	 */
	@Test
	public void testSetPosition() {
		Position position1 = new Position();
		position1.setPosition(1, 1);
		Position position2 = new Position();
		position2.setPosition(20, 20);
		assertEquals(1, position1.getX());
		assertEquals(1, position1.getY());
		assertEquals(20, position2.getX());
		assertEquals(20, position2.getY());
	}
	
	/**
	 * ����getX��getY����
	 */
	@Test
	public void testgetXgetY() {
		Position position1 = new Position();
		position1.setPosition(1, 1);
		Position position2 = new Position();
		position2.setPosition(20, 20);
		assertEquals(1, position1.getX());
		assertEquals(1, position1.getY());
		assertEquals(20, position2.getX());
		assertEquals(20, position2.getY());
	}
	
	/**
	 * ����toString����
	 * 
	 */
	@Test
	public void testToString() {
		Position position1 = new Position();
		position1.setPosition(1, 1);
		String result = "(" + 1 + ","  + 1 + ")";
		assertEquals(result, position1.toString());
	}
	
	
	
}
