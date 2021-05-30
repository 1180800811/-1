/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices, edges) = a set of weighted directed edges 
    //	from one source vertex to one target vertex that is different from source vertex
    //
    // Representation invariant:
    //    weight > 0,There are at most one directed edge between source and target
    //
    // 
    //  vertices is mutable, so vertices() make defensive copy to avoid rep exposure
    //	and each of the fields are modified by key word final and private , so they can't be changed from outside
    
    // TODO constructor
    /**
     * ����һ���յ�����ͼ
     */
    public ConcreteEdgesGraph() {
    	vertices.clear();
    	edges.clear();
    	checkRep();
    }
    
///     public ConcreteEdgesGraph(final List<L> vertices) {
//		this.vertices.addAll(vertices);
//	}
    // TODO checkRep
    public void checkRep() {
    	assert vertices != null ;//��鶥�㼯�Ƿ�Ϊ��
    	for(L l : vertices) {
    		assert l != null ;//��鶥���Ƿ�Ϊ��
    	}
    	for(Edge<L> l : edges) {
    		assert l != null ;
    		assert l.getWeight() > 0;//���Ȩ���Ƿ������
    	}
    }
    /**
     * ��ͼ����Ӷ���
     * 
     * 
     * @param vertex:��Ҫ��ӵĶ���
     * @return ���ԭ����ͼû�а����ö��㣬����true�����򷵻�false
     */
    @Override public boolean add(L vertex) {
        assert vertex != null ;
        if(vertices.contains(vertex)) //�ж�ԭ���Ƿ���ڸö���
        	return false;
        vertices.add(vertex);
        checkRep();//����Ƿ���ڴ���
        return true ;
    }
    
    /**
     * ���weight=0����source��target����ı�ɾ��
     * ���weight��=0,���ͼ�д���source��target���㣬�򽫱ߵ�Ȩ�ظ�Ϊweight�������һ��Ȩ��Ϊtarget�ı�
     * 				 ���ͼ�в�����source��target���㣬������µıߺͶ��㡣
     * 
     * @param source Դ����
     * @param target Ŀ�궥��
     * @param weight Ȩ��
     * @param return �����Ѵ��ڵĶ���ԭ����Ȩ�ػ���0
     * 
     */
    @Override public int set(L source, L target, int weight) {
       this.add(source);//��Ӷ���
       this.add(target);//��Ӷ���
       int x = -1 ;
       for(int i = 0 ; i < edges.size() ; i++) {
    	   L Source = (L)edges.get(i).getSource();
    	   L Target = (L) edges.get(i).getTarget();
    	   if(Source.equals(source)&&Target.equals(target)){
    		   x = i ;//ԭ��source��target�����б�
    		   break;
    	   }
       }
       int w = 0 ;//Ȩ��
       if(weight == 0 ) {
    	   if( x != -1 ) {//ԭ���Ѿ����ڸñߣ���ɾ��
    		   w = edges.get(x).getWeight();
    		   edges.remove(x);
    	   }
       }else {
    		   Edge<L> e = new Edge<L>(source,target,weight);
    		   if(x != -1) {//�ߴ���
    			   w = edges.get(x).getWeight();
    			   edges.remove(x);  
    		   }
    		   edges.add(e);//������ӽ�ȥ
    	   }
       checkRep();
       return w ;//����ԭ���ߵ�Ȩ�ػ���0
    }
    /**
     * ɾȥͼ�еĶ��㼰���������ı�
     * 
     * @param vertex ��Ҫɾ���Ķ���
     * @param return ���ͼ�д��ڸö�����ɾ���ɹ�����true�����򷵻�false
     * 
     * 
     */
    @Override public boolean remove(L vertex) {
        if(!vertices.contains(vertex)) {
        	return false ;
        }
        vertices.remove(vertex);//ɾ���ö���
        for(int i  = 0 ; i < edges.size() ; i++) {
        	Edge<L> e = edges.get(i);
        	if(e.getSource().equals(vertex) || e.getTarget().equals(vertex)) {
        		edges.remove(i);//ɾ���ö���
        		i-- ; 
        	}
        }
        checkRep();
        return true ;
 }
    
    /**
     * ����ͼ�����еĶ��㼯
     * 
     * @param return ����ͼ�����еĶ��㼯
     */
    @Override public Set<L> vertices() {
        Set<L> result = new HashSet<L>();//�����Կ���
        for(L l : vertices) {
        	result.add(l);
        }
        checkRep();
        return result ;
    }
    /**
     * �������е���targetΪĿ�궥���Դ����ͱߵ�Ȩ��
     * 
     * @param target Ŀ�궥��
     * @param return �������е���targetΪĿ�궥���Դ����Ͷ�Ӧ�ߵ�Ȩ�ص�һ��ӳ��map
     */
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> result = new HashMap<>();//�����Կ���
        for(Edge<L> e : edges) {
        	if(e.getTarget().equals(target)) {
        		result.put(e.getSource(), e.getWeight());
        	}
        }
        checkRep();
        return result ; 
    }
    
    /**
     * �������е���sourceΪԴ�����Ŀ�궥��ͱߵ�Ȩ��
     * 
     * @param target Դ����
     * @param return �������е���targetΪĿ�궥���Ŀ�궥��Ͷ�Ӧ�ߵ�Ȩ�ص�һ��ӳ��map
     */
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> result = new HashMap<>();//�����Կ���
        for(Edge<L> e : edges) {
        	if(e.getSource().equals(source)) {
        		result.put(e.getTarget(), e.getWeight());
        	}
        }
        checkRep();
        return result ; 
    }
    
    // TODO toString()
    @Override 
    	public String toString() {//��дtoString����
    	String result = new String() ;
    	result += "�㼯��";
    	for(L l : vertices	) {
    		result += l.toString() + " " ;
    	}
    	result += "\n";
    	result += "�߼���";
    	for(Edge e : edges ) {
    		result +=e.toString() ;
    	}
    	result += "\n";
    	return result ;
    }
   
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 * @param <L>
 */
class Edge<L> {

    // TODO fields
    private final L source ;
    private final L target ;
	private final int weight ;
	
    // Abstraction function:
    //   represent a set of weighted directed edges 
    //	from one source vertex to one target vertex that is different from source vertex
	//
    // Representation invariant:
    //   weight > 0,source!=null , target !=null ,source and target is different 
	//
    // Safety from rep exposure:
    //   all fields is set with key word of final and private 
	//   so all fields can't be changed from outside 
    
    // TODO constructor
	/**
	 * 
	 * @param source �ߵ�source
	 * @param target �ߵ�target	
	 * @param weight �ߵ�Ȩ��
	 */
    public Edge(L source , L target ,int weight) {
    	
    	this.source = source;
    	this.target = target;
    	this.weight = weight ;

    }
	
    // TODO checkRep

    public void checkRep() {
    	assert this.source != null ;
    	assert this.target != null ;
    	assert this.weight > 0 ;
    }
    // TODO methods
    /**
     * 
     * @return ���رߵ�target 
     */
    public L getTarget() {
    	checkRep();
    	return this.target;
    }
    /**
     * 
     * @return ���رߵ�source 
     */
    public L getSource() {
    	checkRep();
    	return this.source;
    }
    /**
     * 
     * @return ���رߵ�weight
     */
    public int getWeight() {
    	checkRep();
    	return this.weight;
    }
    @Override
    public int hashCode() {
    	final int prime = 31 ;
    	int result = 1 ;
    	result = result * prime + this.source.hashCode() ;
    	result = result * prime + this.target.hashCode();
    	result = result * prime + this.weight ;
    	return result ;
    }
    @Override
    public boolean equals(Object other) {
    	if(this==other)
    		return true;
    	if(other == null)
    		return false;
    	if(this.getClass()!=other.getClass())
    		return false ;
    	if(other instanceof Edge) {
    		Edge<?> edge = (Edge<?>) other ;
    		return this.source.equals(edge.source) && this.target.equals(edge.target)
    					&& this.weight == edge.weight ;
    	}
    		
    	return false;
    }
    // TODO toString()

    public String toString() {
    	return this.source.toString() + " -> " +  this.target.toString() + " : " +this.weight + "\n" ;
    }

}
