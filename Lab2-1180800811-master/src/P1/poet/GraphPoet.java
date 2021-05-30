/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   GraphPoet represents a word affinity graph which is generated with a corpus
    //	and vertices of is are case-insensitive words
    //	and edge weights of it are in-order adjacency counts.
    //
    // Representation invariant:
    //   graph != null , vertices of the graph not empty case-insensitive strings
    //
    // Safety from rep exposure:
    //   All fields are modified by private and final so that
    //	clients can't access the graph reference outside the class

    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(corpus));
        String line ;//读入的每一行
        String result[]	 = new String[10000];//将所有的单词保存到数组里
        int count = 0 ;
        while((line = in.readLine() )!=null) {
        	String string[] = line.split(" ") ;
        	for(int i = 0 ; i < string.length ; i++) {
        		result[count++] = string[i].toLowerCase();//不区分大小写
        	}
        }
        if(count > 0)
        	graph.add(result[count-1]) ;
        for(int i = 0 ; i < count -1 ; i ++) {
        	graph.add(result[i]);
        	int w = graph.set(result[i], result[i+1], 0)  +1 ; //获取权重
        	graph.set(result[i], result[i+1], w);
        }
        in.close();
        checkRep();
    }
    
    // TODO checkRep
    public void checkRep() {
    	assert graph != null ;
    	for(String vertex : graph.vertices()) {
    		assert !vertex.equals("");//判断顶点是非为空内容
    		assert vertex.equals(vertex.toLowerCase());//判断顶点是否都转换为小写
    	}
    }
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	StringBuilder stringBuilder = new StringBuilder();//得到的诗句
        List<String> list = new ArrayList<>(Arrays.asList(input.split(" ")));
        Map<String, Integer> sourceMap;//源顶点集合
        Map<String, Integer> targetMap;//目标顶点集合
        for (int i = 0; i < list.size() - 1; i++) {
            String source = list.get(i).toLowerCase();//变成小写
            String target = list.get(i + 1).toLowerCase();//变成小写
            stringBuilder.append(list.get(i)).append(" ");
            targetMap = graph.targets(source);
            sourceMap = graph.sources(target);
            int maxWeight = 0;
            String bridgeWord = "";
            for (String string : targetMap.keySet()) {
                if (sourceMap.containsKey(string)) {
                    if (sourceMap.get(string) + targetMap.get(string) > maxWeight) {
                        maxWeight = sourceMap.get(string) + targetMap.get(string);//权重最大
                        bridgeWord = string;
                    }
                }
            }
            if (maxWeight > 0) {
                stringBuilder.append(bridgeWord + " ");
            }
        }
        stringBuilder.append(list.get(list.size() - 1));//添加到得到的诗句中
        return stringBuilder.toString();
    }
    
    // TODO toString()
    @Override 
    public String toString() {//重写toString方法
    	return  graph.toString();
    }
    
}
