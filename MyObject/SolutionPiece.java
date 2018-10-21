package MyObject;

public class SolutionPiece {
	
	private String coordinate;
	private Integer rotate;
	
	public SolutionPiece(String coordinate) {
		this.coordinate=new String(coordinate);
		rotate=1;
	}
	
	public void increaseRotate(char ch) {
		rotate++;
		if (ch=='-'||ch=='|')  rotate = rotate%2;
		else  rotate = rotate%4;
	}
	
	public String getSolutionPiece () {
		coordinate=new String(coordinate+rotate.toString());// Add the rotate's number 
		return coordinate;
	}

	@Override
	public int hashCode() {
		return coordinate.hashCode();
	}

	public boolean equals(SolutionPiece sp) {
		return coordinate.equals(sp.coordinate);
	}
	
}
