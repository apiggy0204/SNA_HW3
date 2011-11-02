package helper;

import helper.*;
import helper.MyNode.Label;

import java.io.*;
import java.util.*;
import edu.uci.ics.jung.graph.*;

public class Model implements Cloneable{
	
	DirectedGraph<MyNode,MyLink> graph = null;
	Map<Integer,MyNode> map = null;
	
	ArrayList<MyNode> nodeList = null; 
	
	ArrayList<TreeSet<MyNode>> answerA = null;
	ArrayList<TreeSet<MyNode>> answerB = null;
	
	TreeSet<MyNode> time_candid = null;
	TreeSet<MyNode> person_candid = null;
	TreeSet<MyNode> place_candid = null;
	TreeSet<MyNode> movie_candid = null;		
	
	public Model(Model model) {
		// TODO Auto-generated constructor stub
		Model retModel = new Model();
		retModel.map = new TreeMap<Integer, MyNode>(model.map);
		retModel.graph = new DirectedSparseGraph<MyNode,MyLink>();
		/*for(MyNode node : model.graph.getVertices()){
			//graph.
		}*/
	}

	public Model() {
		
		graph=null;
		map=null;
		
		nodeList = new ArrayList<MyNode>(); 
		
		answerA = new ArrayList<TreeSet<MyNode>>();
		answerB = new ArrayList<TreeSet<MyNode>>();
		
		time_candid = new TreeSet<MyNode>();
		person_candid = new TreeSet<MyNode>();
		place_candid = new TreeSet<MyNode>();
		movie_candid = new TreeSet<MyNode>();
	}

	public void getInitial() throws IOException{
		GraphReader reader = new GraphReader("hw3.masklink_1029.txt");
		graph=reader.readGraph();
		map=reader.getMap();
		for(MyNode node : graph.getVertices()) nodeList.add(node);		
		answerA.add(movie_candid);
		answerA.add(place_candid);
		answerA.add(time_candid);
		answerA.add(person_candid);
		
		for (int i=0;i<10;i++){
			answerB.add(new TreeSet<MyNode>());
		}
	}
	
	public void partFirst(){
		// first partition
		// find time  
		for (MyNode node : graph.getVertices()){
			int neighborNum=graph.getNeighborCount(node);
			if (neighborNum>43 && neighborNum<=300){
				time_candid.add(node);
			}
		}
		
		// use time to find movie
		for (MyNode time : time_candid){
			for (MyNode neighbor : graph.getNeighbors(time)){
				if (!time_candid.contains(neighbor) && 
					!movie_candid.contains(neighbor)){
					movie_candid.add(neighbor);
				}
			}
		}
		
		TreeSet<MyNode> deleteList = new TreeSet<MyNode>();
		
		for (MyNode movie : movie_candid){
			int n=0;
			for (MyNode neighbor : graph.getNeighbors(movie)){
				if (time_candid.contains(neighbor)){
					++n;
				}
			}
			if (n>1) deleteList.add(movie);
		}
		
		for (MyNode delete : deleteList){
			movie_candid.remove(delete);
		}
		
		//Add all remaining nodes to person_candid and move some of them to place_candid
		deleteList = null;
	
		for (MyNode person : graph.getVertices()){
			if (!movie_candid.contains(person) && !time_candid.contains(person)){
				person_candid.add(person);
			}
		}
		
		TreeSet<MyNode> person_neighbor = getNeighbor(person_candid);
		TreeSet<MyNode> movie_neighbor = getNeighbor(movie_candid);
		

		ArrayList<MyNode> transferList=new ArrayList<MyNode>();
		
		for (MyNode transfer : person_candid){
			if (person_neighbor.contains(transfer) 
					&& movie_neighbor.contains(transfer)){
				transferList.add(transfer);
			}
		}
		
		for (MyNode place : transferList){
			if (graph.getNeighborCount(place)>10){
				place_candid.add(place);
				person_candid.remove(place);	
			}
		}
	}
	
	public void writerOutcomeA(String S) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(S));
		for (TreeSet<MyNode> array : answerA){
			for (MyNode node : array){
			writer.write(node.getId()+" ");
			}
			writer.newLine();
		}
		if (writer!=null){
			writer.close();
		}
	}
	
	public TreeSet<MyNode> getNeighbor(TreeSet<MyNode> set){
		TreeSet<MyNode> r = new TreeSet<MyNode>();
		for (MyNode node : set){
			for (MyNode neighbor:graph.getNeighbors(node)){
				if (!r.contains(neighbor)){
					r.add(neighbor);
				}
			}
		}
		return r;
	}
	
	
	public static void main(String[] args)throws IOException {		
		
		// Initial Step
		
		Model model = new Model();
		model.getInitial();
		model.partFirst();
		
		//Label every node
		for(MyNode movie : model.movie_candid) movie.setLabel(Label.Movie);
		for(MyNode movie : model.place_candid) movie.setLabel(Label.Place);
		for(MyNode movie : model.time_candid) movie.setLabel(Label.Time);
		for(MyNode movie : model.person_candid) movie.setLabel(Label.Person);		
		
		//output result
		System.out.println(model.movie_candid.size());
		System.out.println(model.place_candid.size());
		System.out.println(model.time_candid.size());
		System.out.println(model.person_candid.size());
		
		model.writerOutcomeA("outcomeA.txt");
	}
}
