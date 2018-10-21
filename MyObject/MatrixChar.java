package MyObject;

import java.util.ArrayList;

public class MatrixChar implements Comparable<MatrixChar> {

	private char[][] matrix;
	private int size;
	
	public MatrixChar(char[][] matrix) {
		setMatrix(matrix);
	}
	
	public MatrixChar(ArrayList<String> matrix) {
		char[][] matrixchar=new char[matrix.size()][];
		for (int i=0;i<matrix.size();i++)
			matrixchar[i]=matrix.get(i).toCharArray();
		setMatrix(matrixchar);
	}
	
	public char[][] getMatrix() {
		return matrix;
	}
	
	public void setRow(char[] line , int index){ // Deep copy set
		if(index<matrix.length)
			matrix[index]=line.clone();
	}
	
	
	public char[] getRow(int index) {
		if(index<matrix.length)
			return matrix[index].clone();
		return null;
	}
	
	public void setMatrix(char[][] matrix) { // Deep copy set
		this.matrix= new char[matrix.length][];
		for (int i=0;i<matrix.length;i++)
			this.matrix[i]=matrix[i].clone();
		size=matrix.length*matrix[0].length;
	}
	
	public boolean equals (char[][] mat) {
		for (int i=0;i<matrix.length;i++)
			for (int j=0;j<matrix[i].length;j++)
				if (matrix[i][j]!=mat[i][j])
					return false;
		return true;
	}
	
	public boolean equals (MatrixChar mat) {
	 return equals(mat.getMatrix());
	}

	
	public void print() {
		for (int i=0;i<matrix.length;i++) {
			for (int j=0;j<matrix[i].length;j++)
				System.out.print(matrix[i][j]);
			System.out.println("");
		}
	}

	//Override the functions of Object
	@Override
	public int hashCode() { 
		String hash=new String(matrix[0]);
		for (int i=1;i<matrix.length;i++)
			hash=new String(hash+new String(matrix[i]));
		return hash.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return (this.hashCode() == obj.hashCode());
	}
	// Comparable interface
	@Override
	public int compareTo(MatrixChar o) {
		return size-o.size;
	}

}
 
