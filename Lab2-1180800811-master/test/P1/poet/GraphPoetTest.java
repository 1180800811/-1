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
    /*����: empty file
     * 		empty input
     */
    @Test
    public void testEmptyFile() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	final String input = "" ; 
    	assertEquals("", graph.poem(input));
    	assertEquals(0, graph.poem(input).length());
    }
    
    
    /* 	����: empty file
     * 		input with one word
     */
    @Test
    public void testEmptyFileOneWord() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	final String input = "single" ; 
    	assertEquals(input, graph.poem(input));
    }
    
    

    /* 	����: file with one line
     * 		 input :several words
     */
    @Test
    public void testOneLineSeveralWord() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/oneline.txt"));
    	final String input = "I have dream of being teacher !" ;
    	assertEquals("I have a dream of being teacher !",graph.poem(input));
	}
    
    /*
     * 	����: file with more lines
     * 		 several word
     */
    @Test
    public void testMoreLineSeveralWord1() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/morelines.txt"));
    	final String input = "It a happy day ! You will konw how happy that are" ;
    	assertEquals("It is a happy day ! You will konw how happy that you are",graph.poem(input));
    	
	}
    /**
          * �����ŽӴ��ж��ʱ��graph.poem()����
          *  ����: file with more lines
     *      input : several words
     */
    @Test
    public void testMoreLineSeveralWord2() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/morelines.txt"));
    	final String input = "I find that are so intersting" ;//�ŽӴ���they��you,ѡ�� Ȩ�ظ����you
    	assertEquals("I find that you are so intersting",graph.poem(input));
    	
	}
    /**
     * test graph.toString()
     * 	����: empty graph 
     * @throws IOException 
     */
    @Test
    public void testEmptyToString() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	String result = "�㼯��" + "\n" + "�߼���" + "\n" ;
    	assertEquals(result, graph.toString());
    }
    
    /**
     * test graph.toString()
     * 	����: graph with one vertex 
     * @throws IOException 
     */
    @Test
    public void testOneVertexToString() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/oneword.txt"));
    	String result = "�㼯��" + "vertex " + "\n" + "�߼���" + "\n" ;
    	assertEquals(result, graph.toString());
    }
    
    /**
     * test graph.toString()
     * 	����: graph with several vertex 
     * @throws IOException 
     */
    @Test
    public void testSeveralVertexToString() throws IOException {
    	final GraphPoet graph = new GraphPoet(new File("test/P1/poet/oneline.txt"));
    	String result = new String();
    	result += "�㼯��a big dream have i , " + "\n" ;
    	result +="�߼���i -> have : 1" +"\n" ;
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

