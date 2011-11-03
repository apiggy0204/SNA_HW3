package sna_hw3;

import helper.MyNode;


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
						
			int timeCount = 0;
			int peopleCount = 0;
			int placeCount = 0;
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
					timeScore -= (timeCount + peopleCount + placeCount);
					/*timeScore -= Math.pow(timeCount/MyNode.getTimeCount(), 1);
					timeScore -= Math.pow(peopleCount/MyNode.getPeopleCount(), 1);
					timeScore -= Math.pow(placeCount/MyNode.getPeopleCount(), 1);*/
				}
				break;
				case Person:{
					peopleScore -= (timeCount + peopleCount);
					/*peopleScore -= Math.pow(timeCount/MyNode.getTimeCount(), 1);
					peopleScore -= Math.pow(peopleCount/MyNode.getPeopleCount(), 1);*/
				}
				break;
				case Place:{				
					placeScore -= (timeCount + placeCount);
					/*placeScore -= Math.pow(timeCount/MyNode.getTimeCount(), 1);					
					placeScore -= Math.pow(placeCount/MyNode.getPeopleCount(), 1);*/
				}
				break;
			}
		}
		
		/*System.out.println("time: " + timeScore);
		System.out.println("person: " + peopleScore);
		System.out.println("place: " + placeScore);*/
		
		//Inverse frequency
		timeScore *= Math.log(28960/100);//Math.log(MyNode.getTotalVertexCount()/MyNode.getTimeCount());
		peopleScore *= Math.log(28960/16542);//Math.log(MyNode.getTotalVertexCount()/MyNode.getPeopleCount());
		placeScore *= Math.log(28960/817);//Math.log(MyNode.getTotalVertexCount()/MyNode.getPlaceCount());
		
		/*System.out.println("time: " + timeScore);
		System.out.println("person: " + peopleScore);
		System.out.println("place: " + placeScore);*/
				
		score = timeScore + placeScore + peopleScore;
		return score;
	}
	
}
