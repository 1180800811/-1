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
    	assert vertices != null ;//顶点不为null
    	for(Vertex<L> l : vertices) {
    		for(Integer weight : l.getRelationship().values())
    					assert weight >0;//weight > 0
    	}
    	int size = vertices.size();
    	for(int i = 0 ; i < size ; i++) {
    		for(int j = i+1 ; j <size ; j ++ )	{
    			assert !vertices.get(i).equals(vertices.get(j));//顶点各不相同
    		}
    	}
    }
    /**
     * 向图中添加顶点
     * 
     * 
     * @param vertex:需要添加的顶点
     * @return 如果原来的图没有包含该顶点，返回true，否则返回false
     */
    @Override public boolean add(L vertex) {
        for(Vertex<L> l : vertices) {
        	if(l.getSource().equals(vertex)) {//检测是否包含vertex顶点
        		checkRep();
        		return false ;
        	}
        }
        vertices.add(new Vertex(vertex));//添加顶点
        checkRep();
        return true;
    }
    /**
     * 如果weight=0，则将source和target顶点的边删除
     * 如果weight！=0,如果图中存在source和target顶点，则将边的权重改为weight或者添加一条权重为target的边
     * 				 如果图中不存在source和target顶点，则加入新的边和顶点。
     * 
     * @param source 源顶点
     * @param target 目标顶点
     * @param weight 权重
     * @param return 返回已存在的顶点原来的权重或者0
     * 
     */
    @Override public int set(L source,L target, int weight) {
    	this.add(target);
    	if(this.add(source)) {
    		if(weight==0) {//若原图不存在顶点source
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
    	}else { //该source顶点已经加入到图中
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
     * 删去图中的顶点及其所关联的边
     * 
     * @param vertex 需要删除的顶点
     * @param return 如果图中存在该顶点且删除成功返回true。否则返回false
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
        		iterator.remove();//删除该顶点
        		result = true ;
        	}
        }
        checkRep();
        return result ;
    }
    /**
     * 返回图中所有的顶点集
     * 
     * @param return 返回图中所有的顶点集
     */
    @Override public Set<L> vertices() {
        Set<L> result = new HashSet<L>();//防御性拷贝
        for(Vertex<L> l: vertices)
        	result.add(l.getSource());
        checkRep();
        return result ;
    }
    /**
     * 返回所有的以target为目标顶点的源顶点和边的权重
     * 
     * @param target 目标顶点
     * @param return 返回所有的以target为目标顶点的源顶点和对应边的权重的一个映射map
     */
    @Override public Map<L, Integer> sources(L target) {
       Map<L, Integer> result = new HashMap<>();//防御性拷贝
       for(Vertex<L> l : vertices) {
    	   if(l.getRelationship().containsKey(target)) {
    		   result.put(l.getSource(), l.getWeight(target));
    	   }
       }
       checkRep();
       return result ;
       
    }
    /**
     * 返回所有的以source为源顶点的目标顶点和边的权重
     * 
     * @param target 源顶点
     * @param return 返回所有的以target为目标顶点的目标顶点和对应边的权重的一个映射map
     */
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> result = new HashMap<>();//防御性拷贝
        for(Vertex<L> l : vertices) {
        	if(l.getSource().equals(source)) {
        		result.putAll(l.getRelationship());
        	}
        }
        checkRep();
        return result ;
    }
    
    // TODO toString()
    @Override public String toString() {//重写toString方法
    	String result = "所有的顶点是:" ;
    	result += "\n" ;
    	for(Vertex<L> s : vertices) {
    		result +=s.getSource() +" " ;  		
    	}
    	result += "\n" ; 
    	result +="所有的顶点及其对应的边及其权重是" ;
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
     * @param source 源顶点
     * @param map	顶点对应的target顶点以及权重映射关系
     */
    // TODO constructor
    public Vertex(final L source ,final Map<L,Integer> map) {
    	this.source = source ;
    	this.relationship = map ;
    	checkRep();
    }
    /**
     * 
     * @param source 源顶点
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
     * @return  返回源顶点
     */
    
    public L getSource() {
    	return this.source;
    }
    /**
     * 
     * 加入一条边
     * @param target   目标顶点
     * @param weight	源顶点和目标顶点对应的权重
     */
    public void addEdge(final L target , final int weight) {
    	relationship.put(target, weight);
    	checkRep();
    }
    
    /**
     * 删除一个顶点
     * @param target   需要删除的目标顶点
     */
    public void remove(final L target) {
    	relationship.remove(target);
    	
    }
    /**
     * 
     * @return 返回源顶点对应的关系图
     */
    public Map<L,Integer> getRelationship(){
    	return this.relationship;
    }
    /**
     * 
     * @param target  目标顶点
     * @return  如果目标顶点存在，返回边的权重，否则返回零
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
