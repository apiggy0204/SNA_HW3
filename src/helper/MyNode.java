package helper;

public class MyNode implements Comparable<MyNode> {
	private int id;
	
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
