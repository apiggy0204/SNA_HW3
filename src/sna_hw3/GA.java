package sna_hw3;

import helper.MyNode;
import helper.MyNode.Label;


public class GA {
	
	/*public static void permute(Model model, double ratio)
	{
		Model retModel = new Model(model);
		for(int i=0; i<ratio*model.getNodeList().size(); i++)
		{			
			int randId1 = (int) ( model.getNodeList().size() * Math.random() );
			int randId2 = (int) ( model.getNodeList().size() * Math.random() );
			MyNode node1 = model.getNodeList().get(randId1);
			MyNode node2 = model.getNodeList().get(randId2);
			Label labelTemp = node1.getLabel();
			node1.setLabel(node2.getLabel());
			node2.setLabel(labelTemp);
		}
	}*/
	
	public static double evaluate(Model model){
		
		double score = 0;
		double timeScore = 0;
		double peopleScore = 0;
		double placeScore = 0;
		
		for(MyNode node : model.graph.getVertices()){
						
			double timeCount = 0;
			double peopleCount = 0;
			double placeCount = 0;
			for(MyNode neighbor : model.graph.getNeighbors(node)){
				switch(neighbor.getLabel()){
					case Time:
						timeCount++;
						break;
					case Person:
						peopleCount++;
						break;
					case Place:
						placeCount++;
						break;													
				}
			}
			
			//Calculate penalty according to neighbors' assignment
			switch(node.getLabel()){
				case Time:{	
					//TODO
					/*timeScore -= Math.pow(timeCount/MyNode.getTimeCount(), 2);
					timeScore -= Math.pow(peopleCount/MyNode.getPeopleCount(), 2);
					timeScore -= Math.pow(placeCount/MyNode.getPeopleCount(), 2);*/
				}
				break;
				case Person:{
					/*peopleScore -= Math.pow(timeCount/MyNode.getTimeCount(), 2);
					peopleScore -= Math.pow(peopleCount/MyNode.getPeopleCount(), 2);*/
				}
				break;
				case Place:{				
					/*placeScore -= Math.pow(timeCount/MyNode.getTimeCount(), 2);					
					placeScore -= Math.pow(placeCount/MyNode.getPeopleCount(), 2);*/
				}
				break;
			}
		}
		
		System.out.println("time: " + timeScore);
		System.out.println("person: " + peopleScore);
		System.out.println("place: " + placeScore);
		
		score = timeScore + placeScore + peopleScore;
		return score;
	}
	
}
