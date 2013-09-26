public class driver {
//Can take an option parmeter between 0-14 that tells where the inital hole is, Assumes the argument will be a number 
//I.e 8 not dog otherwise parseInt error.
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length==1){
			int hole=Integer.parseInt(args[0]);	
			if(hole>=0 && hole<=14){
				//Valid 
				Board b1=new Board(hole);
				b1.findsolution();	
			}
			else {
				System.out.println("Invalid input");
			}
		}	
		else{	
			//Calls Default Board
			Board b1=new Board();
			b1.findsolution();
		}
	}

}
