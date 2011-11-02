package helper;

import helper.MyNode.Label;


public class GA {
	
	public static void permute(Model model, double ratio)
	{
		Model retModel = new Model(model);
		for(int i=0; i<ratio*model.nodeList.size(); i++)
		{			
			int randId1 = (int) ( model.nodeList.size() * Math.random() );
			int randId2 = (int) ( model.nodeList.size() * Math.random() );
			MyNode node1 = model.nodeList.get(randId1);
			MyNode node2 = model.nodeList.get(randId2);
			Label labelTemp = node1.getLabel();
			node1.setLabel(node2.getLabel());
			node2.setLabel(labelTemp);
		}
	}
	
}
