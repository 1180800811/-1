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
     * 创建一个空的有向图
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
    	assert vertices != null ;//检查顶点集是否为空
    	for(L l : vertices) {
    		assert l != null ;//检查顶点是否为空
    	}
    	for(Edge<L> l : edges) {
    		assert l != null ;
    		assert l.getWeight() > 0;//检查权重是否大于零
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
        assert vertex != null ;
        if(vertices.contains(vertex)) //判断原来是否存在该顶点
        	return false;
        vertices.add(vertex);
        checkRep();//检查是否存在错误
        return true ;
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
    @Override public int set(L source, L target, int weight) {
       this.add(source);//添加顶点
       this.add(target);//添加顶点
       int x = -1 ;
       for(int i = 0 ; i < edges.size() ; i++) {
    	   L Source = (L)edges.get(i).getSource();
    	   L Target = (L) edges.get(i).getTarget();
    	   if(Source.equals(source)&&Target.equals(target)){
    		   x = i ;//原来source和target顶点有边
    		   break;
    	   }
       }
       int w = 0 ;//权重
       if(weight == 0 ) {
    	   if( x != -1 ) {//原来已经存在该边，则删除
    		   w = edges.get(x).getWeight();
    		   edges.remove(x);
    	   }
       }else {
    		   Edge<L> e = new Edge<L>(source,target,weight);
    		   if(x != -1) {//边存在
    			   w = edges.get(x).getWeight();
    			   edges.remove(x);  
    		   }
    		   edges.add(e);//将边添加进去
    	   }
       checkRep();
       return w ;//返回原来边的权重或者0
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
        if(!vertices.contains(vertex)) {
        	return false ;
        }
        vertices.remove(vertex);//删除该顶点
        for(int i  = 0 ; i < edges.size() ; i++) {
        	Edge<L> e = edges.get(i);
        	if(e.getSource().equals(vertex) || e.getTarget().equals(vertex)) {
        		edges.remove(i);//删除该顶点
        		i-- ; 
        	}
        }
        checkRep();
        return true ;
 }
    
    /**
     * 返回图中所有的顶点集
     * 
     * @param return 返回图中所有的顶点集
     */
    @Override public Set<L> vertices() {
        Set<L> result = new HashSet<L>();//防御性拷贝
        for(L l : vertices) {
        	result.add(l);
        }
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
        Map<L,Integer> result = new HashMap<>();//防御性拷贝
        for(Edge<L> e : edges) {
        	if(e.getTarget().equals(target)) {
        		result.put(e.getSource(), e.getWeight());
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
        Map<L,Integer> result = new HashMap<>();//防御性拷贝
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
    	public String toString() {//重写toString方法
    	String result = new String() ;
    	result += "点集：";
    	for(L l : vertices	) {
    		result += l.toString() + " " ;
    	}
    	result += "\n";
    	result += "边集：";
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
	 * @param source 边的source
	 * @param target 边的target	
	 * @param weight 边的权重
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
     * @return 返回边的target 
     */
    public L getTarget() {
    	checkRep();
    	return this.target;
    }
    /**
     * 
     * @return 返回边的source 
     */
    public L getSource() {
    	checkRep();
    	return this.source;
    }
    /**
     * 
     * @return 返回边的weight
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
