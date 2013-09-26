
public class Board {
	private int board[];
	private int solutionboard[];
	private int noofpegs=14;
	private int movehistory[][];
	//List of Possible Moves (Start peg,Jump over peg, Into Peg)
	//I.e. Start a position  0 jump over position 1 in the array into position 3 So the change 
	// 	
	private int move[][]={
						 {0,1,3},
						 {0,2,5},
						 {1,3,6},
						 {1,4,8},
						 {2,4,7},
						 {2,5,9},
						 {3,6,10},
						 {3,7,12},
						 {3,1,0},
						 {3,4,5},
						 {4,7,11},
						 {4,8,13},
						 {5,9,14},
						 {5,8,12},
						 {5,2,0},
						 {5,4,3},
						 {6,3,1},
						 {6,7,8},
						 {7,4,2},
						 {7,8,9},
						 {8,4,1},
						 {8,7,6},
						 {9,5,2},
						 {9,8,7},
						 {10,6,3},
						 {10,11,12},
						 {11,7,4},
						 {11,12,13},
						 {12,7,3},
						 {12,8,5},
						 {12,11,10},
						 {12,13,14},
						 {13,8,4},
						 {13,12,11},
						 {14,9,5},
						 {14,13,12}
						 };//list of moves(start,over, into)

	//Default Constructor
	//Hole starts at the top of the triangle board
	public Board(){
		board=new int[]{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		solutionboard=new int[]{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1};//Stores the orginal board
		movehistory=new int [14][3];//Holds all the moves
		
	}
	//Constructor Allows you to input the triangle board
	
	public Board(int b){
		board=new int[15];
		solutionboard=new int[15];
		for(int i=0;i<15;i++){
			board[i]=1;
			solutionboard[i]=1;
		}
		board[b]=0;
		solutionboard[b]=0;
		movehistory=new int [14][3];//Holds all the moves
	}
	//Checks to see if it is a valid move 	
	public boolean validmove(int start,int over,int into){
		if(start==1&&over==1&& into==0){
			return true;
		}
		return false;
	}
	//Undo move 
	public void undomove(int start,int over,int into){
		board[start]=1;
		board[over]=1;
		board[into]=0;
		movehistory[noofpegs-1][0]=0;
		movehistory[noofpegs-1][1]=0;
		movehistory[noofpegs-1][2]=0;
		noofpegs++;//must add the second peg back board
	}
	public void makemove(int start,int over,int into){
		board[start]=0;
		board[over]=0;
		board[into]=1;
		movehistory[noofpegs-1][0]=start;
		movehistory[noofpegs-1][1]=over;
		movehistory[noofpegs-1][2]=into;
		noofpegs--;//must remove over peg 
	}
	//Prints the solution of the Peg Problem
	public void printsolution(){
		int movecount=1;
		System.out.println("INITIAL CONFIGURATION");
		for(int i=13;i>=0;i--){
			printfunsolution();
			if(i!=0){
				System.out.println("Move Number:"+movecount+"\nPeg Move:{"+movehistory[i][0]+","+movehistory[i][1]+","+movehistory[i][2]+"}");
			}
			movecount++;
			makesolutionmove(movehistory[i][0], movehistory[i][1], movehistory[i][2]);
		}
	}
	//Finds the solution 
	public void findsolution(){
		//Base Case Only 1 peg on board means it is solved 
		if(noofpegs==1){
			printsolution();//Print Solution 
			return;//Return 
		}
		else{
			//Checks all possible moves
			for(int i=0;i<36;i++){
				if(validmove(board[move[i][0]],board[move[i][1]],board[move[i][2]])){
					makemove(move[i][0],move[i][1],move[i][2]);				
					findsolution();
					int temp=noofpegs;
					if(temp==1){
						break;//break out of the loop
					}
					undomove(movehistory[temp][0],movehistory[temp][1],movehistory[temp][2]);
				}
			}
		}
	}
	public void printfunsolution(){
		System.out.println(solutionboard[0]);
		System.out.println(solutionboard[1]+" "+solutionboard[2]);
		System.out.println(solutionboard[3]+" "+solutionboard[4]+" "+solutionboard[5]);
		System.out.println(solutionboard[6]+" "+solutionboard[7]+" "+solutionboard[8]+" "+solutionboard[9]);
		System.out.println(solutionboard[10]+" "+solutionboard[11]+" "+solutionboard[12]+" "+solutionboard[13]+" "+solutionboard[14]);
	}
	public void makesolutionmove(int start,int over,int into){
		solutionboard[start]=0;
		solutionboard[over]=0;
		solutionboard[into]=1;
		
	}
	public void getsolution(){
		findsolution();	
	}
}
