package helper;

import java.io.*;
import java.util.*;
import edu.uci.ics.jung.graph.*;

public class GraphReader {
	
	Scanner scan = null;
	DirectedGraph<MyNode,MyLink> graph=null;
	Map<Integer,MyNode> map = new HashMap<Integer,MyNode>();
	
	public GraphReader(String s) throws IOException{
		scan =new Scanner(new BufferedReader(new FileReader(s)));
	}
	
	public boolean hasNextPair(){
		if (scan.hasNextInt()){
			return true;
		}
		else return false;
	}
	
	public Integer[] nextPair(){
		Integer[] pair = new Integer[2];
		pair[0]=scan.nextInt();
		pair[1]=scan.nextInt();
		return pair;
	}
	
	public ArrayList<Integer[]> getEdges(){
		ArrayList<Integer[]> array = new ArrayList<Integer[]>();
		while (hasNextPair()){
			array.add(nextPair());
		}
		return array;
	}
	
	public DirectedGraph<MyNode,MyLink> readGraph(){
		DirectedGraph<MyNode,MyLink> newGraph = new DirectedSparseGraph<MyNode,MyLink>();
		ArrayList<Integer[]> edges = getEdges();
		//System.out.println(edges.size());
		while(!edges.isEmpty()){
			Integer[] temp = edges.get(0);
			//System.out.println(temp[0]+ "  "+temp[1]);
			MyNode node1=map.get(temp[0]);
			if (node1==null){
				node1 = new MyNode(temp[0]);
				map.put(temp[0], node1);
			}
			MyNode node2=map.get(temp[1]);
			if (node2==null){
				node2 = new MyNode(temp[1]);
				map.put(temp[1], node2);
			}
			newGraph.addEdge(new MyLink(), node1, node2);
			edges.remove(0);
		}			
		return newGraph;
	}
	// getter of map   //must use after readGraph
	public Map<Integer,MyNode> getMap(){
		return map;
	}


}
