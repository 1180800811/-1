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
     * ����: graph : empty 
     * 	     vertex : empty
     * 		  edges : empty
     */
    @Test
    public void testAllEmpty() {
    	Graph<String> vertex =  emptyInstance();//ͼΪ��
    	String string = new String();
    	string +="�㼯��" + "\n";
    	string +="�߼���" + "\n";
    	assertEquals(string, vertex.toString());//ͼΪ�յ�toString����
    }
    
    /*
     * ����: graph : not empty 
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
    	string +="�㼯��";
    	string +=s1 + " "+ s2 + " " + "\n"  ;
    	string +="�߼���" + "\n";
    	assertEquals(string, vertex.toString());//���㲻Ϊ�յ��Ǳ߼�Ϊ�յ�toString����
    }

    
    /*
     * ����: graph : not empty 
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
    	string +="�㼯��";
    	string +=s3 +" " + s1 + " " + s2 + " " + "\n";
    	string +="�߼���";
    	string +=s1 + " -> " +  s2 + " : " +weight1 + "\n" ;
    	string +=s2 + " -> " +  s3 + " : " +weight2 + "\n" ;
    	string +="\n";
    	assertEquals(string, vertex.toString());//���㲻Ϊ���ұ߼���Ϊ�յ�toString����
    	
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
    	 assertEquals(source, edge.getSource());//���Աߵ�Դ����
    }
    
    @Test
    public void testGetTarget() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	 Edge<String> edge = new Edge<>(source,target,weight);
    	 assertEquals(target, edge.getTarget());//���Աߵ�Ŀ�궥��
    }
    
    @Test
    public void testGetWeight() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	 Edge<String> edge = new Edge<>(source,target,weight);
    	 assertEquals(1, edge.getWeight());//���Աߵ�Ȩ��
    }
    @Test
    public void testDifferentHashCode() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge1 = new Edge<>(source,target,weight);
    	Edge<String> edge2 = new Edge<>(target,source,weight);
    	assertNotEquals(edge1.hashCode(), edge2.hashCode());//���Բ�ͬ�ıߵ�hashCode����
    }
    
    @Test
    public void testSameHashCode() {
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge1 = new Edge<>(source,target,weight);
    	Edge<String> edge2 = new Edge<>(source,target,weight);
    	assertEquals(edge1.hashCode(), edge2.hashCode());//������ͬ�ıߵ�hashCode����
    }
    
    @Test
    public void testSameEquals() {//����������ͬ�Ķ����equals����
    	final String source = "s1" ; 
    	final String target = "s2" ;
    	final int weight = 1 ;
    	Edge<String> edge1 = new Edge<>(source,target,weight);
    	Edge<String> edge2 = new Edge<>(source,target,weight);
    	assertTrue(edge1.equals(edge2));
    	assertEquals(edge1, edge1);
    }
    
    @Test
    public void testDifferentEquals() {//����������ͬ�Ķ����Equals����
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
    	assertEquals(result, edge.toString());//����Edges���toString����
    }
	

    
    
    
    
}
