package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import MyObject.MatrixChar;
import MyObject.Solution;
import MyObject.State;
import searcherAndsearchable.*;


public class MainSearcherAndSearchable {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		int counter=0;
		String que=new String();
//		while (!que.equals("exit")) {
//			char[][] theMatrix=randomMatrix(5,5);
//			theMatrix=getStartAndGoal(theMatrix,0,3,3,1);
//			MatrixChar matrix =new MatrixChar(theMatrix);
//			System.out.println("the startState");
//			matrix.print();
//			que=new Scanner(System.in).nextLine();
//			if (que.equals("Y")) {
//				writeGames(theMatrix, counter);
//				counter++;
//			}
			
//		}
		char[][] theMatrix=randomMatrix(5,4);
		theMatrix=getStartAndGoal(theMatrix,0,0,4,1);
		theMatrix=goal(theMatrix);
		MatrixChar matrix =new MatrixChar(theMatrix);
		System.out.println("the startState");
		matrix.print();
		new Scanner(System.in).nextLine();
		Serchable<MatrixChar> serchable=new PipeGameBoard(new State<MatrixChar>(matrix));
		Sercher<MatrixChar> sercher = new BestFS<MatrixChar>();
		Solution<MatrixChar> solution = sercher.search(serchable);
		System.out.println("the goalState");
		for(MatrixChar mat : solution.getSolution()) {
			mat.print();
			System.out.println("--------------------");
		} 
	}
	
	public static void writeGames(char[][] matrix,int counter) throws FileNotFoundException {
		PrintWriter games=new PrintWriter(new FileOutputStream(new File("D:/המסלול האקדמי המכללה למנהל/שנה ב/סמסטר א/פתם/פרויקט/myServer/games/game"+Integer.toString(counter)+".txt")));
		for (int i=0;i<matrix.length;i++)
			games.println(matrix[i]);
		games.close();
	}
	
	
	public static char[][] randomMatrix(int row,int col){
		char[][] newMatrix=new char[row][col];
		char temp;
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++) {
				int muli = (int)(Math.random()*7+1);
				switch (muli) {
				case (1):temp='|';break;
				case (2):temp='-';break;
				case (3):temp='7';break;
				case (4):temp='J';break;
				case (5):temp=' ';break;
				case (6):temp='L';break;
				case (7):temp='F';break;

				default:
					temp='F';
				}
				newMatrix[i][j]=temp;
			}
		return newMatrix;
		
	}
	
	public static char[][] getStartAndGoal(char[][] matrix,int rowStart,int colStart,int rowGoal,int colGol){
		matrix[rowStart][colStart]='s';
		matrix[rowGoal][colGol]='g';
		return matrix;
	}
	
	public static char[][] goal(char[][] temp){
		temp[0][0]='s';
		temp[0][1]='-';
		temp[0][2]='7';
		temp[0][3]=' ';
		temp[1][0]=' ';
		temp[1][1]='|';
		temp[1][2]='L';
		temp[1][3]='7';
		temp[2][0]='-';
		temp[2][1]='F';
		temp[2][2]=' ';
		temp[2][3]='|';
		temp[3][0]='7';
		temp[3][1]='F';
		temp[3][2]='-';
		temp[3][3]='J';
		temp[4][0]=' ';
		temp[4][1]='g';
		temp[4][2]=' ';
		temp[4][3]='-';
		return temp;
	}
	
	public static char[][] zeroMatrix(int row,int col){
		char[][] matrix=new char[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				matrix[i][j]=' ';
		return matrix;
	}
	public static char[][] spesificMatrix(int row,int col){
		char[][] newMatrix=new char[row][col];
		newMatrix[0][0]='s';
		newMatrix[0][1]='7';
		newMatrix[0][2]='J';
		newMatrix[0][3]='L';
		newMatrix[1][0]=' ';
		newMatrix[1][1]='L';
		newMatrix[1][2]='F';
		newMatrix[1][3]=' ';
		newMatrix[2][0]='7';
		newMatrix[2][1]=' ';
		newMatrix[2][2]='L';
		newMatrix[2][3]='7';
		newMatrix[3][0]='|';
		newMatrix[3][1]=' ';
		newMatrix[3][2]='|';
		newMatrix[3][3]='g';
		return newMatrix;
	}
}


