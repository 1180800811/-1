package P2;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {
	
	//Testing strategy
	//
	//  Partition for FriendshipGraph.addVertex
	//		input : existed vertex , not existed vertex , not existed vertex with same name
	//
	//  Partition for FriendshipGraph.addEdge
	//		input :  two  different existed vertex , two same  existed vertex
	//
	//	Partition for FriendshipGraph.getDistance
	//		input : ���㵽����ľ��� �� ������ͨ�Ķ������·������̾��� �� ��������ͨ�ľ��룬������ͨ��һ��·������̾���
	//	Partition for Person.Equals
	//      input : two person with same name  , two person with different name 
	//

	/*���Լ��벻���ڵĶ����addVertex����
	 * 		���ǣ� not existed vertex 
	 */
	@Test
	public void testAddNotExistedVertex() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		graph.add(person1);
		assertTrue(graph.vertices().contains(person1));
	}
	
	/*���Լ����Ѿ����ڵĶ����addVertex����
	 * 		���ǣ�  not existed vertex with same name
	 */
	@Test
	public void testAddSameNameVertex() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		Person person2 = new Person("person1") ;
		graph.addVertex(person1);
		assertTrue(graph.vertices().contains(person1));
		assertTrue(graph.vertices().contains(person2));//������ͬ�Ķ���
		assertFalse(graph.add(person2)); //�жϼ���������ͬ�Ķ���
	}
	
	/*���Լ���������ͬ�Ķ����addVertex����
	 * 		���ǣ�  existed vertex 
	 */
	@Test
	public void testAddExistedVertex() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		graph.addVertex(person1);
		assertTrue(graph.vertices().contains(person1));
		assertEquals(false, graph.addVertex(person1));//�ж��ظ����붥��
	}
	
	/*���Լ���ߵ�addEdge����
	 * 		���� �� two  different existed vertex , two same  existed vertex
	 */
	@Test
	public void testAddEdge() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		Person person2 = new Person("person2") ;
		graph.addVertex(person1);
		graph.addVertex(person2);
		assertTrue(graph.vertices().contains(person1));
		assertTrue(graph.vertices().contains(person2));
		graph.addEdge(person1, person2);
		assertFalse(graph.addEdge(person1, person1));
		assertTrue(graph.sources(person2).containsKey(person1));
		assertTrue(graph.targets(person1).containsKey(person2));
		assertTrue(graph.targets(person1).size()==1);

	}
	
	/*���Զ��㵽����ľ����getDistance����
	 * 		���ǣ����㵽����ľ��� 
	 */
	@Test
	public void testZiShenDistance() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		graph.addVertex(person1);
		assertTrue(graph.getDistance(person1, person1)==0);
	}
	
	/*��������������ͨ��getDistance����
	 * 		���ǣ�����������ͨһ��·��
	 */
	@Test
	public void testSingleLianTongGetDistance() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		Person person2 = new Person("person2") ;
		Person person3 = new Person("person3") ;
		graph.addVertex(person1);
		graph.addVertex(person2);
		graph.addVertex(person3);
		graph.addEdge(person1, person2);
		graph.addEdge(person2, person3);
		assertTrue(graph.getDistance(person1, person2)==1);
		assertTrue(graph.getDistance(person2, person3)==1);
		assertTrue(graph.getDistance(person1, person3)==2);
	}
	
	/*��������������ͨ��getDistance����
	 * 		���ǣ�����������ͨ����·��
	 */
	@Test
	public void testDoubleLianTongGetDistance() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		Person person2 = new Person("person2") ;
		Person person3 = new Person("person3") ;
		Person person4 = new Person("person4") ;
		Person person5 = new Person("person5") ;
		graph.addVertex(person1);
		graph.addVertex(person2);
		graph.addVertex(person3);
		graph.addEdge(person1, person2);
		graph.addEdge(person2, person3);
		graph.addEdge(person1, person4);
		graph.addEdge(person4, person5);
		graph.addEdge(person5, person3);
		assertTrue(graph.getDistance(person1, person2)==1);
		assertTrue(graph.getDistance(person2, person3)==1);
		assertTrue(graph.getDistance(person1, person3)==2);// 1-2-3 ���� 1-4-5-3����̾���Ϊ2
	}
	
	/*��������������ͨ��getDistance����
	 * 		���ǣ��������㲻��ͨ
	 */
	@Test
	public void testBuLianTongGetDistance() {
		FriendshipGraph graph  = new FriendshipGraph() ;
		Person person1 = new Person("person1") ;
		Person person2 = new Person("person2") ;
		graph.addVertex(person1);
		graph.addVertex(person2);
		assertTrue(graph.getDistance(person1, person2)==-1);

	}
	/*
	 * ����Person��Equals����
	 */
	@Test
	public void testPersonEquals() {
		Person p1 = new Person("p1") ;
		Person p2 = new Person("p1") ;
		Person p3 = new Person("p3") ;
		assertTrue(p1.equals(p2));	
		assertFalse(p1.equals(p3));
		assertFalse(p1.equals(null));
}
	
}
