package SolverAndCacheManager;

import java.util.ArrayList;
import java.util.Collections;

import MyObject.*;
import searcherAndsearchable.*;

public class MySolver implements Solver<String> {

	//private static MySolver instance;//Singleton
	private Sercher<MatrixChar> serchAlgoritem;
	
	public MySolver(Sercher<MatrixChar> theSerch) { serchAlgoritem=theSerch;}
	
//	public static MySolver getSolver() { Singleton
//		if (instance==null)
//			instance=new MySolver();
//		return instance;
//	}
	

	@Override
	public Solution<String> Solve(ArrayList<String> problem) {
		MatrixChar thematrix=new MatrixChar(problem);
		char[][] matrix=thematrix.getMatrix(); //Copy matrix
		Solution<String> solutionProtocol=new Solution<String>();// The common language
		SolutionPiece theRealAnswer;
		Solution<MatrixChar> solutionMatrixs = new Solution<MatrixChar>();// Solution with matrixs
		Solution<MatrixChar> tempSol;
		PipeGameBoard sercheble=new PipeGameBoard(new State<MatrixChar>(new MatrixChar(matrix)));
		int i,j;
		if (problem.size()*problem.get(0).length()>9) { // If the matrix is large then reduce the problem
			solutionMatrixs =new HillClimbing(new MyStateGrader(), 400).search(sercheble);
			sercheble=new PipeGameBoard(new State<MatrixChar>(solutionMatrixs.getSolution().get(solutionMatrixs.getSolution().size()-1))); //solutionMatrix holder matrixchar arraylist and the best one is the last matrix
		}
		tempSol=serchAlgoritem.search(sercheble);
		Collections.reverse(tempSol.getSolution()); 
		solutionMatrixs.getSolution().addAll(tempSol.getSolution());
		Collections.reverse(solutionMatrixs.getSolution());
		char[][] goal=solutionMatrixs.getSolution().get(0).getMatrix();
		for (i=0;i<goal.length;i++) // Build the solution with the common language
			for (j=0;j<goal[i].length;j++) 
				if (matrix[i][j]!=goal[i][j]) {
					theRealAnswer=new SolutionPiece(new String(Integer.toString(i)+","+Integer.toString(j)+","));
					matrix[i][j]=PipeGameBoard.rotate(matrix[i][j]);
					while(matrix[i][j]!=goal[i][j]) {
						matrix[i][j]=PipeGameBoard.rotate(matrix[i][j]);
						theRealAnswer.increaseRotate(matrix[i][j]);// Count to rotate
					}
					solutionProtocol.getSolution().add(theRealAnswer.getSolutionPiece());
				}
		solutionProtocol.getSolution().add("done");// End of the solution
		return solutionProtocol;
	}
}