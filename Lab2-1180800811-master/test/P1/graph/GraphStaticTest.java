/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2
    // test label of Integer
    @Test
    public void testIntegerLabel() {
    	Graph<Integer> adjph = Graph.empty();
    	final Integer x1 = 1 ;
    	final Integer x2 = 2 ; 
    	final Integer x3 = 3 ;
    	final int weight1 = 1 ;
    	final int weight2 = 2 ;
    	final int weight3 = 3 ;
    	assertEquals(true, adjph.add(x1));
    	assertEquals(true, adjph.add(x2));
    	assertEquals(true, adjph.add(x3));
    	assertEquals(0, adjph.set(x1, x2, weight1));
    	assertEquals(0, adjph.set(x2, x3, weight2));
    	assertEquals(1,adjph.set(x1, x2, weight3));
    	assertEquals(true, adjph.remove(x1));
    	
    }
 // test label of Character
    @Test
    public void testCharacterLabel() {
    	Graph<Character> adjph = Graph.empty();
    	final Character x1 = 'A' ;
    	final Character x2 = 'B' ; 
    	final Character x3 = 'C' ;
    	final int weight1 = 1 ;
    	final int weight2 = 2 ;
    	final int weight3 = 3 ;
    	assertEquals(true, adjph.add(x1));
    	assertEquals(true, adjph.add(x2));
    	assertEquals(true, adjph.add(x3));
    	assertEquals(0, adjph.set(x1, x2, weight1));
    	assertEquals(0, adjph.set(x2, x3, weight2));
    	assertEquals(1,adjph.set(x1, x2, weight3));
    	assertEquals(true, adjph.remove(x1));
    
}
    
}
