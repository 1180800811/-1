/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    /*   graph : empty , not empty 
     * 	 vertex : empty , not empty
     * 	 edges : empty  , not  empty
     * 
     */  
    
    // TODO tests for ConcreteEdgesGraph.toString()
    /*
     * 覆盖: graph : empty 
     * 	     vertex : empty
     * 		  edges : empty
     */
    @Test
    public void testAllEmpty() {
    	Graph<String> vertex =  emptyInstance();//图为空
    	String string = new String();
    	string +="点集：" + "\n";
    	string +="边集：" + "\n";
    	assertEquals(string, vertex.toString());//图为空的toString方法
    }
    
    /*
     * 覆盖: graph : not empty 
     * 	     vertex : not empty
     * 		  edges : empty
     */
    @Test
    public void testEdgesEmpty() {
    	Graph<String> vertex =  emptyInstance();
    	String s1 = new String("s1");
    	String s2 = new String("s2");
    	vertex.add(s1);
    	vertex.add(s2);
    	String string = new String();
    	string +="点集：";
    	string +=s1 + " "+ s2 + " " + "\n"  ;
    	string +="边集：" + "\n";
    	assertEquals(string, vertex.toString());//顶点不为空但是边集为空的toString方法
    }

    
    /*
     * 覆盖: graph : not empty 
     * 	     vertex : not empty
     * 		  edges : not empty
     */
    @Test
    public void testAllNotEmpty() {
    	Graph<String> vertex =  emptyInstance();
    	String s1 = new String("s1");
    	String s2 = new String("s2");
    	String s3 = new String("s3");
    	vertex.add(s1);
    	vertex.add(s2);
    	vertex.add(s3);
    	final int weight1 = 1 ;
    	final int weight2 = 2 ;
    	vertex.set(s1, s2, weight1);
    	vertex.set(s2, s3, weight2);
    	String string = new String();
    	string +="点集：";
    	string +=s3 +" " + s1 + " " + s2 + " " + "\n";
    	string +="边集：";
    	string +=s1 + " -> " +  s2 + " : " +weight1 + "\n" ;
    	string +=s2 + " -> " +  s3 + " : " +weight2 + "\n" ;
    	string +="\n";
    	assertEquals(string, vertex.toString());//顶点不为空且边集不为空的toString方法
    	
    }
          
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    /*
     * Partition for edge.getSource()
     * 		input : edge	
     * 		output : source
     * 
     * Partition for edge.getTarget()
     * 		input : edge	
     * 		output : target
     * 
     * Partition for edge.getWeight()
     * 		input : edge	
     * 		output : weight
     * 
     * Partition for edge.hashCode()
     * 		input : edge	
     * 		output : the hash code of edge
     * 
     * Partition for edge.equals()
     * 		input : equal to edge , not equal to edge 	
     * 		output : true if equal ,false if not equal
     * 
     * Partition for edge.toString()
     * 		input : edge
     * 		output:source.toString()  -> target.toString() : this.weight;
     * 
     */
    
    
    // TODO tests for operations of Edge
    
    @Test
    public void testGetSource() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	 Edge<String> edge = new Edge<>(source,target,weight);
    	 assertEquals(source, edge.getSource());//测试边的源顶点
    }
    
    @Test
    public void testGetTarget() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	 Edge<String> edge = new Edge<>(source,target,weight);
    	 assertEquals(target, edge.getTarget());//测试边的目标顶点
    }
    
    @Test
    public void testGetWeight() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	 Edge<String> edge = new Edge<>(source,target,weight);
    	 assertEquals(1, edge.getWeight());//测试边的权重
    }
    @Test
    public void testDifferentHashCode() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge1 = new Edge<>(source,target,weight);
    	Edge<String> edge2 = new Edge<>(target,source,weight);
    	assertNotEquals(edge1.hashCode(), edge2.hashCode());//测试不同的边的hashCode方法
    }
    
    @Test
    public void testSameHashCode() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge1 = new Edge<>(source,target,weight);
    	Edge<String> edge2 = new Edge<>(source,target,weight);
    	assertEquals(edge1.hashCode(), edge2.hashCode());//测试相同的边的hashCode方法
    }
    
    @Test
    public void testSameEquals() {//测试两个相同的顶点的equals方法
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge1 = new Edge<>(source,target,weight);
    	Edge<String> edge2 = new Edge<>(source,target,weight);
    	assertTrue(edge1.equals(edge2));
    	assertEquals(edge1, edge1);
    }
    
    @Test
    public void testDifferentEquals() {//测试两个不同的顶点的Equals方法
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge1 = new Edge<>(source,target,weight);
    	Edge<String> edge2 = new Edge<>(target,source,weight);
    	assertfalse(edge1.equals(edge2));
    	assertEquals(false, edge2.equals(edge1));
    	assertfalse(edge1.equals(null));
    }
    
    private void assertfalse(boolean equals) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void testToString() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge = new Edge<>(source,target,weight);
    	String result =  new String();
    	result += source + " -> " + target + " : " + weight + "\n" ;
    	assertEquals(result, edge.toString());//测试Edges类的toString方法
    }
	

    
    
    
    
}
