package P3;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FriendshipGraphTest {
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; 
	}
	@Test
	/**
	 * Test that getDistance are enabled
	 */
	public void getDistanceTest() {//��������ľ���
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		assertEquals("expected distance", 1, graph.getDistance(rachel, ross));
		assertEquals("expected distance", 2, graph.getDistance(rachel, ben));
		assertEquals("expected distance", 0, graph.getDistance(rachel, rachel));
		assertEquals("expected distance", -1, graph.getDistance(rachel, kramer));
		
	}
	@Test
	/**
	 *  Test that addVertex are enabled 
	 */
	public void addVertexTest() { // ������ͼ�мӵ�
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("ross");
		Person ben = new Person("Ben");
		assertEquals(true, graph.addVertex(rachel));
		assertEquals(true, graph.addVertex(ross));
		assertEquals(true, graph.addVertex(ben));
		assertEquals(false, graph.addVertex(rachel)); // �����ظ��ӵ�
		assertEquals(false, graph.addVertex(ross));// �����ظ��ӵ�
		assertEquals(false, graph.addVertex(ben));// �����ظ��ӵ�
	}
	
	@Test
	/**
	 *  Test that addEdge are enabled 
	 */
	public void addEdgeTest() { // ���Խ�����ϵ
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		assertEquals(true, graph.addEdge(rachel, ross));//���Թ�ϵ
		assertEquals(true, graph.addEdge(ross, rachel));
		assertEquals(true, graph.addEdge(ross, ben));
		assertEquals(true, graph.addEdge(ben, ross));
		assertEquals(false, graph.addEdge(ben, ross));//�����ظ�������ͬ�Ĺ�ϵ
		assertEquals(false, graph.addEdge(ross, ben));//�����ظ�������ͬ�Ĺ�ϵ
		assertEquals(false, graph.addEdge(ross, rachel));//�����ظ�������ͬ�Ĺ�ϵ

	}
	@Test
	/**
	 *  Test that getName are enabled 
	 */
	public void getNameTest() {
		Person rachel = new Person("Rachel");
		Person ross = new Person("ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("kramer");
		assertEquals("Rachel", rachel.getName());
		assertEquals("ross", ross.getName());		
		assertEquals("Ben", ben.getName());
		assertEquals("kramer", kramer.getName());
	}


}
