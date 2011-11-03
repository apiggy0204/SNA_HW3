package helper;

public class MyNode implements Comparable<MyNode> {
	
	public enum Label {Movie, Person, Place, Time};
	
	private static int movieCount = 0;
	private static int timeCount = 0;
	private static int placeCount = 0;
	private static int peopleCount = 0;
	
	private int id;
	private Label label = null;
	
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		if(this.label != null){
			//Update label counts
			switch(this.label){
				case Person:
					setPeopleCount(getPeopleCount() - 1);
					break;
				case Place:
					setPlaceCount(getPlaceCount() - 1);
					break;
				case Time:
					setTimeCount(getTimeCount() - 1);
					break;
				case Movie:
					setMovieCount(getMovieCount() - 1);
					break;
			}
		}
		
		this.label = label;
		
		switch(label){
			case Person:
				setPeopleCount(getPeopleCount() + 1);
				break;
			case Place:
				setPlaceCount(getPlaceCount() + 1);
				break;
			case Time:
				setTimeCount(getTimeCount() + 1);
				break;
			case Movie:
				setMovieCount(getMovieCount() + 1);
				break;
		}
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

	public static int getMovieCount() {
		return movieCount;
	}

	public static void setMovieCount(int movieCount) {
		MyNode.movieCount = movieCount;
	}

	public static int getTimeCount() {
		return timeCount;
	}

	public static void setTimeCount(int timeCount) {
		MyNode.timeCount = timeCount;
	}

	public static int getPlaceCount() {
		return placeCount;
	}

	public static void setPlaceCount(int placeCount) {
		MyNode.placeCount = placeCount;
	}

	public static int getPeopleCount() {
		return peopleCount;
	}

	public static void setPeopleCount(int peopleCount) {
		MyNode.peopleCount = peopleCount;
	}
}
