import java.io.FileInputStream;



import java.io.FileNotFoundException;

import java.util.Scanner;
/**
 * @author Marvi Shaikh
 * @version 1.0
 * @since 17/01/2018
 */

public class JumpIt {

	private String filename;
	private int count = 0;
	private final int Max_Size = 15;
	int[]row = new int[Max_Size];


	/**
	 * 
	 * Jumpit constructor
	 */
	public JumpIt(String theName) {

		this.filename = new String(theName);

	} 

	public int play() throws BadInputException{
		if (row[0] !=0) {
			throw new BadInputException();
		}
		return play(row,0,count-1);
	}
	
	/**
	 * 
	 * @return cost - lowest cost of playing game
	 */
	private int play(int[] a , int first,int last) {
		int cost;
		cost = a[first];

		if (first==a.length-1) {
			return cost;
		}else if (first == a.length-2) {
			cost+=play(a,first+1,last); 
		}else {
			int path1 = cost+ play(a, first+1,last);
			int path2 = cost + play(a,first+2,last);

			return Math.min(path1, path2);
		}
		return cost;

	}
	
	/**
	 * 
	 * formattes the output
	 */

	public void printGame() {
		int i = 0;
		Scanner readintegers = null;
		try {
			System.out.printf("\nOpening the file %s",filename);
			readintegers = new Scanner(new FileInputStream("src/JumpItGame/"+filename));
			
			while (readintegers.hasNext() && count!= Max_Size) {
				if(readintegers.hasNextInt()) {
					this.row[i]=readintegers.nextInt();
					i ++ ;
					count ++;
				}else {
					System.out.println("\nThe file contains an invalid input ");
					readintegers.next();
				}
				
			}
			
			System.out.println("\nThe file has "+ count+" integers");
			String array = " ";
			for (int j=0;j<count;j++) {
				array+=" ";
				array+=this.row[j];
			}
			System.out.println(array);
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

		
	}
	
}
