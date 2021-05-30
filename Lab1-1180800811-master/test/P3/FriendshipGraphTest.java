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
	public void getDistanceTest() {//测试两点的距离
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
	public void addVertexTest() { // 测试往图中加点
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("ross");
		Person ben = new Person("Ben");
		assertEquals(true, graph.addVertex(rachel));
		assertEquals(true, graph.addVertex(ross));
		assertEquals(true, graph.addVertex(ben));
		assertEquals(false, graph.addVertex(rachel)); // 测试重复加点
		assertEquals(false, graph.addVertex(ross));// 测试重复加点
		assertEquals(false, graph.addVertex(ben));// 测试重复加点
	}
	
	@Test
	/**
	 *  Test that addEdge are enabled 
	 */
	public void addEdgeTest() { // 测试建立关系
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		assertEquals(true, graph.addEdge(rachel, ross));//测试关系
		assertEquals(true, graph.addEdge(ross, rachel));
		assertEquals(true, graph.addEdge(ross, ben));
		assertEquals(true, graph.addEdge(ben, ross));
		assertEquals(false, graph.addEdge(ben, ross));//测试重复加入相同的关系
		assertEquals(false, graph.addEdge(ross, ben));//测试重复加入相同的关系
		assertEquals(false, graph.addEdge(ross, rachel));//测试重复加入相同的关系

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
