/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
	//	
	//	Partition for the constructor of GraphPoet
	//		corpus : empty file , file with one line , file with several lines
	//
	//Partition for GraphPoet.poem
	//		input : empty string , one word , more than one word
	//
	//Partition for GraphPoet.toString
	//		input : empty graph , one vertex ,more than one vertex
	//

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    /*覆盖: empty file
     * 		empty input
     */
    @Test
    public void testEmptyFile() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	final String input = "" ; 
    	assertEquals("", graph.poem(input));
    	assertEquals(0, graph.poem(input).length());
    }
    
    
    /* 	覆盖: empty file
     * 		input with one word
     */
    @Test
    public void testEmptyFileOneWord() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	final String input = "single" ; 
    	assertEquals(input, graph.poem(input));
    }
    
    

    /* 	覆盖: file with one line
     * 		 input :several words
     */
    @Test
    public void testOneLineSeveralWord() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/oneline.txt"));
    	final String input = "I have dream of being teacher !" ;
    	assertEquals("I have a dream of being teacher !",graph.poem(input));
	}
    
    /*
     * 	覆盖: file with more lines
     * 		 several word
     */
    @Test
    public void testMoreLineSeveralWord1() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/morelines.txt"));
    	final String input = "It a happy day ! You will konw how happy that are" ;
    	assertEquals("It is a happy day ! You will konw how happy that you are",graph.poem(input));
    	
	}
    /**
          * 测试桥接词有多个时的graph.poem()方法
          *  覆盖: file with more lines
     *      input : several words
     */
    @Test
    public void testMoreLineSeveralWord2() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/morelines.txt"));
    	final String input = "I find that are so intersting" ;//桥接词有they和you,选择 权重更大的you
    	assertEquals("I find that you are so intersting",graph.poem(input));
    	
	}
    /**
     * test graph.toString()
     * 	覆盖: empty graph 
     * @throws IOException 
     */
    @Test
    public void testEmptyToString() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	String result = "点集：" + "\n" + "边集：" + "\n" ;
    	assertEquals(result, graph.toString());
    }
    
    /**
     * test graph.toString()
     * 	覆盖: graph with one vertex 
     * @throws IOException 
     */
    @Test
    public void testOneVertexToString() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/oneword.txt"));
    	String result = "点集：" + "vertex " + "\n" + "边集：" + "\n" ;
    	assertEquals(result, graph.toString());
    }
    
    /**
     * test graph.toString()
     * 	覆盖: graph with several vertex 
     * @throws IOException 
     */
    @Test
    public void testSeveralVertexToString() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/oneline.txt"));
    	String result = new String();
    	result += "点集：a big dream have i , " + "\n" ;
    	result +="边集：i -> have : 1" +"\n" ;
    	result +="have -> a : 1" + "\n" ;
    	result +="a -> dream : 1" + "\n" ;
    	result +="dream -> , : 1" + "\n" ;
    	result +=", -> a : 1" + "\n" ;
    	result +="a -> big : 1" + "\n" ;
    	result += "big -> dream : 1" +"\n";
    	result +="\n" ;
    	assertEquals(result, graph.toString());
    }
    
}

