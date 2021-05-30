/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   graph:empty , not empty
    //	 vertices:  not existed , existed without edges ,  existed with edges
    
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
         * ����ͼΪ�յ�����µ�toString����
         * ���ǣ�graph : empty
     * 	  vertices : not existed
     */
    @Test
    public void testEmptyGraphToString() {
    	Graph<String> graph = emptyInstance();
    	String result = "���еĶ�����:" ;
    	result += "\n" ;
    	result += "\n" ; 
    	result +="���еĶ��㼰���Ӧ�ı߼���Ȩ����" ;
    	result += "\n" ;
    	assertEquals(result, graph.toString());
    }
    /*
         * ����ͼ��Ϊ�յ��Ƕ����ޱߵ�����µ�toString����
         * ���ǣ�graph : not empty
     * 	    vertices :  existed without edges
     */
    @Test
    public void testVerticesWithoutEdgesToString() {
    	Graph<String> graph = emptyInstance();
    	String s1 = "s1" ; 
    	String s2 = "s2" ;
    	graph.add(s1);
    	graph.add(s2);
    	String result = "���еĶ�����:" ;
    	result += "\n" ;
    	result += s1 + " " + s2 + " " ;
    	result += "\n" ; 
    	result +="���еĶ��㼰���Ӧ�ı߼���Ȩ����" ;
    	result += "\n" ;
    	assertEquals(result, graph.toString());
    }
    
    /*
     * ����ͼ��Ϊ���Ҷ����бߵ�����µ�toString����
     * ���ǣ�graph : not empty
   * 	  vertices :  existed without edges
   */
    @Test
    public void testVerticesWithEdgesToString() {
    	Graph<String> graph = emptyInstance();
    	String s1 = "s1" ; 
    	String s2 = "s2" ;
    	final int weight = 1   ;
    	graph.add(s1);
    	graph.add(s2);
    	graph.set(s1, s2, weight);
    	String result = "���еĶ�����:" ;
    	result += "\n" ;
    	result += s1 + " " + s2 + " " ;
    	result += "\n" ; 
    	result +="���еĶ��㼰���Ӧ�ı߼���Ȩ����" ;
    	result += "\n" ;
    	result += s1 + " -> " +  s2 +  ":" + weight+ "\n"  ;
    	assertEquals(result, graph.toString());
    }
    
    
    // Testing strategy for Vertex
    //   TODO
    /*
     * Partition for vertex.getSource
     * 		input : vertex
     * 		output: the name of vertex
     * 
     * Partition for vertex.addEdge
     * 		input :  target vertex , weight 
     * 		output:   null
     * 
     * Partition for vertex.remove
     * 		input :  target vertex 
     * 		output : null
     * 
     * Partition for vertex.getRelationship
     * 		input : vertex without edges , vertex with edges
     *  	output : the copy of the map
     * Partition for vertex.getWeight 
     *  	input : the target vertex
     *  	output : weight if there is an edge from vertex to target 
     *  			 0 if not 
     * Partition for vertex.Equals
     * 		input : equal to vertex , not equal to vertex
     * 		output : true if equal , false if not equal 
     * 
     * Partition for vertex.hashCode
     * 		input : vertex 
     * 		output : the hashCode of the vertex
     * 
     * Partition for vertex.toString
     * 		input : vertex with edges
     * 		output : null 
     */
    // TODO tests for operations of Vertex
    
    @Test
    public void testVertexgetSource(){//����getSource����
    	final String name = "s1" ; 
    	Vertex<String>  vertex = new Vertex<>(name) ;
    	assertEquals(name, vertex.getSource());
    }
    @Test
    public void testVertexAddEdge(){//����addEdge����
    	final String s1 = "s1" ;
    	final String s2 = "s2" ; 
    	final int weight = 1 ;
    	Vertex<String>  vertex = new Vertex<>(s1) ;
    	vertex.addEdge(s2, weight);
    	assertEquals(true, vertex.getRelationship().containsKey(s2));
    	assertEquals(weight, vertex.getWeight(s2));
    }
    
    @Test
    public void testVertexRemove(){//����Vertex���Remove����
    	final String s1 = "s1" ;
    	final String s2 = "s2" ; 
    	final int weight = 1 ;
    	Vertex<String>  vertex = new Vertex<>(s1) ;
    	vertex.addEdge(s2, weight);
    	assertEquals(true, vertex.getRelationship().containsKey(s2));
    	assertEquals(weight, vertex.getWeight(s2));
    	vertex.remove(s2);
    	assertEquals(Collections.EMPTY_MAP, vertex.getRelationship());
    }
    
    @Test
    public void testVertexGetRelationshipWithoutEdges(){//����û�бߵ�getRelationship����
    	final String s1 = "s1" ; 
    	Vertex<String>  vertex = new Vertex<>(s1) ;
    	assertEquals(Collections.EMPTY_MAP, vertex.getRelationship());
    }
    
    @Test
    public void testVertexGetRelationshipWithEdges(){//�����бߵ�getRelationship����
    	final String s1 = "s1" ;
    	final String s2 = "s2" ; 
    	final int weight = 1 ;
    	Vertex<String>  vertex = new Vertex<>(s1) ;
    	vertex.addEdge(s2, weight);
    	Map<String , Integer> map = new HashMap<>();
    	map.put(s2, weight);
    	assertEquals(map, vertex.getRelationship());
    }
    
    @Test
    public void testVertexGetWeight() {//����getWeight����
    	final String s1 = "s1" ; 
    	final String s2 = "s2" ; 
    	final int weight = 1 ;
    	Vertex<String>  vertex = new Vertex<>(s1) ;
    	vertex.addEdge(s2, weight);
    	assertEquals(weight, vertex.getWeight(s2));
  
    }
    
    @Test
    public void testVertexEquals() {//����Vertex���Equals����
    	final String s1 = "s1" ; 
    	final String s2 = "s2" ; 
    	Vertex<String> vertex1 = new Vertex<>(s1);
    	Vertex<String> vertex2 = new Vertex<>(s1); 
    	Vertex<String> vertex3 = new Vertex<>(s2);
    	assertEquals(true,vertex1.equals(vertex2));
    	assertFalse(vertex1.equals(vertex3));
    }
    
    @Test
    public void testVertexHashCode() {//����Vertex���HashCode����
    	final String s1 = "s1" ; 
    	final String s2 = "s2" ;
    	Vertex<String> vertex1 = new Vertex<>(s1);
    	Vertex<String> vertex2 = new Vertex<>(s2);
    	Vertex<String> vertex3 = new Vertex<>(s1);
    	assertEquals(vertex1.hashCode(),vertex3.hashCode());
    	assertNotEquals(vertex1.hashCode(), vertex2.hashCode());
    }
    
    @Test
    public void testVertexToString() {//����Vertex���toString����
    	final String s1 = "s1" ; 
    	final String s2 = "s2" ;
    	final int weight = 1 ;
    	Vertex<String> vertex = new Vertex<>(s1) ;
    	vertex.addEdge(s2, weight);
    	String result = s1 + " -> " +  s2 +  ":" + weight + "\n";
    	assertEquals(result, vertex.toString());
    }
    
}
