package P2;

import static org.junit.Assert.assertTrue;

import java.util.*;

import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;
import P1.poet.GraphPoet;

public class FriendshipGraph extends ConcreteEdgesGraph<Person>{
	

	/**
	 * 将一个人加入到关系图中
	 * @param p 待加入的人
	 */
	
	public boolean addVertex(Person p)  {//添加顶点
		if(this.vertices().contains(p)) {
			System.out.println("The person of "+p+ "has been constructed");
			return false ;
		}else{
			this.add(p);
			return true ;
		}
		
	}
	
	/**
	 * 将一个关系添加到关系图中
	 * @param p1   人1/入边
	 * @param p2  人2/出边
	 */
	public boolean addEdge(Person p1 , Person p2) {//添加边
			if(p1.equals(p2))
				return false ; ;
			this.set(p1, p2, 1);
			return true ;
	}
	
	/**
	 * 
	 * @param p1  p1是寻找起点
	 * @param p2  p2是寻找终点
	 * @return  p1 和 p2 之间的最短距离
	 */

    public int getDistance(Person person1, Person person2) {
        if(person1.equals(person2)){//顶点相同，距离为0
            return 0;
        }
    	final Map<Person, Integer> distance = new HashMap<>();//person1到Person的距离的map图
        final Map<Person, Boolean> visited = new HashMap<>();//判断person是否被访问的map图
        Queue<Person> queue = new LinkedList<>();//队列
        for (Person person : vertices()) {//初始化距离为0 ，未被访问
            distance.put(person, 0);
            visited.put(person, false);
        }
        int answer = -1;
        visited.put(person1, true);//访问
        queue.offer(person1);
        Person person;
        while(!queue.isEmpty()) {
            person = queue.poll();
            Set<Person> adjacent = targets(person).keySet();//所有与adjacent邻接的顶点
            for(Person person3 : adjacent) {
                if(!visited.get(person3)) {//未被访问
                    visited.put(person3, true);//访问
                    queue.offer(person3);
                    distance.put(person3, distance.get(person) + 1);//距离加1
                    if(person3.equals(person2)) {//目标点找到
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
