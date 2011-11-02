package helper;

public class MyNode implements Comparable<MyNode> {
	
	public enum Label {Movie, Person, Place, Time};
	
	private int id;
	private Label label = null;
	
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public MyNode(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}
	
	public int compareTo(MyNode node){
		if (id>node.id) return 1;
		else if (id==node.id) return 0;
		else {
			return -1;
		}
	}
}
