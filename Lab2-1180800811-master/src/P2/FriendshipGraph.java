package P2;

import static org.junit.Assert.assertTrue;

import java.util.*;

import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;
import P1.poet.GraphPoet;

public class FriendshipGraph extends ConcreteEdgesGraph<Person>{
	

	/**
	 * ��һ���˼��뵽��ϵͼ��
	 * @param p ���������
	 */
	
	public boolean addVertex(Person p)  {//��Ӷ���
		if(this.vertices().contains(p)) {
			System.out.println("The person of "+p+ "has been constructed");
			return false ;
		}else{
			this.add(p);
			return true ;
		}
		
	}
	
	/**
	 * ��һ����ϵ��ӵ���ϵͼ��
	 * @param p1   ��1/���
	 * @param p2  ��2/����
	 */
	public boolean addEdge(Person p1 , Person p2) {//��ӱ�
			if(p1.equals(p2))
				return false ; ;
			this.set(p1, p2, 1);
			return true ;
	}
	
	/**
	 * 
	 * @param p1  p1��Ѱ�����
	 * @param p2  p2��Ѱ���յ�
	 * @return  p1 �� p2 ֮�����̾���
	 */

    public int getDistance(Person person1, Person person2) {
        if(person1.equals(person2)){//������ͬ������Ϊ0
            return 0;
        }
    	final Map<Person, Integer> distance = new HashMap<>();//person1��Person�ľ����mapͼ
        final Map<Person, Boolean> visited = new HashMap<>();//�ж�person�Ƿ񱻷��ʵ�mapͼ
        Queue<Person> queue = new LinkedList<>();//����
        for (Person person : vertices()) {//��ʼ������Ϊ0 ��δ������
            distance.put(person, 0);
            visited.put(person, false);
        }
        int answer = -1;
        visited.put(person1, true);//����
        queue.offer(person1);
        Person person;
        while(!queue.isEmpty()) {
            person = queue.poll();
            Set<Person> adjacent = targets(person).keySet();//������adjacent�ڽӵĶ���
            for(Person person3 : adjacent) {
                if(!visited.get(person3)) {//δ������
                    visited.put(person3, true);//����
                    queue.offer(person3);
                    distance.put(person3, distance.get(person) + 1);//�����1
                    if(person3.equals(person2)) {//Ŀ����ҵ�
                        answer = distance.get(person3);
                        break;
                    }
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));//1
        System.out.println(graph.getDistance(rachel, ben));//2
        System.out.println(graph.getDistance(rachel, rachel));//0
        System.out.println(graph.getDistance(rachel, kramer));//-1
    }
}
