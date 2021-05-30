/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    /*	Testing strategy partition :
     *   Empty graph : yes , no
     *   vertex :  existed , not existed
     *   weight of edges : 0 , > 0
     *   illegal weight : negative or not an integer
     *    
     *Partition for inputs of garph.add(input)
     *                          graph: empty , not empty
     *                          vertex : existed , not existed
     * 
     * Partition for inputs of garph.remove(input)
     * 							graph: empty , not empty
     * 							vertex : not existed ,existed with edges ,
     * 									existed without edges
     * Partition for inputs of garph.set(source �� target ��weight)
     * 							graph : empty , not empty
     * 							source: existed �� not existed
     * 							target: existed �� not existed
     * 							weight:  0, >0
     * 							edge:  not existed , existed
     * Partition for graphs.vertices()
     * 							graph: empty , not empty
     * Partition for inputs of graph.source(target)
     * 							graph: empty , not empty
     * 							target: existed with edges source to, existed without edges source to
     * 									not existed
     * Partition for inputs of graph.target(source)
     * 							graph:empty , not empty
     * 							source : existed with edges target to ,existed without edges with target to
     * 									not existed
     * 							
     */

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    
    /*
     * �������ͼ������һ���µĶ���
     * ���ǣ�empty graph , not existed vertex 
     */
    @Test
    public void testAddNewVertex() {
    	Graph<String> adjph = emptyInstance() ; 
    	final String vertex = "vertex" ;
    	assertEquals(true, adjph.add(vertex));
    	assertEquals(false, adjph.vertices().isEmpty());
    	assertEquals(true, adjph.vertices().contains(vertex));
    }
    /*
     * ������ͼ������һ���Ѿ����ڵĶ���
     * ���ǣ�not empty graph , existed vertex
     */
    @Test
    public void testAddExistedVertex() {
    	Graph<String> adjph = emptyInstance() ; 
    	final String vertex = "vertex" ;
    	adjph.add(vertex);
    	assertEquals(false, adjph.add(vertex));
    	assertEquals(true, adjph.vertices().contains(vertex));
    }
    /*
     * ������ͼ��ɾȥһ�������ڵĶ���
     * ���ǣ� empty graph , not existed vertex
     */
    @Test 
    public void testRemoveNewVertex() {
    	Graph<String> adjph = emptyInstance() ; 
    	final String vertex = "vertex" ;
    	assertFalse(adjph.remove(vertex));
    }
    /*
     * ������ͼ��ɾȥһ���Ѿ����ڵĵ���û�б��ڽӵĶ���
     * ���ǣ� not empty graph ,  existed vertex without edges
     */
    @Test
    public void testRemoveExistedWithoutEdgesVertex() {
    	Graph<String> adjph = emptyInstance() ; 
    	final String vertex = "vertex" ;
    	adjph.add(vertex);
    	assertEquals(true, adjph.vertices().contains(vertex));
    	assertEquals(true, adjph.remove(vertex));
    	assertTrue(adjph.vertices().isEmpty());
    }
    /*
     * ������ͼ��ɾȥһ���Ѿ����ڵĶ����б��ڽӵĶ���
     * ���ǣ� not empty graph ,  existed vertex with edges
     */
    public void testRemoveExistedWithEdgesVertex() {
    	Graph<String> adjph = emptyInstance() ; 
    	final String vertex1 = "vertex1" ;
    	final String vertex2 = "vertex2" ;
    	final int weight =  1 ;
    	adjph.set(vertex1, vertex2, weight);
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(true, adjph.remove(vertex1));
    	assertEquals(false, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(Collections.EMPTY_MAP, adjph.sources(vertex2));
    }
    /*
     * ����һ��ͼ��û���ڽӱߵ�source����
     * ����: empty graph , not existed vertex
     */
    @Test
    public void testSourceWithoutEdges() {
    	Graph<String> adjph = emptyInstance() ;
    	String vertex = "vertex" ; 
    	assertEquals(Collections.EMPTY_SET, adjph.vertices());
    	assertEquals(Collections.EMPTY_MAP, adjph.sources(vertex));
    }
    
    
    /*
     * ������һ��ͼ�м���һ��ȨֵΪ0���������������ͼ�д��ڵ�set����
     * ����:  graph: empty
     * 		  source : not existed
     * 		  target : not existed
     * 		  weight : 0
     * 		  edge  :  not existed
     */
    @Test
    public void testSetWeightZeroNotExistedVertex() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight = 0 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";
    	assertEquals(0, adjph.set(vertex1, vertex2, weight));
    	assertTrue(adjph.vertices().contains(vertex1));
    	assertTrue(adjph.vertices().contains(vertex2));
    }
    
    /*
     * ������һ��ͼ�м���һ��ȨֵΪ0�������������ͼ�д��ڵ�set����
     * ����:  graph:  not empty
     * 		  source : existed
     * 		  target :  existed
     * 		  weight : 0
     * 		  edge:   not existed
     */
    @Test
    public void testSetWeightZeroExistedVertexEdgeNotExisted() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight = 0 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";
    	adjph.add(vertex1);
    	adjph.add(vertex2);
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(0, adjph.set(vertex1, vertex2, weight));
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(Collections.EMPTY_MAP, adjph.sources(vertex2));
    	assertEquals(Collections.EMPTY_MAP, adjph.targets(vertex1));

    }
    /*
     * ������һ��ͼ�м���һ��ȨֵΪ0������������һ��δ��ͼ�д��ڵ�set����
     * ����:  graph:  not empty
     * 		  source : existed
     * 		  target : not existed
     * 		  weight : 0
     * 		  edge   : not existed
     */			
    @Test
    public void testSetWeightZeroEitherExistedVertex() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight = 0 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";
    	adjph.add(vertex1);
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(false, adjph.vertices().contains(vertex2));
    	assertEquals(0, adjph.set(vertex1, vertex2, weight));
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(Collections.EMPTY_MAP, adjph.targets(vertex1));

    }
    
    /*
     * ������һ��ͼ�м���һ��Ȩֵ����0���������㶼δ��ͼ�д��ڵ�set����
     * ����:  graph:  empty
     * 		  source : not existed
     * 		  target : not existed
     * 		  weight : > 0 
     * 		  edge   : not existed
     */			
    @Test
    public void testSetWeightPositiveNotExistedVertex() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight = 1 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";	
    	assertEquals(false, adjph.vertices().contains(vertex1));
    	assertEquals(false, adjph.vertices().contains(vertex2));
    	assertEquals(0, adjph.set(vertex1, vertex2, weight));
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(true, adjph.targets(vertex1).containsKey(vertex2));
    	assertEquals(true, adjph.sources(vertex2).containsKey(vertex1));
    }
    
    /*
     * ������һ��ͼ�м���һ��Ȩֵ����0���������㶼��ͼ�е��ǲ����ڱߵ�set����
     * ����:  graph:  not empty
     * 		  source :  existed
     * 		  target :  existed
     * 		  weight : > 0 
     * 		  edge   :  not existed
     */			
    @Test
    public void testSetWeightPositiveExistedVertex() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight = 1 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";	
    	adjph.add(vertex1);
    	adjph.add(vertex2);
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(0, adjph.set(vertex1, vertex2, weight));
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(true, adjph.targets(vertex1).containsKey(vertex2));
    	assertEquals(true, adjph.sources(vertex2).containsKey(vertex1));
    }
    
    
    /*
     * ������һ��ͼ�м���һ��Ȩֵ����0������������һ������δ��ͼ�д��ڵ�set����
     * ����:  graph:  not empty
     * 		  source : not existed
     * 		  target :  existed
     * 		  weight : > 0 
     * 		  edge   : not existed
     */			
    @Test
    public void testSetWeightPositiveEitherExistedVertex() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight = 1 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";
    	adjph.add(vertex2);
    	assertEquals(false, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(0, adjph.set(vertex1, vertex2, weight));
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(true, adjph.targets(vertex1).containsKey(vertex2));
    	assertEquals(true, adjph.sources(vertex2).containsKey(vertex1));
    }
    
    /*
     * ������һ��ͼ�м���һ��Ȩֵ����0���������㶼��ͼ�ж���ԭ�����ڱߵ�set����
     * ����:  graph:  not empty
     * 		  source :  existed
     * 		  target :  existed
     * 		  weight :  0 
     * 		  edge   :  existed
     */			
    @Test
    public void testSetWeightZeroExistedVertexEdgeExisted() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight = 1 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";	
    	adjph.add(vertex1);
    	adjph.add(vertex2);
    	adjph.set(vertex1, vertex2, weight);
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(weight, adjph.set(vertex1, vertex2, 0));
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(false, adjph.targets(vertex1).containsKey(vertex2));
    	assertEquals(false, adjph.sources(vertex2).containsKey(vertex1));
    }
    
    /*
     * ������һ��ͼ�м���һ��Ȩֵ����0���������㶼��ͼ����ԭ��ͼ�д��ڱߴ��ڵ�set����
     * ����:  graph:   empty
     * 		  source :  existed
     * 		  target :  existed
     * 		  weight : > 0 
     * 		  edge   :  existed
     */			
    @Test
    public void testSetWeightPositiveExistedVertexExistedEdge() {
    	Graph<String> adjph = emptyInstance() ;
    	final int weight1 = 1 ;
    	final int weight2 = 2 ;
    	String vertex1 = "vertex1";
    	String vertex2 = "vertex2";	
    	adjph.add(vertex1);
    	adjph.add(vertex2);
    	adjph.set(vertex1, vertex2, weight1);
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(true, adjph.targets(vertex1).containsKey(vertex2));
    	assertEquals(true, adjph.sources(vertex2).containsKey(vertex1));
    	assertEquals(weight1, adjph.set(vertex1, vertex2, weight2));
    	assertEquals(true, adjph.vertices().contains(vertex1));
    	assertEquals(true, adjph.vertices().contains(vertex2));
    	assertEquals(true, adjph.targets(vertex1).containsKey(vertex2));
    	assertEquals(true, adjph.sources(vertex2).containsKey(vertex1));
    }
    
    
    
    
    
    /*
     * ����һ��ͼ�����ڽӱߵĵ���û��source�����source����
     * ���ǣ�not empty graph ,  existed vertex  without edges sources to 
     */
	@Test
	public void testSourceWithoutEdgesSourceTo() {
    	Graph<String> adjph = emptyInstance() ; 
		final String vertex1 = "vertex1" ; 
		final String vertex2 = "vertex2" ; 
		final String vertex3 = "vertex3" ; 
		final int weight1 =  1 ; 
		final int weight2 = 2 ;
		adjph.set(vertex1, vertex2, weight1);
		adjph.set(vertex1, vertex3, weight2);
		assertEquals(Collections.EMPTY_MAP,adjph.sources(vertex1) );	
	}
    /*
     * ����һ��ͼ�����ڽӱߵĶ�����source�����source����
     * ���ǣ�not empty graph ,  existed vertex with edges sources to 
     */
	@Test
	public void testSourceWithEdgesSourceTo() {
    	Graph<String> adjph = emptyInstance() ; 
		final String vertex1 = "vertex1" ; 
		final String vertex2 = "vertex2" ; 
		final String vertex3 = "vertex3" ; 
		final int weight1 =  1 ; 
		final int weight2 = 2 ;
		adjph.set(vertex1, vertex3, weight1);
		adjph.set(vertex2, vertex3, weight2);
		assertEquals(true, adjph.sources(vertex3).containsKey(vertex1));
		assertEquals(true, adjph.sources(vertex3).containsKey(vertex1));
	}
    /*
     * ����һ��ͼ��û���ڽӱߵ�Target����
     * ����: empty graph , not existed vertex
     */
    @Test
    public void testTargetWithoutEdges() {
    	Graph<String> adjph = emptyInstance() ;
    	String vertex = "vertex" ; 
    	assertEquals(Collections.EMPTY_SET, adjph.vertices());
    	assertEquals(Collections.EMPTY_MAP, adjph.targets(vertex));
    }
    /*
     * ����һ��ͼ�����ڽӱߵĵ���û��target�����target����
     * ���ǣ�not empty graph ,  existed vertex  without edges targets to 
     */
	@Test
	public void testTargetWithoutEdgesTargetTo() {
    	Graph<String> adjph = emptyInstance() ; 
		final String vertex1 = "vertex1" ; 
		final String vertex2 = "vertex2" ; 
		final String vertex3 = "vertex3" ; 
		final int weight1 =  1 ; 
		final int weight2 = 2 ;
		adjph.set(vertex1, vertex3, weight1);
		adjph.set(vertex2, vertex3, weight2);
		assertEquals(Collections.EMPTY_MAP,adjph.targets(vertex3) );	
	}
    /*
     * ����һ��ͼ�����ڽӱߵĶ�����target�����target����
     * ���ǣ�not empty graph ,  existed vertex with edges targets to 
     */
	@Test
	public void testTargetWithEdgesTargetTo() {
    	Graph<String> adjph = emptyInstance() ; 
		final String vertex1 = "vertex1" ; 
		final String vertex2 = "vertex2" ; 
		final String vertex3 = "vertex3" ; 
		final int weight1 =  1 ; 
		final int weight2 = 2 ;
		adjph.set(vertex1, vertex2, weight1);
		adjph.set(vertex1, vertex3, weight2);
		assertEquals(true, adjph.targets(vertex1).containsKey(vertex2));
		assertEquals(true, adjph.targets(vertex1).containsKey(vertex3));
	}
}