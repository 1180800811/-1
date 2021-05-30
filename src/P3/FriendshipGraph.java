package P3;

import java.util.*;

public class FriendshipGraph {
	
	public  Map<Person, ArrayList<Person> > adjph  = new HashMap<Person, ArrayList<Person>>();
	
	public boolean addVertex(Person p)  {//��Ӷ���
		//�ж�person������û��������ȵ�����
		Set<Person> persons = adjph.keySet();
		Iterator<Person> key = persons.iterator();
		boolean flag = true ;
		while(key.hasNext()) {
			Person pp = key.next();
			if(p.getName() == pp.getName()) {//�жϻ᲻���������
				flag = false ;
				System.out.println("error:everyone should has a unique name !"+"The name of " + p.getName() + " is repeated !");
	//			System.exit(0);//������ͬ��ǿ���˳�����
			}
		}
		if(flag == true) {
			ArrayList<Person> friends = new ArrayList<Person>();
			adjph.put(p, friends);//���������ӵ���ϵͼ��	
		}
		return flag;
	}
	
	public boolean addEdge(Person p1 , Person p2) {//��Ӷ���
			int count = this.adjph.get(p1).size();
			boolean flag = true ;
			for(int i = 0 ; i < count ; i++) {
				if(this.adjph.get(p1).get(i)==p2) {
					System.out.println("error:"  +  p1 + "�Ѿ�������" + p2 );
					flag = false ; 
				}
			}
			if(flag)
				this.adjph.get(p1).add(p2);	
			return flag;
	}
	
	public int getDistance(Person p1, Person p2) {
		Person s = p1 ,t = p2 ;
		int count = 0;
		Queue<Person> queue = new LinkedList<Person>();//����
		ArrayList<Person> visited = new ArrayList<Person>();//�Ƿ���ʵ�����
		if(p1==p2)
			return count;
		queue.add(s);
		visited.add(s);
		while(!queue.isEmpty()) {
			s = queue.poll();
			count++;
			for(int i = 0 ; i < this.adjph.get(s).size() ; i ++ ) {
				if(!visited.contains(this.adjph.get(s).get(i))) {
					if(this.adjph.get(s).get(i) == t) {
						return count;
					}
					else {
						queue.add(this.adjph.get(s).get(i));
						visited.add(this.adjph.get(s).get(i));
					}
				}
			}
		}
		return -1;
		
	}
	
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
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
		System.out.println(graph.getDistance(rachel, ross));
		System.out.println(graph.getDistance(rachel, ben));
		System.out.println(graph.getDistance(rachel, rachel));
		System.out.println(graph.getDistance(rachel, kramer));

	}
}
