/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   Represent a set of vertices that are different 
    // Representation invariant:
    //   Each Vertex in vertices is different from other
    //
    // Safety from rep exposure:
    //   vertices is modified by keyword private and final
    //   so it can't be changed from outside
    //	and vertices(),targets(),sources() make defensive copy to avoid rep exposure
    
    // TODO constructor
    public ConcreteVerticesGraph() {
		vertices.clear();
		
	}
    
    // TODO checkRep
    public void checkRep() {
    	assert vertices != null ;//���㲻Ϊnull
    	for(Vertex<L> l : vertices) {
    		for(Integer weight : l.getRelationship().values())
    					assert weight >0;//weight > 0
    	}
    	int size = vertices.size();
    	for(int i = 0 ; i < size ; i++) {
    		for(int j = i+1 ; j <size ; j ++ )	{
    			assert !vertices.get(i).equals(vertices.get(j));//���������ͬ
    		}
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
        for(Vertex<L> l : vertices) {
        	if(l.getSource().equals(vertex)) {//����Ƿ����vertex����
        		checkRep();
        		return false ;
        	}
        }
        vertices.add(new Vertex(vertex));//��Ӷ���
        checkRep();
        return true;
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
    @Override public int set(L source,L target, int weight) {
    	this.add(target);
    	if(this.add(source)) {
    		if(weight==0) {//��ԭͼ�����ڶ���source
    			checkRep();
    			return 0 ;
    		}
    		for(Vertex<L> l :vertices) {
    			if(l.getSource().equals(source)) {
    				l.addEdge(target, weight);
    			}
    		}
    		checkRep();
    		return 0 ;
    	}else { //��source�����Ѿ����뵽ͼ��
    		for(Vertex<L> l :vertices) {
    			if(l.getSource().equals(source)) {
    				int w = 0 ;
    				if(l.getRelationship().containsKey(target)) {
    					w = l.getRelationship().get(target);
    					if(weight == 0 ) {
    						l.remove(target);
    					}else {
    						l.addEdge(target, weight);
    					}
    				}else {
    					if(weight!=0) {
    						l.addEdge(target, weight);
    					}
    					
    				}
    				checkRep();
    				return w ;
    			}
    		}
    	}
    	checkRep();
    	return 0 ;
    	
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
        Iterator<Vertex<L>>  iterator = vertices.iterator();
        boolean result = false ;
        while(iterator.hasNext()) {
        	Vertex<L>  l = iterator.next();
        	if(l.getRelationship().containsKey(vertex)) {
        		l.getRelationship().remove(vertex);
        	}
        	if(l.getSource().equals(vertex)) {
        		iterator.remove();//ɾ���ö���
        		result = true ;
        	}
        }
        checkRep();
        return result ;
    }
    /**
     * ����ͼ�����еĶ��㼯
     * 
     * @param return ����ͼ�����еĶ��㼯
     */
    @Override public Set<L> vertices() {
        Set<L> result = new HashSet<L>();//�����Կ���
        for(Vertex<L> l: vertices)
        	result.add(l.getSource());
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
       Map<L, Integer> result = new HashMap<>();//�����Կ���
       for(Vertex<L> l : vertices) {
    	   if(l.getRelationship().containsKey(target)) {
    		   result.put(l.getSource(), l.getWeight(target));
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
        Map<L, Integer> result = new HashMap<>();//�����Կ���
        for(Vertex<L> l : vertices) {
        	if(l.getSource().equals(source)) {
        		result.putAll(l.getRelationship());
        	}
        }
        checkRep();
        return result ;
    }
    
    // TODO toString()
    @Override public String toString() {//��дtoString����
    	String result = "���еĶ�����:" ;
    	result += "\n" ;
    	for(Vertex<L> s : vertices) {
    		result +=s.getSource() +" " ;  		
    	}
    	result += "\n" ; 
    	result +="���еĶ��㼰���Ӧ�ı߼���Ȩ����" ;
    	result += "\n" ;
    	for(Vertex<L> s : vertices) {
    		result +=s.toString();
    	}
    	return result ;
    	
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    private final L source ;
    private Map<L,Integer>  relationship  = new HashMap<>();
    
    
    // TODO fields
    
    // Abstraction function:
    //   represent one vertex which is one of 
    //	two vertices of a directed edges with weight
    //
    // Representation invariant:
    //   source != null , 
    //	 the value of relationship.getValue() is positive
    // 	 Target of relationship.getKey() is not null or empty string
    //
    // Safety from rep exposure:
    //  All fields are private and final 
    //	the relationship map is return with defensive copying
    /**
     * 
     * @param source Դ����
     * @param map	�����Ӧ��target�����Լ�Ȩ��ӳ���ϵ
     */
    // TODO constructor
    public Vertex(final L source ,final Map<L,Integer> map) {
    	this.source = source ;
    	this.relationship = map ;
    	checkRep();
    }
    /**
     * 
     * @param source Դ����
     */
    public Vertex(final L source ) {
    	this.source = source ;
    	checkRep();
    }
    // TODO checkRep
    
    public void checkRep() {
    	assert source!= null ;
    	if(!relationship.isEmpty()) {
    		    for(int x : relationship.values() )
    		    	assert x > 0 ;
    		    for(L target : relationship.keySet()) {
    		    	assert !this.source.equals(target);
    		    }
    	}

    }
    // TODO methods
    /**
     * 
     * @return  ����Դ����
     */
    
    public L getSource() {
    	return this.source;
    }
    /**
     * 
     * ����һ����
     * @param target   Ŀ�궥��
     * @param weight	Դ�����Ŀ�궥���Ӧ��Ȩ��
     */
    public void addEdge(final L target , final int weight) {
    	relationship.put(target, weight);
    	checkRep();
    }
    
    /**
     * ɾ��һ������
     * @param target   ��Ҫɾ����Ŀ�궥��
     */
    public void remove(final L target) {
    	relationship.remove(target);
    	
    }
    /**
     * 
     * @return ����Դ�����Ӧ�Ĺ�ϵͼ
     */
    public Map<L,Integer> getRelationship(){
    	return this.relationship;
    }
    /**
     * 
     * @param target  Ŀ�궥��
     * @return  ���Ŀ�궥����ڣ����رߵ�Ȩ�أ����򷵻���
     */
    public int getWeight(final L target ) {
    	if(!this.relationship.containsKey(target))
    		 return 0 ;
    	
    	return this.relationship.get(target);
    }
    @Override
    public boolean equals(Object other) {
    	if(other == null)
    		return false ;
    	if(other == this)
    		return true ;
    	if(other instanceof Vertex) {
    		Vertex<?> vertex = (Vertex<?>) other ;
    		return this.getSource().equals(vertex.getSource());
    	}
    	
    	return false ;
    }
    @Override
    public int hashCode() {
    	int result = 17 ;
    	int value = 31 ;
    	result =  result * value + source.hashCode();
    	return result ;
    }
    
    // TODO toString()
    @Override
    public String toString() {
    	String string = new String();
    if(!relationship.isEmpty())
    	for(L l : relationship.keySet()) {
    		string += this.source.toString() + " -> " +  l +  ":" + this.relationship.get(l) + "\n";
    	}
    	
    	return string ;
    }
}
