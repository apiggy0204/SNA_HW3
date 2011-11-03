package sna_hw3;

import java.io.IOException;
import java.util.Random;

import helper.MyNode;
import helper.MyNode.Label;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try {			
			
			Model model = new Model();
			model.getInitial();
			int count = 0;
			for(MyNode node : model.graph.getVertices()){
				node.setLabel(Label.Time);				
				switch( count%4 ){
					case 0:
						node.setLabel(Label.Movie);
						break;
					case 1:
						node.setLabel(Label.Time);
						break;
					case 2:
						node.setLabel(Label.Person);
						break;
					case 3:
						node.setLabel(Label.Place);
						break;
					default:
						assert(false);
				}
				count++;
			}
			
			//Display counts			
			/*System.out.println(MyNode.getMovieCount());
			System.out.println(MyNode.getPlaceCount());
			System.out.println(MyNode.getTimeCount());
			System.out.println(MyNode.getPeopleCount());*/			
			
			System.out.println(GA.evaluate(model));
			
			
			//List degree distribution
			/*int[] degreeCount = new int[2400];
			int max = 0;
			for(MyNode node : model.graph.getVertices()){
				int degree = model.graph.degree(node);
				if(degree > max) max = degree;
				degreeCount[degree]++;
			}
			//System.out.println(max);
			for(int i=0; i<2400; i++) {
				System.out.println(String.valueOf(i) + " " + String.valueOf(degreeCount[i]));			
			}*/
			
			model.partFirst();
			
			//Label every node
			for(MyNode movie : model.movie_candid) movie.setLabel(Label.Movie);
			for(MyNode movie : model.place_candid) movie.setLabel(Label.Place);
			for(MyNode movie : model.time_candid) movie.setLabel(Label.Time);
			for(MyNode movie : model.person_candid) movie.setLabel(Label.Person);		
			
					
			System.out.println(GA.evaluate(model));
			
			//model.writerOutcomeA("outcomeA.txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
