package searcherAndsearchable;

import java.awt.Point;
import java.util.ArrayList;

import MyObject.MatrixChar;
import MyObject.State;

public class PipeGameBoard implements Serchable<MatrixChar> {
	
	private State<MatrixChar> initState;
	private Point start, goal;
	private oriantation[][] matrixOriantation;
	private int cost;
	
	private enum oriantation { // Where did the last step come from 
		right,left,top,bottom,start;
	}
	public PipeGameBoard(State<MatrixChar> problem) {
		int sgCounter=0; // Break check sum
		initState = problem;
		initState.setCost(0);
		char[][] theProblemMatrix=problem.getState().getMatrix();
		outerloop: // Break from 2 loops
		for (int i=0;i<theProblemMatrix.length;i++)
			for (int j=0;j<theProblemMatrix[i].length;j++) {
				if (theProblemMatrix[i][j]=='s') {//Find the start one time
					start=new Point(i, j);
					sgCounter++;
				}
				if (theProblemMatrix[i][j]=='g') {//Find the goal one time
					goal=new Point(i, j);
					sgCounter++;
				}
				if (sgCounter==2)
					break outerloop; 
			}
	}
	
	@Override
	public State<MatrixChar> getInitialState() {
		return initState;
	}

	@Override
	public ArrayList<State<MatrixChar>> getAllPosibleState (State<MatrixChar> n) {//All the rotate positions of one cell in the array
		ArrayList<State<MatrixChar>> posibleState= new ArrayList<State<MatrixChar>>();
		MatrixChar originalMatrix = n.getState();
		MatrixChar newMap;
		char[] row;
		char ch;
		int mtrixLength=n.getState().getMatrix().length;
		int rowLength;
			for (int i=0;i<mtrixLength;i++) {
				row=originalMatrix.getRow(i);
				rowLength=row.length;
				for (int j=0;j<rowLength;j++)
					if (row[j]!='s'&&row[j]!='g'&&row[j]!=' '){
							ch=row[j];
							row[j]=rotate(row[j]);
							while(row[j]!=ch) {
								newMap=new MatrixChar(originalMatrix.getMatrix());//Copy deep Matrix
								newMap.setRow(row, i);
								matrixOriantation=new oriantation[initState.getState().getMatrix().length][initState.getState().getMatrix()[0].length];
								matrixOriantation[i][j]=oriantation.start;
								cost=n.getState().getMatrix().length*n.getState().getMatrix()[0].length;//The cost start from cells' number
								if (pathFinder(newMap.getMatrix(), i, j, oriantation.start,1)) {// If this position come to the goal
									posibleState.add(new State<MatrixChar>(newMap));//Add a new possible
									posibleState.get(posibleState.size()-1).setCost(cost);
								}
								row[j]=rotate(row[j]);// End all rotate in a cell
							}
					}
			}
			
		return posibleState;
	}
	
	@Override
	public boolean isGoal(State<MatrixChar> theState) {
		matrixOriantation=new oriantation[theState.getState().getMatrix().length][theState.getState().getMatrix()[0].length];
		matrixOriantation[(int)start.getX()][(int)start.getY()]=oriantation.start;
		return pathFinder(theState.getState().getMatrix(),(int)start.getX(),(int)start.getY(), oriantation.start,0);
	}

	
	private boolean pathFinder (char[][] matrix,int row,int col,oriantation cameFrom,int flag) { //If the state is a goal or close to being a goal
		if ((row<0)||(row>=matrix.length||col<0||col>=matrix[matrix.length-1].length)||matrix[row][col]==' ')
				return false;
		if (cameFrom==oriantation.start) {// Just in the beginning of the function (one time)
			// 4 ways for the start
			if (matrix[row][col]=='s')return (pathFinder(matrix, row-1, col, oriantation.bottom,flag)||pathFinder(matrix, row+1, col, oriantation.top,flag)||
										      pathFinder(matrix, row, col-1, oriantation.right,flag)||pathFinder(matrix, row, col+1, oriantation.left,flag));
			// The other pipes you have 2 ways with different direction
			if (matrix[row][col]=='|')return (pathFinder(matrix, row-1, col, oriantation.bottom,flag)||
											  pathFinder(matrix, row+1, col, oriantation.top,flag));
			
			if (matrix[row][col]=='-')return (pathFinder(matrix, row, col-1, oriantation.right,flag)||
											  pathFinder(matrix, row, col+1, oriantation.left,flag));
			
			if (matrix[row][col]=='F')return (pathFinder(matrix, row+1, col, oriantation.top,flag)||
											  pathFinder(matrix, row, col+1, oriantation.left,flag));
			
			if (matrix[row][col]=='L')return( pathFinder(matrix, row-1, col, oriantation.bottom,flag)||
											  pathFinder(matrix, row, col+1, oriantation.left,flag));
			
			if (matrix[row][col]=='7')return (pathFinder(matrix, row+1, col,oriantation.top,flag)||
											  pathFinder(matrix, row, col-1, oriantation.right,flag));
			
			if (matrix[row][col]=='J')return (pathFinder(matrix, row-1, col, oriantation.bottom,flag)||
											  pathFinder(matrix, row, col-1, oriantation.right,flag));
		}
		if (matrix[row][col]=='s')// Back to the goal
			return false;
		
		if (matrix[row][col]=='g')// Find the goal
			return true;
		// All pipes with his rules and every pipe that possible move his so the cost down 1
		if (matrix[row][col]=='|') {
			if (cameFrom==oriantation.right||cameFrom==oriantation.left ||matrixOriantation[row][col]!=null)// wrong situation
				return false;
			cost--;
			matrixOriantation[row][col]=cameFrom;
			if (cameFrom==oriantation.top) // Where i came from and where i'm going
				return pathFinder(matrix, row+1, col, oriantation.top,flag);
			else 
				return pathFinder(matrix, row-1, col, oriantation.bottom,flag);
		
		}
		
		if (matrix[row][col]=='-') {
			if (cameFrom==oriantation.top||cameFrom==oriantation.bottom||matrixOriantation[row][col]!=null)
				return false;
			cost--;
			matrixOriantation[row][col]=cameFrom;
			if (cameFrom==oriantation.left)
				return pathFinder(matrix, row, col+1, oriantation.left,flag);
			else
				return pathFinder(matrix, row, col-1, oriantation.right,flag);
			
		}
		
		if (matrix[row][col]=='L') {
			if (cameFrom==oriantation.bottom||cameFrom==oriantation.left||matrixOriantation[row][col]!=null)
				return false;
			cost--;
			matrixOriantation[row][col]=cameFrom;
			if(cameFrom==oriantation.top) 
				return pathFinder(matrix, row, col+1, oriantation.left,flag);
			else		
				return pathFinder(matrix, row-1, col, oriantation.bottom,flag);
			
		}
		
		if (matrix[row][col]=='F') {
			if (cameFrom==oriantation.left||cameFrom==oriantation.top||matrixOriantation[row][col]!=null)
				return false;
			cost--;
			matrixOriantation[row][col]=cameFrom;
			if (cameFrom==oriantation.right) 
				return pathFinder(matrix, row+1, col, oriantation.top,flag);
			else
				return pathFinder(matrix, row, col+1, oriantation.left,flag);
		}
		
		if (matrix[row][col]=='7') {
			if(cameFrom==oriantation.top||cameFrom==oriantation.right||matrixOriantation[row][col]!=null)
				return false;
			cost--;
			matrixOriantation[row][col]=cameFrom;
			if(cameFrom==oriantation.left) 
				return pathFinder(matrix, row+1, col, oriantation.top,flag);
			else
				return pathFinder(matrix, row, col-1, oriantation.right,flag);
			
		}
		
		if (matrix[row][col]=='J') {
			if (cameFrom==oriantation.right||cameFrom==oriantation.bottom||matrixOriantation[row][col]!=null)
				return false;
			cost--;
			matrixOriantation[row][col]=cameFrom;
			if (cameFrom==oriantation.left)
				return pathFinder(matrix, row-1, col, oriantation.bottom,flag);
			else
				return pathFinder(matrix, row, col-1, oriantation.right,flag);
			
		}
		
		return false;
		
	}
	
	public static char rotate (char ch) { // rotation chars
		switch(ch) {
		case('|'): return '-';
		case ('-'): return '|';
		case ('7'): return 'J';
		case ('J'): return 'L';
		case('L'): return 'F';
		case ('F'): return '7';
		default:
			return 0;
		}
	}

}




